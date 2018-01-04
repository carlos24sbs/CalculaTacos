package com.example.pako.calculadoratacos;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarProducto extends AppCompatActivity {
    DatabaseHelper myDb; //Acá se instancia la clase DatabaseHelper y se utiliza como MyDb

    // se declara la variables que tomaran el valor de los elementos del .XML
    EditText editNombre, editCosto;
    Button btnAgregar;
    Button btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        myDb = new DatabaseHelper(this);


        //Se extraen los valores de el XML en las variables locales

        editNombre = (EditText) findViewById(R.id.editText_nombre);
        editCosto = (EditText) findViewById(R.id.editText_costo);
        btnAgregar = (Button) findViewById(R.id.btn_agregar_agregar);
        btnVer = (Button) findViewById(R.id.btn_agregar_ver);

        //Se instancian los métodos que se van a utilizar

        Validar();
        VerProductos();

    }




    //Metodo para Validar
    public void Validar(){
        btnAgregar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String nombre = editNombre.getText().toString();
                        final String costo = editCosto.getText().toString();
                        final Cursor res = myDb.validar(nombre);//LLama a el método que esta el la clase DatabaseHelper



                        //Si faltan datos
                        if (nombre.equals("")||costo.equals("")){
                            //Mostrar un mensage
                            showMessage("ERROR", "Rellena todos los campos");
                            return;
                        }

                        //Si NO existe un producto con el mismo nombre
                        if (res.getCount() == 0) { //Si no hay datos
                            boolean isInserted = myDb.insertData(editNombre.getText().toString(), //Este método elaluará si se envian los parametros correctamente
                                    editCosto.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(AgregarProducto.this, "Producto Agregado", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(AgregarProducto.this, "Error al agregar", Toast.LENGTH_LONG).show();
                            editNombre.setText("");
                            editCosto.setText("");
                        }

                        //Si ya existe algún producto con el mismo nombre
                        else {
                            res.moveToFirst();
                            //Si el nombre y costo son iguales
                            if (res.getString(2).equals(costo)) {
                                res.moveToFirst();
                                //Mostrar un mensage
                                showMessage("Mensaje", "Ya existe el producto " + res.getString(1));
                                return;
                            }
                            //Si el nombre del producto es igual, pero con otro costo
                            else {
                                AlertDialog.Builder a_builder = new AlertDialog.Builder(AgregarProducto.this);//Manda a llamar a la clase de AlertDialog
                                a_builder.setMessage("¿Desea actualizar el costo?")//Crea un mensage
                                        .setCancelable(false)//No permite cerrar la ventana de dialogo amenos que opriman un mensaje
                                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {//Pone un boton qyue dice que si
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //Aquí se actualiza el producto
                                                res.moveToFirst();
                                                String id = res.getString(0);


                                                boolean isUpdate = myDb.updateData(id ,nombre,costo);
                                                if (isUpdate == true)
                                                    Toast.makeText(AgregarProducto.this, "Producto Actualizado", Toast.LENGTH_LONG).show();
                                                else
                                                    Toast.makeText(AgregarProducto.this, "Producto NO Actualizado", Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {//Pone un mensage que no
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                dialog.cancel();//cancela el AlertDialog
                                                //Toast.makeText(Tables.this,"Gracias por no cerrar este mensaje !! :D", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                AlertDialog alert = a_builder.create();
                                alert.setTitle("¡Ya existe el producto " + nombre + " pero con otro costo $"+ res.getString(2));//Pone un titulo al mensage
                                alert.show();
                            }
                        }

                    }
                }
        );

    }




    //Método para ver todos los datos

    public void VerProductos() {
        btnVer.setOnClickListener(
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


}
