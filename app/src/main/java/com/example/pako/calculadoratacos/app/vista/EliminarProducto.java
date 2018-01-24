package com.example.pako.calculadoratacos.app.vista;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pako.calculadoratacos.R;
import com.example.pako.calculadoratacos.app.modelo.businesslayer.BusinessLayer;
import com.example.pako.calculadoratacos.app.modelo.dto.Producto;

import java.util.List;


public class EliminarProducto extends AppCompatActivity {

     BusinessLayer myDb; //Acá se instancia la clase DatabaseHelper y se utiliza como MyDb

    // se declara la variables que tomaran el valor de los elementos del .XML

    Button btn_ver_eliminar;
    Button btn_eliminar_eliminar;
    EditText edit_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);
        myDb = new BusinessLayer(this);

        //Se extraen los valores de el XML en las variables locales


        btn_ver_eliminar = (Button) findViewById(R.id.btn_ver_eliminar);
        btn_eliminar_eliminar = (Button) findViewById(R.id.btn_eliminar_eliminar);
        edit_eliminar = (EditText) findViewById(R.id.editText_id_eliminar);

        //Se instancian los métodos que se van a utilizar

        VerProductos();
        EliminarProducto();

    }

    public void VerProductos() {    //Método para ver todos los datos
        btn_ver_eliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Producto> dtos = myDb.ProductoListar();//LLama a el método que esta el la clase DatabaseHelper
                        if (dtos.isEmpty()) { //Si no hay datos
                            showMessage("Error", "No existe algún producto");                            //Mostrar un mensage
                            return;
                        }
                        StringBuffer buffer = new StringBuffer(); //A ESTO NO LE ENTIENDO XD
                        for(Producto dto : dtos){
                            buffer.append("Id :" + dto.getId() + "\n"); //Va a obtener datos de la columna 0 (Id)
                            buffer.append("Nombre :" + dto.getNombre() + "\n"); //Va a obtener datos de la columna 0 (Name)
                            buffer.append("Costo :" + dto.getCosto() + "\n\n"); //Va a obtener datos de la columna 0 (Surname)
                        }
                        showMessage("Productos", buffer.toString());                        //Show all Data
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {//Método para mostrar un mensage
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void EliminarProducto(){    //Método para eliminar Datos
        btn_eliminar_eliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idDelete = edit_eliminar.getText().toString();
                        if (idDelete.equals("")){
                            Toast.makeText(EliminarProducto.this, "Ingresa el ID del producto que deseas eliminar.", Toast.LENGTH_LONG).show();
                        }
                        else {
                            try{
                                myDb.ProductoEliminar(Integer.parseInt(idDelete));
                                Toast.makeText(EliminarProducto.this, "Producto Eliminado Correctamente", Toast.LENGTH_LONG).show();
                            }catch (Exception ex){
                                Toast.makeText(EliminarProducto.this, "Producto no eliminado", Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                }
        );

    }



}
