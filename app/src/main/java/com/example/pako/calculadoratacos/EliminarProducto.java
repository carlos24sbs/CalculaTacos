package com.example.pako.calculadoratacos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarProducto extends AppCompatActivity {

    DatabaseHelper myDb; //Acá se instancia la clase DatabaseHelper y se utiliza como MyDb

    // se declara la variables que tomaran el valor de los elementos del .XML

    Button btn_ver_eliminar;
    Button btn_eliminar_eliminar;
    EditText edit_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);
        myDb = new DatabaseHelper(this);

        //Se extraen los valores de el XML en las variables locales


        btn_ver_eliminar = (Button) findViewById(R.id.btn_ver_eliminar);
        btn_eliminar_eliminar = (Button) findViewById(R.id.btn_eliminar_eliminar);
        edit_eliminar = (EditText) findViewById(R.id.editText_id_eliminar);

        //Se instancian los métodos que se van a utilizar

        VerProductos();
        EliminarProducto();

    }

    //Método para ver todos los datos

    public void VerProductos() {
        btn_ver_eliminar.setOnClickListener(
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



    //Método para eliminar Datos

    public void EliminarProducto(){
        btn_eliminar_eliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idDelete = edit_eliminar.getText().toString();
                        if (idDelete.equals("")){
                            Toast.makeText(EliminarProducto.this, "Ingresa el ID del producto que deseas eliminar.", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Integer deletedRows = myDb.deleteData(edit_eliminar.getText().toString());
                            if (deletedRows > 0)
                                Toast.makeText(EliminarProducto.this, "Producto Eliminado Correctamente", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(EliminarProducto.this, "Producto no eliminado", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );

    }



}
