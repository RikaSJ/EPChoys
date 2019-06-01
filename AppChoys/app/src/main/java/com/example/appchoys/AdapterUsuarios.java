package com.example.appchoys;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

public class AdapterUsuarios extends RecyclerView.Adapter<AdapterUsuarios.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<Usuarios> ListaUsuarios;
    private View view;
    private View.OnClickListener listener;

    public AdapterUsuarios(ArrayList<Usuarios> listaUsuarios) {
        ListaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public AdapterUsuarios.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_usuarios,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsuarios.ViewHolderDatos holder, int i) {
     holder.TxtNombre.setText("Nombre: "+ListaUsuarios.get(i).getFirst_name()+" "+ListaUsuarios.get(i).getLast_name());
     holder.TxtCorreo.setText("Email: "+ListaUsuarios.get(i).getEmail());
     holder.TxtId.setText("Id: "+ListaUsuarios.get(i).getId());
     Picasso.with(view.getContext())
             .load(ListaUsuarios.get(i).getAvatar())
             .into(holder.Avatar);
    }

    @Override
    public int getItemCount() {
        return ListaUsuarios.size();
    }
     public void setOnClickListener(View.OnClickListener listener){
           this.listener=listener;
     }
    @Override
    public void onClick(View v) {
          if(listener!= null){
              listener.onClick(v);
          }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        //Crear variables del layaout lista_usuario
        TextView TxtNombre;
        TextView TxtCorreo;
        TextView TxtId;
        ImageView Avatar;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            asignarDatos();
        }

        public void asignarDatos() {
            //Asignar Datos a cada seccion de la lista de usuario
            TxtNombre = (TextView) itemView.findViewById(R.id.LluNombre);
            TxtCorreo = (TextView) itemView.findViewById(R.id.Llucorreo);
            TxtId = (TextView) itemView.findViewById(R.id.LluId);
            Avatar = (ImageView) itemView.findViewById(R.id.LluAvatar);
        }
    }

}
