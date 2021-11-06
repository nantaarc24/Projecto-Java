/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IProvinciaDAO;
import com.edu.sise.capas.entity.Provincia;
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
public class MySqlProvinciaDAO implements IProvinciaDAO{

    
    final String INSERT = "INSERT INTO Provincias(nombre) "
            + " VALUES(?)";
    final String GETALL = "SELECT * FROM Provincias";
    final String UPDATE=  "UPDATE Provincias SET nombre = ? WHERE id_prov = ?";
       
    final String BUSQUEDA = "SELECT * FROM Provincias "
            + " WHERE nombre LIKE ?";
    final String DELETE = "DELETE FROM Provincias WHERE id_prov = ?";
    final String COLUMNAS = "SELECT * FROM Provincias LIMIT 0";
    final String PAGINACION = "SELECT * FROM Provincias LIMIT ?,? ";
    final String COUNT = "SELECT count(*) FROM Provincias";
    
    private Connection cn;
    
    public MySqlProvinciaDAO(Connection cn){
        this.cn = cn;
    }
    
    @Override
    public void insertar(Provincia o) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = cn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++,o.getNombre());
            
            
            if(ps.executeUpdate()==0)
                throw new DAOException("No se pudo realizar el registro!!!");
            
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                o.setId_prov(rs.getInt(1));
            }else{
                throw new DAOException("No se puede generar el ID de la provincia");
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
    public void modificar(Provincia o) throws DAOException {
       PreparedStatement ps = null;
        
        try{
            ps = cn.prepareStatement(UPDATE);
            int i = 1;
            ps.setString(i++,o.getNombre());
            ps.setInt(i++,o.getId_prov());
            
            
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
    public void eliminar(Provincia o) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = cn.prepareStatement(DELETE);
            int i = 1;
            ps.setInt(i++, o.getId_prov());
            if(ps.executeUpdate()==0)
                throw new DAOException("No se pudo eliminar la provincia!!!");
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
    public List<Provincia> obtenerTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Provincia> lista = new ArrayList<>();
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
    public Provincia obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Provincia getRS(ResultSet rs) throws SQLException{
        return new Provincia(
                rs.getInt("id_prov"),
                rs.getString("nombre")
                
            );
    }

    @Override
    public List<Provincia> obtenerBusqueda(String v1) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Provincia> lista = new ArrayList<>();
        String valor ="%"+v1+"%";
        try {
            ps = cn.prepareStatement(BUSQUEDA);
            int i =1;
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
    public int[] cargaMasiva(List<Provincia> lista) throws Exception {
        PreparedStatement ps = null;
        int[] res;
        try{
            ps = cn.prepareStatement(INSERT);
            for(Provincia o: lista){
                int i = 1;
                ps.setString(i++,o.getNombre());
                
                ps.addBatch();
            }
            
            res = ps.executeBatch();
            if(res.length==0)
                throw new DAOException("No se pudo realizar la carga masiva!!!");
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(ps!=null) ps.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return res;
    }

    @Override
    public List<String> obtenerNombresColumnas() throws Exception {
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
    public List<Provincia> paginacion(Integer posicion, Integer registros) throws Exception {
        PreparedStatement ps =  null;
        ResultSet rs = null;
        List<Provincia> lista = new ArrayList<>();
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
    public int getCount() throws Exception {
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
    
}
