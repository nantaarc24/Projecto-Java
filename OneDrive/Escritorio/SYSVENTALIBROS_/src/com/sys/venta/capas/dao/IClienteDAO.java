/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao;

import com.sys.venta.capas.entity.Cliente;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IClienteDAO extends GenericDAO<Cliente, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Cliente> obtenerBusqueda(String valor) throws DAOException;
//    Cliente obtenerCliente  (String valor1) throws DAOException;
}
