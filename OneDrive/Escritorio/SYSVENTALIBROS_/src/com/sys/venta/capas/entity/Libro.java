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
public class Libro {
    private Integer idlibro;
    private Integer idcategoriafk;
    private String categoria;
    private String nombre;
    private String autor;
    private String descripcion;
    private Integer stock;
    private Double precio;

    public Libro() {
    }

    public Libro(Integer idlibro, Integer idcategoriafk, String categoria, String nombre, String autor, String descripcion, Integer stock, Double precio) {
        this.idlibro = idlibro;
        this.idcategoriafk = idcategoriafk;
        this.categoria = categoria;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public Libro(Integer idlibro, Integer idcategoriafk, String nombre, String autor, String descripcion, Integer stock, Double precio) {
        this.idlibro = idlibro;
        this.idcategoriafk = idcategoriafk;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public Integer getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(Integer idlibro) {
        this.idlibro = idlibro;
    }

    public Integer getIdcategoriafk() {
        return idcategoriafk;
    }

    public void setIdcategoriafk(Integer idcategoriafk) {
        this.idcategoriafk = idcategoriafk;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    
    @Override
    public String toString() {
        return getIdlibro()+ " - " + getNombre();
    }

    
}
