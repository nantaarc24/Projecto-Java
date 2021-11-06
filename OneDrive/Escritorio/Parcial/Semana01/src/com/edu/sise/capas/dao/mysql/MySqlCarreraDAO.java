/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.ICarreraDAO;
import com.edu.sise.capas.entity.Carrera;
import java.sql.Connection;
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
public class MySqlCarreraDAO implements ICarreraDAO{
    
    final String INSERT = "INSERT INTO Carreras(nombre) "
            + " VALUES(?)";
    final String GETALL = "SELECT * FROM Carreras";
    final String UPDATE=  "UPDATE Carreras "
            + " SET nombre = ? "
            + " WHERE id_carrera = ?";
    final String BUSQUEDA = "SELECT * FROM Carreras "
            + " WHERE nombre LIKE ?";
    final String DELETE = "DELETE FROM Carreras WHERE id_carrera = ?";
    final String COLUMNAS = "SELECT * FROM Carreras LIMIT 0";
    final String PAGINACION = "SELECT * FROM Carreras LIMIT ?,? ";
    final String COUNT = "SELECT count(*) FROM Carreras";
    
    private Connection cn;
    
    public MySqlCarreraDAO(Connection cn){
        this.cn=cn;
    }

    @Override
    public void insertar(Carrera o) throws DAOException {
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
                o.setId_carrera(rs.getInt(1));
            }else{
                throw new DAOException("No se puede generar el ID de la carrera");
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
    public void modificar(Carrera o) throws DAOException {
        PreparedStatement ps = null;
        
        try{
            ps = cn.prepareStatement(UPDATE);
            int i = 1;
            ps.setString(i++,o.getNombre());
            ps.setInt(i++, o.getId_carrera());
            
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
    public void eliminar(Carrera o) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = cn.prepareStatement(DELETE);
            int i = 1;
            ps.setInt(i++, o.getId_carrera());
            if(ps.executeUpdate()==0)
                throw new DAOException("No se pudo eliminar la carrera!!!");
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
    public List<Carrera> obtenerTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Carrera> lista = new ArrayList<>();
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
    
     private Carrera getRS(ResultSet rs) throws SQLException{
        return new Carrera(
                rs.getInt("id_carrera"),
                rs.getString("nombre")
                
            );
    }
    
    @Override
    public Carrera obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carrera> obtenerBusqueda(String v1) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Carrera> lista = new ArrayList<>();
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
    public int[] cargaMasiva(List<Carrera> lista) throws DAOException {
        PreparedStatement ps = null;
        int[] res;
        try{
            ps = cn.prepareStatement(INSERT);
            for(Carrera o: lista){
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
    public List<Carrera> paginacion(Integer posicion, Integer registros) throws DAOException {
       PreparedStatement ps =  null;
        ResultSet rs = null;
        List<Carrera> lista = new ArrayList<>();
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
    
}
