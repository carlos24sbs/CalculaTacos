package com.example.pako.calculadoratacos.app.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pako.calculadoratacos.app.modelo.dto.ProductoServicio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeiden on 22/01/18.
 */

public class ProductoServicioDao extends DaoCore {
    String[] campos = new String[] {KEY_PRODUCTOSERVICIO_ID, KEY_PRODUCTOSERVICIO_CANTIDAD, KEY_PRODUCTOSERVICIO_COSTO,KEY_PRODUCTOSERVICIO_PRODUCTO,KEY_PRODUCTOSERVICIO_SERVICIO};

    public ProductoServicioDao(Context cont){
        super(cont);
        db = this.getWritableDatabase();
    }

    public void insertar(Integer idProducto, double costo,Integer idServicio, int catnidad){
        contValores = new ContentValues();
        contValores.put(KEY_PRODUCTOSERVICIO_PRODUCTO,idProducto);
        contValores.put(KEY_PRODUCTOSERVICIO_COSTO, costo);
        contValores.put(KEY_PRODUCTOSERVICIO_SERVICIO, idServicio);
        contValores.put(KEY_PRODUCTOSERVICIO_CANTIDAD, catnidad);
        db.insert(TABLE_PRODUCTOSERVICIO,null,contValores);
    }

    public List<ProductoServicio> listar(Integer idProducto){
        db = this.getWritableDatabase(); //crea una instancia de la base de datos
        args = new String[] {idProducto.toString()};
        List<ProductoServicio> dtos = obtener(db.query(TABLE_PRODUCTOSERVICIO,campos,KEY_PRODUCTOSERVICIO_PRODUCTO+"=?",args, null, null, null,null));
        return dtos;
    }

    private List<ProductoServicio> obtener(Cursor res){
        List<ProductoServicio> dtos = new ArrayList<>();
        if (res.moveToFirst()) {
            do {//Recorremos el cursor hasta que no haya más registros
                ProductoServicio dto = new ProductoServicio();
                dto.setId(res.getInt(0));
                dto.setCantidad(res.getInt(1));
                dto.setCosto(res.getDouble(2));
                dto.setIdProducto(res.getInt(3));
                dto.setIdServicio(res.getInt(4));
                dtos.add(dto);
            } while(res.moveToNext());
        }
        return dtos;
    }
}
