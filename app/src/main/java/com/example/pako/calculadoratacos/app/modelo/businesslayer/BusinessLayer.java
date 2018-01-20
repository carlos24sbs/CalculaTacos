package com.example.pako.calculadoratacos.app.modelo.businesslayer;

import android.content.Context;

import com.example.pako.calculadoratacos.app.modelo.dao.ProductoDao;
import com.example.pako.calculadoratacos.app.modelo.dto.Producto;
import java.util.List;

/**
 * Created by yeiden on 19/01/18.
 */

public class BusinessLayer {
    private Context cont;

    public BusinessLayer(Context cont){
        this.cont = cont;
    }

    public void ProductoPersistir(Producto dto){
        ProductoDao dao = new ProductoDao(cont);
        dao.persistir(dto);
    }

    public List<Producto> ProductoListar(){
        ProductoDao dao = new ProductoDao(cont);
        List<Producto> dtos = dao.listar();
        return  dtos;
    }

    public Producto ProductoListarId(Integer id){
        ProductoDao dao = new ProductoDao(cont);
        Producto dto = dao.listarId(id);
        return  dto;
    }

    public Producto ProductoListarNombre(String nombre){
        ProductoDao dao = new ProductoDao(cont);
        Producto dto = dao.listarNombre(nombre);
        return  dto;
    }

    public void ProductoEliminar(Integer id){
        ProductoDao dao = new ProductoDao(cont);
        dao.remover(id);
    }
}
