package com.lrgcuadrado.mydebts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Modifier;

public class modificarDebts extends AppCompatActivity {
    EditText txtPersona, txtRazon, txtDinero;
    Button btnModificar, btnEliminar;
    int id;
    String persona;
    String razon;
    String dinero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_debts);

        Bundle b= getIntent().getExtras();
        if(b!=null){
            id = b.getInt("id");
            persona = b.getString("persona");
            razon = b.getString("razon");
            dinero = b.getString("dinero");
        }

        txtPersona = (EditText) findViewById(R.id.txtPersona);
        txtRazon = (EditText) findViewById(R.id.txtRazon);
        txtDinero = (EditText) findViewById(R.id.txtDinero);

        txtPersona.setText(persona);
        txtRazon.setText(razon);
        txtDinero.setText(dinero);

        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Traemos los nuevos datos
                modificar(id, txtPersona.getText().toString(), txtRazon.getText().toString(), txtDinero.getText().toString());
                onBackPressed();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(id);
                onBackPressed();
            }
        });
    }
    private void modificar(int id,String persona, String razon, String dinero){
        BaseHelper helper = new BaseHelper(this, "baseDebts", null,1);
        SQLiteDatabase db= helper.getWritableDatabase();

        try {
            String sql = "UPDATE debts SET persona='" + persona + "' ,razon='"+ razon + "', dinero='" + dinero +"' WHERE id=" + id;
            db.execSQL(sql);
            db.close();
        }catch (Exception e){
            Toast.makeText(this, "Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void eliminar(int id){
        BaseHelper helper = new BaseHelper(this, "baseDebts", null,1);
        SQLiteDatabase db= helper.getWritableDatabase();

        try {
            String sql = "DELETE FROM debts WHERE id= " + id;
            db.execSQL(sql);
            db.close();
        }catch (Exception e){
            Toast.makeText(this, "Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}