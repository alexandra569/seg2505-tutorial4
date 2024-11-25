package com.example.tutoriel4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.ActionBar;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper myDB;
    EditText nomUtilisateur;
    EditText email;
    EditText motDePasse;
    Button btnInscription;
    Button btnDeleteData;
    String id , name, mail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myDB = new DatabaseHelper(RegisterActivity.this);

        nomUtilisateur = findViewById(R.id.nomUtilisateur);
        email = findViewById(R.id.emailinscription);
        motDePasse = findViewById(R.id.motDePasseinscription);
        btnInscription = findViewById(R.id.btnInscription);
        btnDeleteData = findViewById(R.id.btnDeleteData);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        addData();

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    public void addData(){
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(nomUtilisateur.getText().toString() , email.getText().toString() , motDePasse.getText().toString());

                if (isInserted){
                    Toast.makeText(RegisterActivity.this, "Data Inserted...", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(RegisterActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }


    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("mail") && getIntent().hasExtra("password")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            mail = getIntent().getStringExtra("mail");
            password = getIntent().getStringExtra("password");

            //Setting Intent Data
            nomUtilisateur.setText(name);
            email.setText(mail);
            motDePasse.setText(password);
            Log.d("stev", name+" "+mail+" "+password);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(RegisterActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void login(View view){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }
}