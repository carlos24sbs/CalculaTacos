package com.example.pako.calculadoratacos.app.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Pako on 03/05/2017.
 */

public class DaoCore extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "DBCALCULATACOS";
    protected static final int DATABASE_VERSION = 1;
    // Table Names
    protected static final String TABLE_PRODUCTO = "Producto";
    protected static final String TABLE_SERVICIO = "Servicio";
    protected static final String TABLE_USUARIO = "UsuarioDao";
    protected static final String TABLE_PRODUCTOSERVICIO = "ProductoServico";
    // Producto Table Columns
    protected static final String KEY_PRODUCTO_ID = "id";
    protected static final String KEY_PRODUCTO_NOMBRE = "nombre";
    protected static final String KEY_PRODUCTO_COSTO = "costo";
    // UsuarioDao Table Columns
    protected static final String KEY_USUARIO_ID = "id";
    protected static final String KEY_USUARIO_NOMBRE= "nombre";
    // Servicio Table Columns
    protected static final String KEY_SERVICIO_ID = "id";
    protected static final String KEY_SERVICIO_FECHAI = "fechaInicio";
    protected static final String KEY_SERVICIO_FECHAT = "fechaTermino";
    protected static final String KEY_SERVICIO_TOTAL = "total";
    protected static final String KEY_SERVICIO_USUARIO = "idUsuario";
    protected static final String KEY_SERVICIO_MESA = "idMesa";
    // ProductoServicio Table Columns
    protected static final String KEY_PRODUCTOSERVICIO_ID = "id";
    protected static final String KEY_PRODUCTOSERVICIO_CANTIDAD = "cantidad";
    protected static final String KEY_PRODUCTOSERVICIO_COSTO = "costo";
    protected static final String KEY_PRODUCTOSERVICIO_SERVICIO = "idServicio";
    protected static final String KEY_PRODUCTOSERVICIO_PRODUCTO = "idProducto";

    protected SQLiteDatabase db;
    protected String[] args;
    protected ContentValues contValores;

    public DaoCore(Context context) {//Constructor   ->Cuando se manda a llamar al constructor, la base de datos es creada
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Código que crea la tabla
        db.execSQL("create table " + TABLE_PRODUCTO + " ("
                + KEY_PRODUCTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCTO_NOMBRE + " TEXT,"
                + KEY_PRODUCTO_COSTO + " REAL)");
        db.execSQL("create table " + TABLE_SERVICIO + " ("
                + KEY_SERVICIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SERVICIO_USUARIO + " INTEGER,"
                + KEY_SERVICIO_MESA + " INTEGER,"
                + KEY_SERVICIO_FECHAI + " NUMBER,"
                + KEY_SERVICIO_FECHAT + " NUMBER,"
                +KEY_SERVICIO_TOTAL+ " REAL)");
        db.execSQL("create table " + TABLE_USUARIO + " ("
                + KEY_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USUARIO_NOMBRE + " TEXT)");
        db.execSQL("create table " + TABLE_PRODUCTOSERVICIO + " ("
                + KEY_PRODUCTOSERVICIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCTOSERVICIO_CANTIDAD + " INTEGER,"
                + KEY_PRODUCTOSERVICIO_COSTO + " REAL,"
                + KEY_PRODUCTOSERVICIO_PRODUCTO + " INTEGER,"
                + KEY_PRODUCTOSERVICIO_SERVICIO +" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTOSERVICIO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SERVICIO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USUARIO);
        onCreate(db);
    }



}
