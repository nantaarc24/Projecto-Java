
package com.sys.venta.capas.dao;


public interface IDAOManager {
    IUsuarioDAO getUsuarioDAO();
    ILibroDAO getLibroDAO();
    ICategoriaDAO getCategoriaDAO();
    IClienteDAO getClienteDAO();
    IVentaDAO getVentaDAO();
    IDetalleventaDAO getDetalleventaDAO();
       
}
