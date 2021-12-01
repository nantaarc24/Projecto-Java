/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao;

import com.sys.venta.capas.entity.Usuario;
import java.util.List;

/**
 *
 * @author Fernando Tapia Arcos
 */
public interface IUsuarioDAO extends GenericDAO<Usuario, Integer>{
    
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Usuario> obtenerBusqueda(String v1) throws DAOException;
    public void validarAcceso(Usuario o) throws DAOException;
    
}
