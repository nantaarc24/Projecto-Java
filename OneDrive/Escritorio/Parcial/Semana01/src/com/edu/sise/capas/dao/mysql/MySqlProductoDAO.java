/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IEProductoDAO;
import com.edu.sise.capas.entity.Empleado;
import com.edu.sise.capas.entity.Producto;
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
 * @author Fernando Tapia Arcos
 */
public class MySqlProductoDAO implements IEProductoDAO{

    final String INSERT = "INSERT INTO productos(nombre, descripcion, modelo, stock, precio, estado) "
            + " VALUES(?, ?, ?, ?, ?, ?)";
    final String GETALL = "SELECT * FROM productos";
    final String UPDATE=  "UPDATE productos "
            + " SET nombre = ?, descripcion = ?, modelo = ?, stock = ? , precio = ?, estado = ? "
            + " WHERE id_producto = ?";
    final String BUSQUEDA = "SELECT * FROM productos "
            + " WHERE nombre LIKE ? || descripcion LIKE ? ";
    final String DELETE = "DELETE FROM productos WHERE id_producto = ?";
    final String COLUMNAS = "SELECT * FROM productos LIMIT 0";
    final String PAGINACION = "SELECT * FROM productos LIMIT ?,? ";
    final String COUNT = "SELECT count(*) FROM productos";
    
    private Connection cn;
    
    public  MySqlProductoDAO(Connection cn){
        this.cn=cn;
    }
    
    @Override
    public List<Producto> obtenerBusqueda(String v1) throws DAOException {
         PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<>();
        String valor ="%"+v1+"%";
        try {
            ps = cn.prepareStatement(BUSQUEDA);
            int i =1;
            ps.setString(i++, valor);
            ps.setString(i++, valor);
            
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
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
        return lista;
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
    public List<Producto> paginacion(Integer posicion, Integer registros) throws DAOException {
        PreparedStatement ps =  null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<>();
        try{
            ps = cn.prepareStatement(PAGINACION);
            int i =1;
            ps.setInt(i++, posicion);
            ps.setInt(i++, registros);
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
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
        return lista;
    }

    @Override
    public int getCount() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cantidad = 0;
        try {
            ps = cn.prepareStatement(COUNT);
            rs = ps.executeQuery();
            if(rs.next())
                cantidad = rs.getInt(1);
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
        return cantidad;
    }

    @Override
    public void insertar(Producto o) throws DAOException {
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = cn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++,o.getNombre());
            ps.setString(i++,o.getDescripcion());
            ps.setString(i++,o.getModelo());
            ps.setInt(i++,o.getStock());
            ps.setDouble(i++, o.getPrecio());
            ps.setInt(i++, o.getEstado());
            
            if(ps.executeUpdate()==0)
                throw new DAOException("No se pudo realizar el registro!!!");
            
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                o.setId_producto(rs.getInt(1));
            }else{
                throw new DAOException("No se puede generar el ID del producto");
            }    
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
    }

    @Override
    public void modificar(Producto o) throws DAOException {
        PreparedStatement ps = null;
        
        try{
            ps = cn.prepareStatement(UPDATE);
            int i = 1;
            ps.setString(i++,o.getNombre());
            ps.setString(i++,o.getDescripcion());
            ps.setString(i++,o.getModelo());
            ps.setInt(i++,o.getStock());
            ps.setDouble(i++, o.getPrecio());
            ps.setInt(i++, o.getEstado());
            ps.setInt(i++,o.getId_producto());
            
            if(ps.executeUpdate()==0)
                throw new DAOException("No se pudo modificar el registro!!!");
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(ps!=null) ps.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void eliminar(Producto o) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = cn.prepareStatement(DELETE);
            int i = 1;
            ps.setInt(i++, o.getId_producto());
            if(ps.executeUpdate()==0)
                throw new DAOException("No se pudo eliminar el producto!!!");
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(ps!=null) ps.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public List<Producto> obtenerTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<>();
        try {
            ps = cn.prepareStatement(GETALL);
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
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
        return lista;
    }

    @Override
    public Producto obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     private Producto getRS(ResultSet rs) throws SQLException{
        return new Producto(
                rs.getInt("id_producto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getString("modelo"),
                rs.getInt("stock"),
                rs.getDouble("precio"),
                rs.getInt("estado")
            );
    }
    
}
