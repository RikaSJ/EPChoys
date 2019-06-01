package com.example.appchoys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Cursor cursor;
    ArrayList<Usuarios> LU= new ArrayList<>();
    TextView espera;
    int x=0;
    final BdUsuarios conn= new BdUsuarios(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ws();
        espera= (TextView) findViewById(R.id.cargando);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    espera.setVisibility(View.GONE);
                    LU=Extraccion();
                    VistaRecycler();
                    x++;
                }
            },1200);

    }
    public void VistaRecycler(){
        recyclerView = (RecyclerView) findViewById(R.id.ListaUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        //Cargar el Rycicler
        AdapterUsuarios adapterUsuarios = new AdapterUsuarios(LU);
        adapterUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),
                //        "Seleciono"+ LU.get(recyclerView.getChildAdapterPosition(v)).getId(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),item_usuario.class);
                intent.putExtra("id",LU.get(recyclerView.getChildAdapterPosition(v)).getId());
                intent.putExtra("email",LU.get(recyclerView.getChildAdapterPosition(v)).getEmail());
                intent.putExtra("first_name",LU.get(recyclerView.getChildAdapterPosition(v)).getFirst_name());
                intent.putExtra("last_name",LU.get(recyclerView.getChildAdapterPosition(v)).getLast_name());
                intent.putExtra("avatar",LU.get(recyclerView.getChildAdapterPosition(v)).getAvatar());
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapterUsuarios);
    }
    public void Ws(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://reqres.in/api/users?page=2";
        try{
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //Procesar JSONObject
                    JSONArray jsonArray =response.optJSONArray("data");
                    SQLiteDatabase db = conn.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    Log.d("lon",jsonArray.length()+"");
                    for (int i=0; i< jsonArray.length();i++){
                        Usuarios aux = new Usuarios(jsonArray.optJSONObject(i));
                        values.put("id",aux.getId());
                        values.put("email",aux.getEmail());
                        values.put("first_name",aux.getFirst_name());
                        values.put("last_name",aux.getLast_name());
                        values.put("avatar",aux.getAvatar());
                        //validar que el dato no este en la base de datos
                        if(db.rawQuery("SELECT id FROM Usuarios WHERE id LIKE "+aux.getId(),null).getCount() == 0){
                            db.insert("Usuarios",null,values);
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Error, necesita conectarse a internet la primera vez",Toast.LENGTH_LONG).show();
                }
            });
            queue.add(request);

        }catch (Exception ex){

        }
    }
    public ArrayList<Usuarios> Extraccion(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] columnas= new String[]{"id","email","first_name","last_name","avatar"};

        ArrayList<Usuarios> Lista= new ArrayList<>();
        cursor= db.query("Usuarios",columnas,null,null,null,null,null);
        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String first_name = cursor.getString(cursor.getColumnIndex("first_name"));
            String last_name = cursor.getString(cursor.getColumnIndex("last_name"));
            String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
            Usuarios data = new Usuarios(id,email,first_name,last_name,avatar);
            Lista.add(data);
        }
        return Lista;
    }
}
