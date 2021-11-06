/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;


import com.edu.sise.capas.entity.Producto;
import java.util.List;

/**
 *
 * @author Fernando Tapia Arcos
 */
public interface IEProductoDAO extends GenericDAO<Producto, Integer>{
    List<Producto> obtenerBusqueda(String v1) throws DAOException;
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Producto> paginacion(Integer posicion, Integer registros) throws DAOException;
    int getCount()throws DAOException;
}
