/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao;

import com.sys.venta.capas.entity.Categoria;

import java.util.List;

/**
 *
 * @author Carlos
 */
public interface ICategoriaDAO extends GenericDAO<Categoria, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Categoria> obtenerBusqueda(String valor) throws DAOException;
}
