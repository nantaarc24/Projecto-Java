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
import com.sys.venta.capas.entity.UsuarioForm;
import com.sys.venta.capas.utils.Utils;
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
    //validaciones
    public void hayInputError(UsuarioForm o) throws Exception{
        
        //validaciones
     if(o.getIdusuario().isEmpty() || o.getIdusuario().trim().length()==0) throw new Exception(Utils.getMensaje("el ID ",Utils.NO_VACIO));
     if(o.getNombre().isEmpty() || o.getNombre().trim().length()==0) throw new Exception(Utils.getMensaje("el nombre ",Utils.NO_VACIO));
     if(o.getApellidos().isEmpty() || o.getApellidos().trim().length()==0) throw new Exception(Utils.getMensaje("los apellidos",Utils.NO_VACIO));
     if(o.getDni().isEmpty() || o.getDni().trim().length()==0){
        
         throw new Exception(Utils.getMensaje("El DNI ", Utils.NO_LETRAS));
     }
     if(o.getLogin().isEmpty() || o.getLogin().trim().length()==0) throw new Exception(Utils.getMensaje("el usuario",Utils.NO_VACIO));
     
     
    } 
     public void hayInputErrorLogic(UsuarioForm o) throws Exception{
        
        //validaciones
     
     if(o.getLogin().isEmpty() || o.getLogin().trim().length()==0) throw new Exception(Utils.getMensaje("el usuario",Utils.NO_VACIO));
     if(o.getClave().isEmpty() || o.getClave().trim().length()==0) throw new Exception(Utils.getMensaje("la contraseña",Utils.NO_VACIO));
     
     
    } 
}