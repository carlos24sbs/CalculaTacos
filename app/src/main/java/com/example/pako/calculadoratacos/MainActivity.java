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
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView grabar;
    TextView grabar2;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    DatabaseHelper myDb; //Acá se instancia la clase DatabaseHelper y se utiliza como MyDb

    // se declara la variables que tomaran el valor de los elementos del .XML

    Button btn_ir_productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);


        //Se extraen los valores de el XML en las variables locales

        grabar = (TextView) findViewById(R.id.txtGrabarVoz);
        grabar2=  (TextView) findViewById(R.id.textView);


        btn_ir_productos = (Button) findViewById(R.id.btn_ir_productos);

        //Se instancian los métodos que se van a utilizar

        AbrirProductos();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:
                //Si se reconoce el audio....
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);

                    String [ ] palabras = speech.get(0).toString().split(" ");//Corta el arreglo escuchado en palabras



                    grabar.setText(strSpeech2Text);//Pone el texto escuchado en el TextView
                    int a = 0;
                    for (int h=1; h<palabras.length; h+=2)
                    {
                        if (isNumeric(palabras[h]) == false)
                        {
                            Toast.makeText(MainActivity.this, "La cantidad del producto "+palabras[h-1]+" debe de ser un número", Toast.LENGTH_LONG).show();
                            a++;
                        }
                        else
                            break;
                    }



                    if (palabras.length == 0)//Si no se escuchó nada
                    {
                        Toast.makeText(MainActivity.this, "No escuche nada!!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, "La cantidad del producto "+palabras[h]+" debe de ser un número", Toast.LENGTH_LONG).show();
                    }


                    else if (palabras.length%2 != 0)//Si la sintaxis NO está completa
                    {
                        Toast.makeText(MainActivity.this, "Utiliza la sigiente sintaxis \n NombreProducto1 + CantidadProducto1 + NombreProducto2 + CantidadProducto2 + NombreProductoN + CantidadProductoN", Toast.LENGTH_LONG).show();
                    }

                    else if (a!=0)//Si la cantidad de un producto NO es un número
                    {
                        Toast.makeText(MainActivity.this, "La cantidad de producto debe de ser un numero", Toast.LENGTH_LONG).show();
                    }



                    else
                    {
                        ArrayList dto =  new ArrayList();
                        for(int i=0; i<palabras.length; i+=2){
                            Producto dd = new Producto();//Instancia un objeto de la clase producto para poderlo usar con dd
                            dd.nombre = palabras[i];//aquí le pone el valor de el producto i a la variable que está en la clase Producto
                            dd.cantidad = Integer.parseInt(palabras[i+1]);//Aquí se le pone la cantidad de producto a lavariable cantidad que está en la clase Producto
                            dd.nombre = dd.nombre.substring(0, 1).toUpperCase() + dd.nombre.substring(1);//Convierte la primera letra de producto en Mayuscula para irla a buscar a la BD
                            final Cursor res = myDb.validar(dd.nombre);//LLama a el método que esta el la clase DatabaseHelper
                            res.moveToFirst();
                            if (res.getCount()==0)//Si no existe el producto
                            {
                                Toast.makeText(MainActivity.this, "El producto "+dd.nombre+" no existe", Toast.LENGTH_LONG).show();
                                dd.precio = 0;//Se le pone en Precio el valor de 0 por defecto
                            }
                            else //Si existe el producto en la BD
                            {
                                dd.precio = res.getInt(2);//aquí se le manda el nombre a la clase DB para que regrese el costo y se le ponga a la variable Precio
                            }
                            dto.add(dd);//El Arrylist DTO se le agrega nombre, cantidad y precio en cada elemento
                        }

                        double total= 0.0;
                        for(int i =0; i<dto.size(); i++){
                            Producto dd = (Producto)dto.get(i);
                            total += dd.cantidad* dd.precio;
                            Toast.makeText(MainActivity.this, dd.nombre + " = " +dd.cantidad*dd.precio, Toast.LENGTH_LONG).show();

                        }





                        grabar2.setText("Total: " + String.valueOf(total));//Pone el texto escuchado en el TextView
                        //Toast.makeText(MainActivity.this, "Encontrado ", Toast.LENGTH_LONG).show();
                    }



                }

                break;
            default:

                break;
        }
    }

    //Método que se eecuta cuando se oprime la imagen del microfono
    public void onClickImgBtnHablar(View v) {

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }

    }


    //ABRIR PRODUCTOS
    public void AbrirProductos(){
        btn_ir_productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goProductos = new Intent(getApplicationContext(),Productos.class);
                startActivity(goProductos);
            }
        });

    }

    //VALIDAR SI EL COSTO ES NÚMERICO
    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


}
