package com.lrgcuadrado.mydebts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 22:24
    EditText txtPersona, txtRazon, txtDinero;
    Button btnAgregar, btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPersona = (EditText) findViewById(R.id.txtPersona);
        txtRazon = (EditText) findViewById(R.id.txtRazon);
        txtDinero = (EditText) findViewById(R.id.txtDinero);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(txtPersona.getText().toString(), txtRazon.getText().toString(), txtDinero.getText().toString());
            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, listadoDebts.class));
            }
        });
    }

    private void guardar(String persona, String razon, String dinero){
        BaseHelper helper = new BaseHelper(this, "baseDebts", null,1);
        SQLiteDatabase db= helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("persona",persona);
            c.put("razon",razon);
            c.put("dinero",dinero);
            db.insert("debts",null,c);
            db.close();
            Toast.makeText(this,"Deuda registrada", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}