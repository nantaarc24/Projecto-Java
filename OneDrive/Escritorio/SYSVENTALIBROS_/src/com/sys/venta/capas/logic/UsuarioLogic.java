/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.logic;

import com.sys.venta.capas.dao.IUsuarioDAO;
import com.sys.venta.capas.dao.mysql.MySqlDAOManager;
import com.sys.venta.capas.dao.mysql.MySqlUsuarioDAO;
import com.sys.venta.capas.entity.Usuario;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando Tapia Arcos
 */
public class UsuarioLogic {
    
//    MySqlUsuarioDAO dao1=null; 
    
    MySqlDAOManager factory=MySqlDAOManager.getInstancia();//Singleton
    IUsuarioDAO dao=factory.getUsuarioDAO();
    DefaultTableModel modelo=null;
    DefaultComboBoxModel modeloCB=null;
//    public Usuario validarAcceso(Usuario objUsuario){
//        dao = new MySqlUsuarioDAO();
//        return dao.validarAcceso(objUsuario);
//    }
    
    private DefaultTableModel obtenerTodos() throws Exception{
        return getModelo(modelo, dao.obtenerTodos());
    }
    
    public void imprimirTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
    
    public void insertar(Usuario o)throws Exception{
        dao.insertar(o);
    }
    
    public void modificar(Usuario o)throws Exception{
        dao.modificar(o);
    }
    
    public void eliminar(Usuario o)throws Exception{
        dao.eliminar(o);
    }
    
    public DefaultTableModel obtenerBusqueda(String v1) throws Exception{
        return getModelo(modelo, dao.obtenerBusqueda(v1));
    }
    
     public void imprimirTB(JTable jtable, DefaultTableModel modelo) throws Exception{
        jtable.setModel(modelo);
    }
    
    public void validarAcceso(Usuario o)throws Exception{
        
        dao.validarAcceso(o);
    }
    
     
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Usuario> lista)throws Exception{
        modelo= new DefaultTableModel();
        List<String> listaNomColumn= dao.obtenerNombresColumnas();
        
        //APE_PAT --> APE_PAT
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
            
        }
        //llenar el modelo con la lista
        for(Usuario obj: lista){
            Object data[] = {
                obj.getIdusuario(),
                
                obj.getNombre(),
                obj.getApellidos(),
                obj.getDni(),
                obj.getLogin(),
                obj.getClave(),
                obj.getRol(),
                obj.getEstado()
                
            };
            modelo.addRow(data);
        }
        return modelo;
    }

    
    public List<Usuario> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
    
     private DefaultComboBoxModel getModeloCB(List<Usuario> lista){
        modeloCB=new DefaultComboBoxModel();
        for(Usuario o:lista){
            modeloCB.addElement(o);
        }
        return modeloCB;
    }
     
     
      public void BuscarCB(JComboBox jComboBox,int idusuario){
        DefaultComboBoxModel modeloUsuarioCB=(DefaultComboBoxModel) jComboBox.getModel();
        Usuario objUsuario=null;
        for (int i = 0; i < modeloUsuarioCB.getSize(); i++) {
            objUsuario=(Usuario)modeloUsuarioCB.getElementAt(i);
            
            if(objUsuario.getIdusuario()==idusuario){
                modeloUsuarioCB.setSelectedItem(modeloUsuarioCB.getElementAt(i));
                break;
            }
            
        }
    }
}