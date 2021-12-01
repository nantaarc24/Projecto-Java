package com.sys.venta.capas.dao.mysql;

import com.sys.venta.capas.dao.Conexion;
import com.sys.venta.capas.dao.ICategoriaDAO;

import com.sys.venta.capas.dao.IClienteDAO;
import com.sys.venta.capas.dao.IDAOManager;
import com.sys.venta.capas.dao.IDetalleventaDAO;
import com.sys.venta.capas.dao.IUsuarioDAO;
import com.sys.venta.capas.dao.ILibroDAO;
import com.sys.venta.capas.dao.IVentaDAO;
import java.sql.Connection;

public class MySqlDAOManager implements IDAOManager{
    //Singleton - parte 1
    private static final MySqlDAOManager instancia = new MySqlDAOManager();
    private Connection cn;
    
    private MySqlDAOManager(){
        cn = new Conexion().getConn();
    }
    
    //Singleton - parte 2
    public static MySqlDAOManager getInstancia(){
        return instancia;
    }
    
    //Factory
    private IUsuarioDAO usuarioDao = null;
    private ILibroDAO libroDao = null;
    private ICategoriaDAO categoriaDao = null;
    private IClienteDAO clienteDao = null;
    private IVentaDAO ventaDao = null;
    private IDetalleventaDAO detalleventaDao = null;
  
 

    @Override
    public IUsuarioDAO getUsuarioDAO() {
         if(usuarioDao==null)
            usuarioDao = new MySqlUsuarioDAO(cn);
        return usuarioDao;
    }

    @Override
    public ILibroDAO getLibroDAO() {
         if(libroDao==null)
            libroDao = new MySqlLibroDAO(cn);
        return libroDao;
    }



    @Override
    public IClienteDAO getClienteDAO() {
         if(clienteDao==null)
            clienteDao = new MySqlClienteDAO(cn);
        return clienteDao;
    }

    @Override
    public IVentaDAO getVentaDAO() {
        if(ventaDao==null)
        ventaDao = new MySqlVentaDAO(cn);
        return ventaDao;
    }

    @Override
    public IDetalleventaDAO getDetalleventaDAO() {
        if(detalleventaDao==null)
        detalleventaDao = new MySqlDetalleventaDAO(cn);
        return detalleventaDao;
    }

    @Override
    public ICategoriaDAO getCategoriaDAO() {
        if(categoriaDao==null)
        categoriaDao = new MySqlCategoriaDAO(cn);
        return categoriaDao;        
    }


    
}
