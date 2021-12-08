/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.logic;

import com.sys.venta.capas.dao.ICategoriaDAO;
import com.sys.venta.capas.dao.mysql.MySqlDAOManager;
import com.sys.venta.capas.entity.Categoria;
import com.sys.venta.capas.entity.CategoriaForm;
import com.sys.venta.capas.entity.UsuarioForm;
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
public class CategoriaLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    ICategoriaDAO dao = factory.getCategoriaDAO();
    DefaultTableModel modelo =  null;
     DefaultComboBoxModel modeloCB=null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Categoria> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Categoria obj : lista){
            Object data[] = {
              obj.getIdcategoria(),             
              obj.getNombre(),              
              obj.getDescripcion(),
             
              
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
    
    public void insertar(Categoria o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Categoria o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Categoria o) throws Exception {
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
//    public void generarReporte(List<Categoria> lista) throws Exception{
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
    
    public List<Categoria> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
 
    
     public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
     
        private DefaultComboBoxModel getModeloCB(List<Categoria> lista){
        modeloCB=new DefaultComboBoxModel();
        for(Categoria o:lista){
            modeloCB.addElement(o);
        }
        return modeloCB;
    }
        
              public void BuscarCB(JComboBox jComboBox,int idcategoria){
        DefaultComboBoxModel modeloCategoriaCB=(DefaultComboBoxModel) jComboBox.getModel();
        Categoria objCategoria=null;
        for (int i = 0; i < modeloCategoriaCB.getSize(); i++) {
            objCategoria=(Categoria)modeloCategoriaCB.getElementAt(i);
            
            if(objCategoria.getIdcategoria()==idcategoria){
                modeloCategoriaCB.setSelectedItem(modeloCategoriaCB.getElementAt(i));
                break;
            }
            
        }
    }
    
    //validaciones
    public void hayInputError(CategoriaForm o) throws Exception{
        
        //validaciones
     if(o.getIdcategoria().isEmpty() || o.getIdcategoria().trim().length()==0) throw new Exception(Utils.getMensaje("el ID ",Utils.NO_VACIO));
     if(o.getNombre().isEmpty() || o.getNombre().trim().length()==0) throw new Exception(Utils.getMensaje("el nombre ",Utils.NO_VACIO));
     if(o.getDescripcion().isEmpty() || o.getDescripcion().trim().length()==0) throw new Exception(Utils.getMensaje("la descripcion",Utils.NO_VACIO));
    } 
}
