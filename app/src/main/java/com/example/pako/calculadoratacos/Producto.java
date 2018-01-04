package com.example.pako.calculadoratacos;

/**
 * Created by Pako on 24/05/2017.
 */

public class Producto {

    public String nombre;
    public int cantidad;
    public double precio;

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
}
