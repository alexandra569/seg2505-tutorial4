package com.example.tutoriel4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList name_id, name, mail, password;

    CustomAdapter(Activity activity, Context context, ArrayList name_id, ArrayList name, ArrayList mail,
                  ArrayList password){
        this.activity = activity;
        this.context = context;
        this.name_id = name_id;
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name_id_txt.setText(String.valueOf(name_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.mail_txt.setText(String.valueOf(mail.get(position)));
        holder.password_txt.setText(String.valueOf(password.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                intent.putExtra("id", String.valueOf(name_id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("mail", String.valueOf(mail.get(position)));
                intent.putExtra("password", String.valueOf(password.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_id_txt, name_txt, mail_txt, password_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id_txt = itemView.findViewById(R.id.name_id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            mail_txt = itemView.findViewById(R.id.mail_txt);
            password_txt = itemView.findViewById(R.id.password_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }

    }

}
