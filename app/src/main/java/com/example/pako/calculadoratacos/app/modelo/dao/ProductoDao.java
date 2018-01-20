package com.example.pako.calculadoratacos.app.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pako.calculadoratacos.app.modelo.dto.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeiden on 19/01/18.
 */

public class ProductoDao extends DaoCore {
    String[] campos = new String[] {KEY_PRODUCTO_ID, KEY_PRODUCTO_NOMBRE, KEY_PRODUCTO_COSTO};

    public ProductoDao(Context contex){
        super(contex);
    }

    public void persistir(Producto dto) {//Método para insertar datos
        SQLiteDatabase db = this.getWritableDatabase(); //Crea instancia de la base de datos para poder usarla
        ContentValues contentValues = new ContentValues();//Es un contenedor que te permite guardar los valores que vas a insertar en la tabla
        contentValues.put(KEY_PRODUCTO_NOMBRE,dto.getNombre());
        contentValues.put(KEY_PRODUCTO_COSTO,dto.getCosto());
        List<Producto> dtos = new ArrayList<>();
        if(dto.getId()!= null){
            String[] args = new String[] {dto.getId().toString()};
            dtos = obtener(db.query(TABLE_PRODUCTO, campos, KEY_PRODUCTO_ID+"=?", args, null, null, null));
        }
        if(dtos.isEmpty()) {
            String[] args = new String[]{dto.getNombre().toString()};
            dtos = obtener(db.query(TABLE_PRODUCTO, campos, KEY_PRODUCTO_NOMBRE + "=?", args, null, null, null));
        }
        if(dtos.isEmpty()){
            db.insert(TABLE_PRODUCTO,null,contentValues);
        }
        else{
            String[] args = new String[]{dto.getNombre().toString()};
            db.update(TABLE_PRODUCTO,contentValues,KEY_PRODUCTO_ID+"=?",args);
        }
    }

    public List<Producto> listar() {    //Método para mostrar los datos insertados
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        List<Producto> dtos = obtener(db.query(TABLE_PRODUCTO,campos,null,null, null, null, null, null));
        return dtos;
    }

    public Producto listarId(Integer id) {    //Método para mostrar los datos insertados
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        String[] args = new String[] {id.toString()};
        List<Producto> dtos = obtener(db.query(TABLE_PRODUCTO,campos,KEY_PRODUCTO_ID+"=?",args, null, null, null, "1"));
        Producto dto = null;
        if(!dtos.isEmpty()){
            dto = dtos.get(0);
        }
        return dto;
    }

    public Producto listarNombre(String nombre) {    //Método para mostrar los datos insertados
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        String[] args = new String[] {nombre.toString()};
        List<Producto> dtos = obtener(db.query(TABLE_PRODUCTO,campos,KEY_PRODUCTO_NOMBRE+"=?",args, null, null, null, "1"));
        Producto dto = null;
        if(!dtos.isEmpty()){
            dto = dtos.get(0);
        }
        return dto;
    }

    public void remover(Integer id){    //Funcion para Eliminar Datos
        SQLiteDatabase db = this.getWritableDatabase(); //Crea instancia de la base de datos para poder usarla
        String[] args = new String[] {id.toString()};
        db.delete(TABLE_PRODUCTO, KEY_PRODUCTO_ID+" = ?", args);
    }

    private List<Producto> obtener(Cursor res){
        List<Producto> dtos = new ArrayList<>();
        if (res.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Producto dto = new Producto();
                dto.setId(res.getInt(0));
                dto.setNombre(res.getString(1));
                dto.setCosto(res.getDouble(2));
                dtos.add(dto);
            } while(res.moveToNext());
        }
        return dtos;
    }
}
