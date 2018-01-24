package com.example.pako.calculadoratacos.app.modelo.dto;

/**
 * Created by yeiden on 16/01/18.
 */

public class ProductoServicio {
    private Integer id;
    private Integer cantidad;
    private Double costo;
    private Integer idProducto;
    private Integer idServicio;

    public ProductoServicio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoServicio)) return false;

        ProductoServicio that = (ProductoServicio) o;

        if (getId() != that.getId()) return false;
        if (getCantidad() != that.getCantidad()) return false;
        if (getCosto() != null ? !getCosto().equals(that.getCosto()) : that.getCosto() != null)
            return false;
        if (getIdProducto() != null ? !getIdProducto().equals(that.getIdProducto()) : that.getIdProducto() != null)
            return false;
        return getIdServicio() != null ? getIdServicio().equals(that.getIdServicio()) : that.getIdServicio() == null;
    }

    @Override
    public String toString() {
        return "ProductoServicio{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", costo=" + costo +
                ", idProducto=" + idProducto +
                ", idServicio=" + idServicio +
                '}';
    }
}
