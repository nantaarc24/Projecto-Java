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
public class UsuarioForm {
    
    private String idusuario;
    
    private String nombre;
    private String apellidos;
    private String dni;
    private String login;
    private String clave;
    private String rol;
    private String estado;

    public UsuarioForm() {
    }

    public UsuarioForm(String login, String clave) {
        this.login = login;
        this.clave = clave;
    }

    
    public UsuarioForm(String idusuario, String nombre, String apellidos, String dni, String login, String clave, String rol, String estado) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.login = login;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
