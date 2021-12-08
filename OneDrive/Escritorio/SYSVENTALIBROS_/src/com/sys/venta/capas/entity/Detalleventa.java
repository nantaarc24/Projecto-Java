/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.entity;

/**
 *
 * @author jhonb
 */
public class Detalleventa {
    
    private Integer idventafk;
    private Integer idlibrofk;
    private Integer cantidad;
    private Double monto;

    public Detalleventa() {
    }

    public Detalleventa( Integer idventafk, Integer idlibrofk, Integer cantidad, Double monto) {
       
        this.idventafk = idventafk;
        this.idlibrofk = idlibrofk;
        this.cantidad = cantidad;
        this.monto = monto;
    }

   

  

    public Integer getIdventafk() {
        return idventafk;
    }

    public void setIdventafk(Integer idventafk) {
        this.idventafk = idventafk;
    }

    public Integer getIdlibrofk() {
        return idlibrofk;
    }

    public void setIdlibrofk(Integer idlibrofk) {
        this.idlibrofk = idlibrofk;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    
}
