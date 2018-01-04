package com.example.pako.calculadoratacos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Productos extends AppCompatActivity {
    DatabaseHelper myDb; //Acá se instancia la clase DatabaseHelper y se utiliza como MyDb
    Button btn_ir_Ver;
    Button btn_ir_Agregar;
    Button btn_ir_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        myDb = new DatabaseHelper(this);

        btn_ir_Ver = (Button) findViewById(R.id.btn_ir_verProducto);
        btn_ir_Agregar = (Button) findViewById(R.id.btn_ir_agregarProducto);
        btn_ir_Eliminar = (Button) findViewById(R.id.btn_ir_eliminarProducto);
        AbrirAgregarProducto();
        AbrirEliminarProducto();
        verProductos();

    }

    //Método para ver todos los datos

    public void verProductos() {
        btn_ir_Ver.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();//LLama a el método que esta el la clase DatabaseHelper
                        if (res.getCount() == 0) { //Si no hay datos
                            //Mostrar un mensage
                            showMessage("Error", "No existe algún producto");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer(); //A ESTO NO LE ENTIENDO XD
                        while (res.moveToNext()) {//Mientras se mueva al siguiente elemento
                            buffer.append("Id :" + res.getString(0) + "\n"); //Va a obtener datos de la columna 0 (Id)
                            buffer.append("Nombre :" + res.getString(1) + "\n"); //Va a obtener datos de la columna 0 (Name)
                            buffer.append("Costo :" + res.getString(2) + "\n\n"); //Va a obtener datos de la columna 0 (Surname)
                        }

                        //Show all Data

                        showMessage("Productos", buffer.toString());

                    }
                }
        );
    }

    //Método para mostrar un mensage
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    //ABRIR AGREGAR
    public void AbrirAgregarProducto(){
        btn_ir_Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegistrar = new Intent(getApplicationContext(),AgregarProducto.class);
                startActivity(goRegistrar);
            }
        });

    }

    //ABRIR ELIMINAR
    public void AbrirEliminarProducto(){
        btn_ir_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegistrar = new Intent(getApplicationContext(),EliminarProducto.class);
                startActivity(goRegistrar);
            }
        });

    }
}
