package com.example.pako.calculadoratacos.app.vista;

import android.content.DialogInterface;
import android.database.Cursor;
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


public class AgregarProducto extends AppCompatActivity {
    BusinessLayer myDb; //Acá se instancia la clase DatabaseHelper y se utiliza como MyDb

    // se declara la variables que tomaran el valor de los elementos del .XML
    EditText editNombre, editCosto;
    Button btnAgregar;
    Button btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        myDb = new BusinessLayer(this);


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
                        Producto dto = new Producto();
                        dto.setNombre(editNombre.getText().toString());
                        dto.setCosto(Double.parseDouble(editCosto.getText().toString()));
                        if (dto.validarSinId()) {
                            myDb.ProductoPersistir(dto);
                            editNombre.setText("");
                            editCosto.setText("");
                        } else {
                            showMessage("ERROR", "Rellena todos los campos"+ dto.getCosto());
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
                        List<Producto> dtos  = myDb.ProductoListar();//LLama a el método que esta el la clase DatabaseHelper
                        if (dtos.isEmpty()) { //Si no hay datos
                            //Mostrar un mensage
                            showMessage("Error", "No existe algún producto");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer(); //A ESTO NO LE ENTIENDO XD
                        for(Producto dto : dtos){
                            buffer.append("Id :" + dto.getId() + "\n"); //Va a obtener datos de la columna 0 (Id)
                            buffer.append("Nombre :" + dto.getNombre() + "\n"); //Va a obtener datos de la columna 0 (Name)
                            buffer.append("Costo :" + dto.getCosto() + "\n\n"); //Va a obtener datos de la columna 0 (Surname)
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
