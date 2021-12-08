/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.logic;

import com.sys.venta.capas.dao.IDetalleventaDAO;
import com.sys.venta.capas.dao.mysql.MySqlDAOManager;
import com.sys.venta.capas.entity.Detalleventa;
import java.util.List;
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
public class DetalleventaLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IDetalleventaDAO dao = factory.getDetalleventaDAO();
    DefaultTableModel modelo =  null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Detalleventa> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Detalleventa obj : lista){
            Object data[] = {
              
              obj.getIdventafk(),
              obj.getIdlibrofk(),
              obj.getCantidad(),
              obj.getMonto()
             
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    
    private DefaultTableModel obtenerTodos() throws Exception{
        return getModelo(modelo, dao.obtenerTodos());
    }
    
    public void imprimirDetalleTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
    
    public void insertar(Detalleventa o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Detalleventa o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Detalleventa o) throws Exception {
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
//    public void generarReporte(List<Venta> lista) throws Exception{
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
//    
    public List<Detalleventa> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
}
