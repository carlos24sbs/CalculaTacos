package com.example.pako.calculadoratacos.app.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pako.calculadoratacos.app.modelo.dto.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeiden on 19/01/18.
 */

public class UsuarioDao extends DaoCore {
    private String[] campos = new String[] {KEY_USUARIO_ID, KEY_USUARIO_NOMBRE};


    public UsuarioDao(Context cont){
        super(cont);
        db = this.getWritableDatabase(); //crea una instancia de la base de datos
    }

    public void persitir(Usuario dto){
        SQLiteDatabase db = this.getWritableDatabase(); //Crea instancia de la base de datos para poder usarla
        ContentValues contentValues = new ContentValues();//Es un contenedor que te permite guardar los valores que vas a insertar en la tabla
        contentValues.put(KEY_USUARIO_NOMBRE,dto.getNombre());
        db.insert(TABLE_USUARIO,null,contentValues);
    }

    public Usuario listarId(Integer id){
        SQLiteDatabase db = this.getWritableDatabase(); //crea una instancia de la base de datos
        String[] args = new String[] {id.toString()};
        List<Usuario> dtos = obtener(db.query(TABLE_USUARIO,campos,KEY_USUARIO_ID+"=?",args, null, null, null, "1"));
        Usuario dto = null;
        if(!dtos.isEmpty()){
            dto = dtos.get(0);
        }
        return dto;
    }

    private List<Usuario> obtener(Cursor res){
        List<Usuario> dtos = new ArrayList<>();
        if (res.moveToFirst()) {
            do {          //Recorremos el cursor hasta que no haya más registros
                Usuario dto = new Usuario();
                dto.setId(res.getInt(0));
                dto.setNombre(res.getString(1));
                dtos.add(dto);
            } while(res.moveToNext());
        }
        return dtos;
    }
}
