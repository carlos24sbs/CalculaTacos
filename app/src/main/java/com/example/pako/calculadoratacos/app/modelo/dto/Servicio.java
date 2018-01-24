package com.example.pako.calculadoratacos.app.modelo.dto;

import java.util.Date;

/**
 * Created by yeiden on 16/01/18.
 */

public class Servicio {
    private Integer id;
    private Integer idUsuario;
    private Integer idMesa;
    private Double total;
    private Date fechaInicio;
    private Date fechaTermino;

    public Servicio() {
    }

    public Servicio(Integer id, Integer idUsuario, Integer idMesa, Double total, Date fechaInicio, Date fechaTermino) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.total = total;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public long getFechaInicio_long() {
        return fechaInicio.getTime();
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicio(long fechaInicio) {
        this.fechaInicio = new Date(fechaInicio);
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public long getFechaTermino_long() {
        return fechaTermino.getTime();
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public void setFechaTermino(long fechaTermino) {
        this.fechaTermino = new Date(fechaTermino);
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
