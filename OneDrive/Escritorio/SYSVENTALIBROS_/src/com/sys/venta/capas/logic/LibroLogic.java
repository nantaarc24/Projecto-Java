/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.logic;

import com.sys.venta.capas.dao.ILibroDAO;
import com.sys.venta.capas.dao.mysql.MySqlDAOManager;
import com.sys.venta.capas.entity.Libro;
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
public class LibroLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    ILibroDAO dao = factory.getLibroDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB = null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Libro> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Libro obj : lista){
            Object data[] = {
              obj.getIdlibro(),
              obj.getIdcategoriafk(),
              obj.getCategoria(),
              obj.getNombre(),
              obj.getAutor(),
              obj.getDescripcion(),
              obj.getStock(),
              obj.getPrecio(),
              
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
    
    public void insertar(Libro o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Libro o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Libro o) throws Exception {
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
//    public void generarReporte(List<Libro> lista) throws Exception{
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
    
    public List<Libro> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
     
        private DefaultComboBoxModel getModeloCB(List<Libro> lista){
        modeloCB=new DefaultComboBoxModel();
        for(Libro o:lista){
            modeloCB.addElement(o);
        }
        return modeloCB;
    }
}
