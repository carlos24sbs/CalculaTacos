package com.example.pako.calculadoratacos.app.modelo.dto;

/**
 * Created by yeiden on 16/01/18.
 */

public class Usuario {
    private int id;
    private String nombre;

    public Usuario() {

    }

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (getId() != usuario.getId()) return false;
        return getNombre() != null ? getNombre().equals(usuario.getNombre()) : usuario.getNombre() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

