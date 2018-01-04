package com.example.pako.calculadoratacos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Pako on 03/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Calculadora.db";//Declarar variable para el nombre de la BASE DE DATOS

    public static final String TABLE_NAME = "calculadora_table";//Declarar variable para el nombre de la TABLA
    //Declarar variables para el nombre para las COLUMNAS DE CADA UNA DE LAS TABLAS
    public static final String COL_1 = "idProducto";
    public static final String COL_2 = "nombreProducto";
    public static final String COL_3 = "costo";





    //Constructor   ->Cuando se manda a llamar al constructor, la base de datos es creada
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Código que crea la tabla
        db.execSQL("create table " + TABLE_NAME +" (idProducto INTEGER PRIMARY KEY AUTOINCREMENT,nombreProducto TEXT,costo REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //Método para insertar datos

    public boolean insertData(String nombre,String costo) {
        SQLiteDatabase db = this.getWritableDatabase(); //Crea instancia de la base de datos para poder usarla
        ContentValues contentValues = new ContentValues();//NO SÉ PAR QUE ES EL CONTENT VALUES
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,costo);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;//Si envía este valor no se insertaron correctamente los datos
        else
            return true;//Si envía este valor se han insertado correctamente los datos
    }



    //Método para mostrar los datos insertados

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null); //Lo unico que hace es obtener todos los datos de la tabla
        return res;

    }


    //Funcion para Eliminar Datos
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase(); //Crea instancia de la base de datos para poder usarla
        return db.delete(TABLE_NAME, "idProducto = ?", new String[]{id});
    }

    //Método para mostrar los datos insertados

    public Cursor validar(String nombreProducto) {
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        Cursor res = db.rawQuery(" SELECT * FROM calculadora_table WHERE nombreProducto='"+nombreProducto+"' ", null);
        return res;

    }

    //Método para regresar el costo de un producto

    public Cursor regresarCosto(String nombreProducto) {
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        Cursor res = db.rawQuery(" SELECT * FROM calculadora_table WHERE nombreProducto='"+nombreProducto+"' ", null);
        return res;

    }

    //Método para Actualizar los datos
    public boolean updateData (String id,String nombre,String costo){
        SQLiteDatabase db = this.getWritableDatabase(); //Crea instancia de la base de datos para poder usarla
        ContentValues contentValues = new ContentValues();//NO SÉ PAR QUE ES EL CONTENT VALUES
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,costo);


        db.update(TABLE_NAME, contentValues, "idProducto = ?", new  String[] { id });//NO SÉ PARA QUE ES ESTA LÍNEA
        return true;
    }

}
