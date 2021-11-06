/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IEProductoDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Empleado;
import com.edu.sise.capas.entity.Producto;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando Tapia Arcos
 */
public class ProductoLogic {
    
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IEProductoDAO dao = factory.getProductoDAO();
    DefaultTableModel modelo =  null;
    
    private DefaultTableModel obtenerTodos() throws Exception{
        return getModelo(modelo, dao.obtenerTodos());
    }
    
    public DefaultTableModel obtenerBusqueda(String v1) throws Exception{
        return getModelo(modelo, dao.obtenerBusqueda(v1));
    }
    
    public void imprimirTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
    
    public void imprimirTB(JTable jtable, DefaultTableModel modelo) throws Exception{
        jtable.setModel(modelo);
    }
    
     public void insertar(Producto o) throws Exception{
        dao.insertar(o);
    }
    
    public void modificar(Producto o) throws Exception{
        dao.modificar(o);
    }
    
    public void eliminar(Producto o) throws Exception{
        dao.eliminar(o);
    }
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Producto> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Producto obj : lista){
            Object data[] = {
              obj.getId_producto(),
              obj.getNombre(),
              obj.getDescripcion(),
              obj.getModelo(),
              obj.getStock(),
              obj.getPrecio(),
              obj.getEstado()
            };
            modelo.addRow(data);
        }

        return modelo;
    }
    
    public DefaultTableModel paginacion(int posicion, int registros) throws Exception{
        return getModelo(modelo,dao.paginacion(posicion, registros));
    }
    
    public int getCount() throws Exception{
        return dao.getCount();
    }
}
