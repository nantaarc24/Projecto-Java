/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.venta.capas.gui;

        

/**
 *
 * @author Carlos
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mniSalir = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenu();
        mniUsuarios = new javax.swing.JMenuItem();
        mniClientes = new javax.swing.JMenuItem();
        mniVenta = new javax.swing.JMenuItem();
        mniDetalleVenta = new javax.swing.JMenuItem();
        mniEmpleados = new javax.swing.JMenuItem();
        mniCategoria = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("...::SISTEMA PRINCIPAL::...");

        mniSalir.setText("Archivo");
        mniSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSalirActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Salir");
        mniSalir.add(jMenuItem2);

        jMenuBar1.add(mniSalir);

        jMenuBar2.setText("Mantenedores");

        mniUsuarios.setText("Usuarios");
        mniUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuariosActionPerformed(evt);
            }
        });
        jMenuBar2.add(mniUsuarios);

        mniClientes.setText("Clientes");
        mniClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClientesActionPerformed(evt);
            }
        });
        jMenuBar2.add(mniClientes);

        mniVenta.setText("Venta");
        mniVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniVentaActionPerformed(evt);
            }
        });
        jMenuBar2.add(mniVenta);

        mniDetalleVenta.setText("Detalle de Venta");
        mniDetalleVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDetalleVentaActionPerformed(evt);
            }
        });
        jMenuBar2.add(mniDetalleVenta);

        mniEmpleados.setText("Libros");
        mniEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEmpleadosActionPerformed(evt);
            }
        });
        jMenuBar2.add(mniEmpleados);

        mniCategoria.setText("Categorias");
        mniCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCategoriaActionPerformed(evt);
            }
        });
        jMenuBar2.add(mniCategoria);

        jMenuBar1.add(jMenuBar2);

        jMenu1.setText("Reportes");

        jMenuItem6.setText("Profesores");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_mniSalirActionPerformed

    private void mniEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEmpleadosActionPerformed
        // TODO add your handling code here:
        iFrmLibros ifrmInterno = new iFrmLibros();
        this.add(ifrmInterno);
        ifrmInterno.show();
    }//GEN-LAST:event_mniEmpleadosActionPerformed

    private void mniUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuariosActionPerformed
       FrmUsuario frmInterno = new FrmUsuario();
        
        frmInterno.setVisible(true);
        
    }//GEN-LAST:event_mniUsuariosActionPerformed

    private void mniClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClientesActionPerformed
        iFrmClientes ifrmInterno = new iFrmClientes();
        this.add(ifrmInterno);
        ifrmInterno.show();
        
    }//GEN-LAST:event_mniClientesActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void mniVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniVentaActionPerformed
           FrmVenta venta = new FrmVenta();
           venta.setVisible(true);
       
    }//GEN-LAST:event_mniVentaActionPerformed

    private void mniDetalleVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDetalleVentaActionPerformed
//        iFrmDetalleventa ifrmInterno = new iFrmDetalleventa();
//        this.add(ifrmInterno);
//        ifrmInterno.show();

    }//GEN-LAST:event_mniDetalleVentaActionPerformed

    private void mniCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCategoriaActionPerformed
      iFrmCategoria ifrmInterno = new iFrmCategoria();
       this.add(ifrmInterno);
        ifrmInterno.show();
 
    }//GEN-LAST:event_mniCategoriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrmPrincipal().setVisible(true);
                FrmPrincipal frm = new FrmPrincipal();
                frm.setExtendedState(MAXIMIZED_BOTH);
                frm.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem mniCategoria;
    private javax.swing.JMenuItem mniClientes;
    private javax.swing.JMenuItem mniDetalleVenta;
    private javax.swing.JMenuItem mniEmpleados;
    private javax.swing.JMenu mniSalir;
    private javax.swing.JMenuItem mniUsuarios;
    private javax.swing.JMenuItem mniVenta;
    // End of variables declaration//GEN-END:variables
}
