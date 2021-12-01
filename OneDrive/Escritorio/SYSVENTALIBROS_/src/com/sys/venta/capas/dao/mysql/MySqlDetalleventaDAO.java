/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao.mysql;

import com.sys.venta.capas.dao.DAOException;
import  com.sys.venta.capas.dao.IDetalleventaDAO;
import  com.sys.venta.capas.entity.Detalleventa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class MySqlDetalleventaDAO implements IDetalleventaDAO{
    
    final String GETALL = "{call pa_listar_detalle_venta()}";
    final String COLUMNAS = "{call pa_columnas_detalle_venta()}";
    final String INSERT = "{call ppa_insertar_detalle_venta(?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_detalle_venta(?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_detalle_venta(?)}";
    final String BUSQUEDA = "{call pa_buscar_detalleVenta_id(?)}";

    private Connection cn;

    public MySqlDetalleventaDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Detalleventa o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(INSERT);
            int i = 1;
            
            cs.setInt(i++,o.getIdventafk());
            cs.setInt(i++,o.getIdlibrofk());
            cs.setInt(i++,o.getCantidad());
            cs.setDouble(i++,o.getMonto());
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar el registro!!!");
               
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void modificar(Detalleventa o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(UPDATE);
            int i = 1;             
            
            cs.setInt(i++,o.getIdlibrofk());
            cs.setInt(i++,o.getCantidad());
            cs.setDouble(i++,o.getMonto());
            cs.setInt(i++,o.getIdventafk());
          
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar la modificación del registro!!!");
               
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void eliminar(Detalleventa o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getIdventafk());
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar la eliminación del registro!!!");
               
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public List<Detalleventa> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Detalleventa> lista = new ArrayList<>();
        try {
            cs = cn.prepareCall(GETALL);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return lista;
    }

    @Override
    public Detalleventa obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Detalleventa getRS(ResultSet rs) throws SQLException{
        return new Detalleventa(                
                rs.getInt("idventafk"),
                rs.getInt("idlibrofk"),
                rs.getInt("cantidad"),
                rs.getDouble("monto")
            );
    }

    @Override
    public List<String> obtenerNombresColumnas() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<String> listaNomColumn = new ArrayList<>();
        
        try {
            cs = cn.prepareCall(COLUMNAS);
            rs = cs.executeQuery();
            rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount();i++)
                listaNomColumn.add(rsmd.getColumnName(i));
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return listaNomColumn;
    }

    @Override
    public List<Detalleventa> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Detalleventa> lista = new ArrayList<>();
        try {
            cs = cn.prepareCall(BUSQUEDA);
            int i=1;
            cs.setString(i++, valor);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return lista;
    }
    
}
