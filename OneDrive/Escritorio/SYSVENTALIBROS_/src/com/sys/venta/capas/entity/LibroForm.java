/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.entity;

/**
 *
 * @author Fernando Tapia Arcos
 */
public class LibroForm {
    
    private String idlibro;
    private String nombre;
    private String autor;
    private String descripcion;
    private String stock;
    private String precio;


    public LibroForm() {
    }

    public LibroForm(String idlibro, String nombre, String autor, String descripcion, String stock, String precio) {
        this.idlibro = idlibro;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public String getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(String idlibro) {
        this.idlibro = idlibro;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    
}
