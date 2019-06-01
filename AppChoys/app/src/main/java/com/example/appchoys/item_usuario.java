package com.example.appchoys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class item_usuario extends AppCompatActivity {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CargarExtras();
        setContentView(R.layout.item_user);
        TextView TxtNombre = (TextView) findViewById(R.id.LluNombre);
        TextView TxtCorreo = (TextView) findViewById(R.id.Llucorreo);
        TextView TxtId = (TextView) findViewById(R.id.LluId);
        ImageView Avatar = (ImageView) findViewById(R.id.LluAvatar);

        TxtNombre.setText("Nombre: "+first_name+" "+last_name);
        TxtCorreo.setText("Email: "+email);
        TxtId.setText("Id: "+id);
        Picasso.with(this)
                .load(avatar)
                .into(Avatar);
    }
    public void CargarExtras(){
        id=getIntent().getStringExtra("id");
        email=getIntent().getStringExtra("email");
        first_name=getIntent().getStringExtra("first_name");
        last_name=getIntent().getStringExtra("last_name");
        avatar=getIntent().getStringExtra("avatar");
    }

}
