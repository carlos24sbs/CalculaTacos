package com.example.pako.calculadoratacos.app.modelo.dto;

import java.util.Date;

/**
 * Created by yeiden on 16/01/18.
 */

public class Servicio {
    private int id;
    private Integer idUsuario;
    private Integer idMesa;
    private Double total;
    private Date fechaInicio;
    private Date fechaTermino;

    public Servicio() {
    }

    public Servicio(int id, Integer idUsuario, Integer idMesa, Double total, Date fechaInicio, Date fechaTermino) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.total = total;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idMesa=" + idMesa +
                ", total=" + total +
                ", fechaInicio=" + fechaInicio +
                ", fechaTermino=" + fechaTermino +
                '}';
    }
}
