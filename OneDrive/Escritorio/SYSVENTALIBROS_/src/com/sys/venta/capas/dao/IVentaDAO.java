
package com.sys.venta.capas.dao;

import com.sys.venta.capas.entity.Venta;
import java.util.List;


public interface IVentaDAO extends GenericDAO<Venta, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Venta> obtenerBusqueda(String valor) throws DAOException;
}
