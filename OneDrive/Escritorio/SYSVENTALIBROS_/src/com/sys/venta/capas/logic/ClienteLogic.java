/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.logic;

import com.sys.venta.capas.dao.IClienteDAO;
import com.sys.venta.capas.dao.mysql.MySqlDAOManager;
import com.sys.venta.capas.entity.CategoriaForm;
import com.sys.venta.capas.entity.Cliente;
import com.sys.venta.capas.entity.ClienteForm;
import com.sys.venta.capas.entity.Usuario;
import com.sys.venta.capas.utils.Utils;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.util.JRLoader;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Carlos
 */
public class ClienteLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IClienteDAO dao = factory.getClienteDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB=null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Cliente> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Cliente obj : lista){
            Object data[] = {
              obj.getIdcliente(),
              obj.getNombre(),
              obj.getApellidos(),
              obj.getDni(),
              obj.getDireccion(),
              obj.getTelefono(),
              
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    
    private DefaultTableModel obtenerTodos() throws Exception{
        return getModelo(modelo, dao.obtenerTodos());
    }
    
    public void imprimirTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
    
    public void insertar(Cliente o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Cliente o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Cliente o) throws Exception {
        dao.eliminar(o);
    }
    
    public DefaultTableModel obtenerBusqueda(String valor) throws Exception{
        return getModelo(modelo, dao.obtenerBusqueda(valor));
    }
    
    public void imprimirTB(JTable jtable, DefaultTableModel modelo) throws Exception{
        jtable.setModel(modelo);
    }
//    
//    public void generarReporte() throws Exception{
//        JasperReport reporte;
//        String ruta ="D:\\reportes\\rpt_profesores.jasper";
//        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
//        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
//                new JRBeanCollectionDataSource(dao.obtenerTodos())
//                );
//        JasperViewer jViewer = new JasperViewer(jprint,false);
//        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        jViewer.setVisible(true);
//    }
//    
//    public void generarReporte(List<Cliente> lista) throws Exception{
//        JasperReport reporte;
//        String ruta ="D:\\reportes\\rpt_profesores.jasper";
//        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
//        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
//                new JRBeanCollectionDataSource(lista)
//                );
//        JasperViewer jViewer = new JasperViewer(jprint,false);
//        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        jViewer.setVisible(true);
//    }
    
    public List<Cliente> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
     public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
     
         private DefaultComboBoxModel getModeloCB(List<Cliente> lista){
        modeloCB=new DefaultComboBoxModel();
        for(Cliente o:lista){
            modeloCB.addElement(o);
        }
        return modeloCB;
    }
         
      public void BuscarCB(JComboBox jComboBox,int idcliente){
        DefaultComboBoxModel modeloUsuarioCB=(DefaultComboBoxModel) jComboBox.getModel();
        Cliente objUsuario=null;
        for (int i = 0; i < modeloUsuarioCB.getSize(); i++) {
            objUsuario=(Cliente)modeloUsuarioCB.getElementAt(i);
            
            if(objUsuario.getIdcliente()==idcliente){
                modeloUsuarioCB.setSelectedItem(modeloUsuarioCB.getElementAt(i));
                break;
            }
            
        }
    }
      
       //validaciones
    public void hayInputError(ClienteForm o) throws Exception{
        
        //validaciones
     if(o.getIdcliente().isEmpty() || o.getIdcliente().trim().length()==0) throw new Exception(Utils.getMensaje("el ID ",Utils.NO_VACIO));
     if(o.getDni().isEmpty() || o.getDni().trim().length()==0) throw new Exception(Utils.getMensaje("el DNI ",Utils.NO_VACIO));
     if(o.getNombre().isEmpty() || o.getNombre().trim().length()==0) throw new Exception(Utils.getMensaje("el nombre ",Utils.NO_VACIO));
     if(o.getApellidos().isEmpty() || o.getApellidos().trim().length()==0) throw new Exception(Utils.getMensaje("los apellidos",Utils.NO_VACIO));
     if(o.getDireccion().isEmpty() || o.getDireccion().trim().length()==0) throw new Exception(Utils.getMensaje("la dirección ",Utils.NO_VACIO));
     if(o.getTelefono().isEmpty() || o.getTelefono().trim().length()==0) throw new Exception(Utils.getMensaje("el teléfono",Utils.NO_VACIO));
     
    } 
}
