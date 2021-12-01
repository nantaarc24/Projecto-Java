/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.entity;

import java.time.LocalDate;

/**
 *
 * @author jhonb
 */
public class Venta {
    private Integer idventa;
    private Integer idusuariofk;
    private Integer idclientefk;
    private LocalDate fecha;
    

    public Venta() {
    }

    public Venta(Integer idventa, LocalDate fecha) {
        this.idventa = idventa;
        this.fecha = fecha;
    }
    
    

    public Venta(Integer idventa, Integer idusuariofk, Integer idclientefk, LocalDate fecha) {
        this.idventa = idventa;
        this.idusuariofk = idusuariofk;
        this.idclientefk = idclientefk;
        this.fecha = fecha;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdusuariofk() {
        return idusuariofk;
    }

    public void setIdusuariofk(Integer idusuariofk) {
        this.idusuariofk = idusuariofk;
    }

    public Integer getIdclientefk() {
        return idclientefk;
    }

    public void setIdclientefk(Integer idclientefk) {
        this.idclientefk = idclientefk;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return getIdventa()+"";
    }

    
}
