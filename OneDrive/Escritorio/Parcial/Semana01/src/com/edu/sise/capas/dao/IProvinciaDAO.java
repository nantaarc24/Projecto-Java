/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;


import com.edu.sise.capas.entity.Provincia;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IProvinciaDAO extends GenericDAO<Provincia, Integer>{
    List<Provincia> obtenerBusqueda(String v1) throws DAOException;
    int[] cargaMasiva(List<Provincia> lista) throws Exception;
    List<String> obtenerNombresColumnas() throws Exception;
    List<Provincia> paginacion(Integer posicion, Integer registros) throws Exception;
    int getCount() throws Exception;
}
