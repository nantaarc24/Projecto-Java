/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.ICarreraDAO;
import com.edu.sise.capas.dao.ICarreraDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Carrera;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando Tapia Arcos
 */
public class CarreraLogic {
    
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    ICarreraDAO dao = factory.getCarreraDAO();
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
    
    public void insertar(Carrera o) throws Exception{
        dao.insertar(o);
    }
    
    public void modificar(Carrera o) throws Exception{
        dao.modificar(o);
    }
    
    public void eliminar(Carrera o) throws Exception{
        dao.eliminar(o);
    }
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Carrera> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        //APE_PAT --> APE PAT
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Carrera obj : lista){
            Object data[] = {
              obj.getId_carrera(),
              obj.getNombre()
            };
            modelo.addRow(data);
        }
//        modelo.addColumn("ID");
//        modelo.addColumn("NOMBRE");
//        modelo.addColumn("APE. PAT.");
//        modelo.addColumn("APE. MAT.");
//        modelo.addColumn("FEC. NAC.");
//        modelo.addColumn("SUELDO");
        return modelo;
    }
    
    public int cargaMasiva(String rutaArchivo)throws Exception{
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        DataInputStream entrada = new DataInputStream(archivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
        List<Carrera> lista = new ArrayList<>();
        String linea;
        while((linea=buffer.readLine()) !=null){
           System.out.println(linea);
           String[] campos = linea.split("\\|");
           lista.add(new Carrera(
                   0,
                   campos[0]
                   
                   
           ));
           
//           for(int i=0;i<campos.length;i++){
//               System.out.println(campos[i]);
//           }
               
        }
        entrada.close();
        
        int[] resultados =  dao.cargaMasiva(lista);
        // resultados[0] = 1
        // resultados[1] = 0
        // resultados[2] = 1
        int registros_afectados= 0;
        for(int i=0; i<resultados.length;i++){
            registros_afectados = registros_afectados + resultados[i];
        }
        
        return registros_afectados;
    }
    
    public DefaultTableModel paginacion(int posicion, int registros) throws Exception{
        return getModelo(modelo,dao.paginacion(posicion, registros));
    }
    
    public int getCount() throws Exception{
        return dao.getCount();
    }
}
