/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;


import com.edu.sise.capas.entity.Carrera;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface ICarreraDAO extends GenericDAO<Carrera, Integer>{
    List<Carrera> obtenerBusqueda(String v1) throws DAOException;
    int[] cargaMasiva(List<Carrera> lista) throws DAOException;
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Carrera> paginacion(Integer posicion, Integer registros) throws DAOException;
    int getCount()throws DAOException;
}
