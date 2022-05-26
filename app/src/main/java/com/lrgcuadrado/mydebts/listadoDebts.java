package com.lrgcuadrado.mydebts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class listadoDebts extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_debts);


        listView = (ListView) findViewById(R.id.ListView);
        CargarListado();
        // Boton de regreso
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(listadoDebts.this,"Posicion:"+i,Toast.LENGTH_SHORT).show();
                Toast.makeText(listadoDebts.this,listado.get(i),Toast.LENGTH_SHORT).show();
                int clave = Integer.parseInt(listado.get(i).split(",")[0]);
                String persona= listado.get(i).split(",")[1];
                String razon= listado.get(i).split(",")[2];
                String dinero= listado.get(i).split(",")[3];
                Intent intent = new Intent(listadoDebts.this, modificarDebts.class);
                intent.putExtra("id",clave);
                intent.putExtra("persona",persona);
                intent.putExtra("razon",razon);
                intent.putExtra("dinero",dinero);
                startActivity(intent);

            }
        });

    }
    // Boton hacia atras
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // Si dejamos el metodo de carga en el oncreate no se actuliza si no lo metemos a una funcion

    private void CargarListado(){
        listado = ListaDebts();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaDebts(){
        ArrayList<String> datos = new ArrayList<String>();
        try {
            BaseHelper helper = new BaseHelper(this, "baseDebts",null, 1);
            SQLiteDatabase db = helper.getReadableDatabase();
            String sql = "SELECT id, persona, razon, dinero from debts";
            Cursor c = db.rawQuery(sql,null);
            if(c.moveToFirst()){
                do{
                    String linea = c.getInt(0)+","+ c.getString(1)+"," + c.getString(2) + "," + c.getString(3);
                    datos.add(linea);
                }while (c.moveToNext());
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(this, "Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return datos; //Retorno mis datos
    }
}