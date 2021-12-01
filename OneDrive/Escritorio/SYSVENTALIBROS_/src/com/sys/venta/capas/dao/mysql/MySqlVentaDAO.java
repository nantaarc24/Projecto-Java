/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao.mysql;

import com.sys.venta.capas.dao.DAOException;
import com.sys.venta.capas.dao.IVentaDAO;
import com.sys.venta.capas.entity.Venta;
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
public class MySqlVentaDAO implements IVentaDAO{
    
    final String GETALL = "{call pa_listar_venta()}";
    final String COLUMNAS = "SELECT * FROM Venta LIMIT 0";
    final String INSERT = "{call pa_insertar_venta(?,?,?)}";
    final String UPDATE = "{call pa_modificar_venta(?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_venta(?)}";
    final String BUSQUEDA = "{call pa_buscar_venta(?)}";

    private Connection cn;

    public MySqlVentaDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Venta o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(INSERT);
            int i = 1;
            
            cs.setInt(i++,o.getIdusuariofk());
            cs.setInt(i++,o.getIdclientefk());           
            cs.setDate(i++, Date.valueOf(o.getFecha())); 
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
    public void modificar(Venta o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(UPDATE);
            int i = 1;
            cs.setDate(i++, Date.valueOf(o.getFecha()));  
            cs.setInt(i++,o.getIdclientefk());
            cs.setInt(i++,o.getIdusuariofk());
            
            
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
    public void eliminar(Venta o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getIdventa());
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
    public List<Venta> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Venta> lista = new ArrayList<>();
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
    public Venta obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Venta getRS(ResultSet rs) throws SQLException{
        return new Venta(
                rs.getInt("idventa"),
                rs.getInt("idusuariofk"),
                rs.getInt("idclientefk"),              
                rs.getDate("fecha").toLocalDate()
             
                
            );
    }

    @Override
    public List<String> obtenerNombresColumnas() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<String> listaNomColumn = new ArrayList<>();
        
        try {
            ps = cn.prepareStatement(COLUMNAS);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount();i++)
                listaNomColumn.add(rsmd.getColumnName(i));
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return listaNomColumn;
    }

    @Override
    public List<Venta> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Venta> lista = new ArrayList<>();
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
