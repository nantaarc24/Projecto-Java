/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao.mysql;

import com.sys.venta.capas.dao.DAOException;
import  com.sys.venta.capas.dao.ILibroDAO;
import  com.sys.venta.capas.entity.Libro;
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
public class MySqlLibroDAO implements ILibroDAO{
    
    final String GETALL = "{call pa_listar_libros()}";
    final String COLUMNAS = "{call pa_columnas_libro()}";
    final String INSERT = "{call pa_insertar_libros(?,?,?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_libros(?,?,?,?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_libros(?)}";
    final String BUSQUEDA = "{call pa_buscar_libro_nombre(?)}";

    private Connection cn;

    public MySqlLibroDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Libro o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(INSERT);
            int i = 1;            
            cs.setInt(i++,o.getIdcategoriafk());
            cs.setString(i++,o.getNombre());
            cs.setString(i++,o.getAutor());        
            cs.setString(i++,o.getDescripcion());
            cs.setInt(i++,o.getStock());
            cs.setDouble(i++,o.getPrecio());
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
    public void modificar(Libro o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(UPDATE);
            int i = 1;
              
            cs.setInt(i++,o.getIdcategoriafk());
            cs.setString(i++,o.getNombre());
            cs.setString(i++,o.getAutor());        
            cs.setString(i++,o.getDescripcion());
            cs.setInt(i++,o.getStock());
            cs.setDouble(i++,o.getPrecio());
            cs.setInt(i++,o.getIdlibro());
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
    public void eliminar(Libro o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getIdlibro());
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
    public List<Libro> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Libro> lista = new ArrayList<>();
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
    public Libro obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Libro getRS(ResultSet rs) throws SQLException{
        return new Libro(
                rs.getInt("idlibro"),
                rs.getInt("idcategoriafk"),
                rs.getString("categoria"),               
                rs.getString("nombre"),
                rs.getString("autor"),
                rs.getString("descripcion"),
                rs.getInt("stock"),
                rs.getDouble("precio")
            );
    }

    @Override
    public List<String> obtenerNombresColumnas() throws DAOException {
        CallableStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<String> listaNomColumn = new ArrayList<>();
        
        try {
            ps = cn.prepareCall(COLUMNAS);
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
    public List<Libro> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Libro> lista = new ArrayList<>();
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
