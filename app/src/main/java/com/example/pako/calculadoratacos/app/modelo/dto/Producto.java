package com.example.pako.calculadoratacos.app.modelo.dto;

/**
 * Created by Pako on 24/05/2017.
 */

public class Producto {

    private Integer id;
    private String nombre;
    private Double costo;


    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(String nombre, Double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", costo=" + costo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;

        Producto producto = (Producto) o;

        return getId() != null ? getId().equals(producto.getId()) : producto.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    public boolean validarAll(){
        boolean regreso = true;
        if(id != null && id!= 0){
            regreso = false;
        }
        if(nombre != null && nombre!=""){
           regreso = false;
        }
        if(costo != null && costo != 0){
            regreso = false;
        }
        return regreso;
    }

    public boolean validarSinId(){
        boolean regreso = true;
        if(nombre == null || nombre ==""){
            regreso = false;
        }
        if(costo == null || costo == 0){
            regreso = false;
        }
        return regreso;
    }
}

