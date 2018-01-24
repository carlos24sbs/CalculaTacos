package com.example.pako.calculadoratacos.app.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pako.calculadoratacos.app.modelo.dto.Servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yeiden on 19/01/18.
 */

public class ServicioDao extends DaoCore {
    String[] campos = new String[] {KEY_SERVICIO_ID, KEY_SERVICIO_USUARIO, KEY_SERVICIO_MESA, KEY_SERVICIO_TOTAL,KEY_SERVICIO_FECHAI, KEY_SERVICIO_FECHAT};

    public ServicioDao(Context cont){
        super(cont);
        db = this.getWritableDatabase();
    }

    public Servicio activarMesa(Integer idUsuario, Integer idMesa){
        Servicio dto = new Servicio();
        args = new String[] {idMesa.toString()};

        List<Servicio> dtos = obtener(db.query(TABLE_SERVICIO,campos,KEY_PRODUCTO_NOMBRE+"=? AND fechaTermino is null",args, null, null, null,KEY_SERVICIO_ID + " DESC"));
        switch(dtos.size()){
            case 0:
                contValores = new ContentValues();//Es un contenedor que te permite guardar los valores que vas a insertar en la tabla
                contValores.put(KEY_SERVICIO_MESA,idMesa);
                contValores.put(KEY_SERVICIO_USUARIO,idUsuario);
                contValores.put(KEY_SERVICIO_TOTAL,0);
                contValores.put(KEY_SERVICIO_FECHAI,new Date().getTime());
                db.insert(TABLE_SERVICIO,null,contValores);
                activarMesa(idUsuario,idMesa);
                break;
            case 1:
                dto = dtos.get(0);
                break;
            default:
                dto = dtos.get(0);
                boolean control = false;
                for (Servicio dd:dtos) {
                    if(control == true){
                        args = new String[] {dto.getId().toString()};
                        contValores = new ContentValues();//Es un contenedor que te permite guardar los valores que vas a insertar en la tabla
                        contValores.put(KEY_SERVICIO_FECHAT,new Date().getTime());
                        db.update(TABLE_SERVICIO,contValores,KEY_SERVICIO_ID+"=?",args);
                    }else{
                        control = true;
                    }
                }
                break;
        }
        return dto;
    }

    public void terminarMesa(Integer id){
        args = new String[] {id.toString()};
        contValores = new ContentValues();//Es un contenedor que te permite guardar los valores que vas a insertar en la tabla
        contValores.put(KEY_SERVICIO_FECHAT,new Date().getTime());
        db.update(TABLE_SERVICIO,contValores,KEY_SERVICIO_ID+"=?",args);
    }

    private List<Servicio> obtener(Cursor res){
        List<Servicio> dtos = new ArrayList<>();
        if (res.moveToFirst()) {
            do {          //Recorremos el cursor hasta que no haya más registros
                Servicio dto = new Servicio();
                dto.setId(res.getInt(0));
                dto.setIdUsuario(res.getInt(1));
                dto.setIdMesa(res.getInt(2));
                dto.setTotal(res.getDouble(3));
                dto.setFechaInicio(new Date(res.getLong(4)));
                dto.setFechaTermino(new Date(res.getLong(5)));
                dtos.add(dto);
            } while(res.moveToNext());
        }
        return dtos;
    }
}
