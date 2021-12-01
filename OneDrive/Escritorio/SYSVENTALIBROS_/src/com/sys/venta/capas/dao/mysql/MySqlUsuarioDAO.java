/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.dao.mysql;

import com.sys.venta.capas.dao.Conexion;

import com.sys.venta.capas.dao.DAOException;
import com.sys.venta.capas.dao.IUsuarioDAO;
import com.sys.venta.capas.entity.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Tapia Arcos
 */
public class MySqlUsuarioDAO implements IUsuarioDAO{

    final String GETALL = "{call pa_listar_usuarios()}";
    final String COLUMNAS = "{call pa_obtener_columnas_usuario()}";
    final String INSERT ="{call pa_insertar_usuarios(?,?,?,?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_usuarios(?,?,?,?,?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_usuarios(?)}";
    final String BUSQUEDA ="{call pa_buscar_usuario(?)}";
    final String VALIDAR ="{call pa_validar_acceso_usuario(?,?)}";
    
    private Connection cn;

    public MySqlUsuarioDAO(Connection cn){
        this.cn=cn;
    }
    
    @Override
    public List<String> obtenerNombresColumnas() throws DAOException {
        CallableStatement cs=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        List<String> listaNomColumn=new ArrayList<>();
        
        try {
            cs=cn.prepareCall(COLUMNAS);
            rs=cs.executeQuery();
            rsmd=rs.getMetaData();
            
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                listaNomColumn.add(rsmd.getColumnName(i));
            }
        } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
            }
        return listaNomColumn;
    }


    @Override
    public void insertar(Usuario o) throws DAOException {
    
        CallableStatement cs=null;
        ResultSet rs=null;
        
        try {
            cs = cn.prepareCall(INSERT);
            int i=1;
            
            cs.setString(i++, o.getNombre());
            cs.setString(i++, o.getApellidos());
            cs.setString(i++, o.getDni());
            cs.setString(i++, o.getLogin());
            cs.setString(i++, o.getClave());
            cs.setString(i++, o.getRol());
            cs.setString(i++, o.getEstado());
            
            
            if (cs.executeUpdate()==0) 
                throw new DAOException("No se pudo realizar el registro!!!");
            
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }
        finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void modificar(Usuario o) throws DAOException {
    
        CallableStatement cs=null;
        ResultSet rs=null;
        
        try {
            cs = cn.prepareCall(UPDATE);
            int i=1;
            cs.setString(i++, o.getNombre());
            cs.setString(i++, o.getApellidos());
            cs.setString(i++, o.getDni());
            cs.setString(i++, o.getLogin());
            cs.setString(i++, o.getClave());
            cs.setString(i++, o.getRol());
            cs.setString(i++, o.getEstado());
            cs.setInt(i++, o.getIdusuario());
            
            
            if (cs.executeUpdate()==0) 
                throw new DAOException("No se pudo actualizar el registro!!!");
            
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }
        finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void eliminar(Usuario o) throws DAOException {
    
        CallableStatement cs=null;
        ResultSet rs=null;
        
        try {
            cs = cn.prepareCall(DELETE);
            int i=1;
            cs.setInt(i++, o.getIdusuario()); 
            if (cs.executeUpdate()==0) 
                throw new DAOException("No se pudo eliminar el registro!!!");
            
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }
        finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public List<Usuario> obtenerTodos() throws DAOException {
    
        CallableStatement cs=null;
        ResultSet rs=null;
        List<Usuario> lista=new ArrayList<>();
        
        try {
            cs=cn.prepareCall(GETALL);
            rs=cs.executeQuery();
            while (rs.next()) {                
                lista.add(getRS(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }
        }
        return lista;
    }
    
    private Usuario getRS(ResultSet rs) throws SQLException{
        return new Usuario(
                rs.getInt("idusuario"),
                
                rs.getString("nombre"),
                rs.getString("apellidos"),
                rs.getString("dni"),
                rs.getString("login"),
                rs.getString("clave"),
                rs.getString("roll"),
                rs.getString("estado")
                
        );
    }

    @Override
    public void validarAcceso(Usuario o) throws DAOException {
        cn = new Conexion().getConn();
        CallableStatement cs=null;
        ResultSet rs=null;
//       List<Usuario> lista=new ArrayList<>();
        try {
            cs = cn.prepareCall(VALIDAR);
            
            int i=1;
             
             cs.setString(i++, o.getLogin());
             cs.setString(i++, o.getClave());
//             cs.setInt(i++, o.getIdusuario());
//             rs=cs.executeQuery();
//            while (rs.next()) {                
//                lista.add(getRS(rs));
//            }
         if (cs.executeUpdate()==0) 
                throw new DAOException("No se pudo validar el acceso!!!");
                 

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        } finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }
//        return lista;
    }

    @Override
    public List<Usuario> obtenerBusqueda(String v1) throws DAOException {
        
        CallableStatement cs=null;
        ResultSet rs=null;
        List<Usuario> lista=new ArrayList<>();
        
        try {
            cs=cn.prepareCall(BUSQUEDA);
            int i=1;
            cs.setString(i++, v1);
            
            rs=cs.executeQuery();
            while (rs.next()) {                
                lista.add(getRS(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            } catch (SQLException ex) {
            throw new DAOException("Error en SQL ",ex);
        }
        }
        return lista;
    }

    @Override
    public Usuario obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
