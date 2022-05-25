package com.lrgcuadrado.mydebts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class listadoDebts extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_debts);

        listView = (ListView) findViewById(R.id.ListView);
        CargarListado();
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
                    String linea = c.getInt(0)+" "+ c.getString(1)+" " + c.getString(2) + c.getString(3);
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