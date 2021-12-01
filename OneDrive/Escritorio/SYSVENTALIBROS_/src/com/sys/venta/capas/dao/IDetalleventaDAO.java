
package com.sys.venta.capas.dao;

import com.sys.venta.capas.entity.Detalleventa;
import java.util.List;


public interface IDetalleventaDAO extends GenericDAO<Detalleventa, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Detalleventa> obtenerBusqueda(String valor) throws DAOException;
}
