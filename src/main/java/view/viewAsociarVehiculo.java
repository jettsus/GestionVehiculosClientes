/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.PersonaController;
import controller.VehiculoController;
import controller.VehiculoPersonaController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JETTSUSHD
 */
public class viewAsociarVehiculo extends javax.swing.JFrame {

    /**
     * Creates new form viewAsociarVehiculo
     */
    private DefaultTableModel modeloTablaPersona;
    private DefaultTableModel modeloTablaVehiculo;

    private final PersonaController controladorPersona;
    private final VehiculoController controladorVehiculo;

    public viewAsociarVehiculo() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        controladorPersona = new PersonaController(this);
        controladorVehiculo = new VehiculoController(this);

        // Configurar las tablas
        configurarModelosTablas();
        cargarTablas();
        setTitle("Asociar vehiculo");
        this.pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Maximiza la ventana
        keyBinding();
        TablaPersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //getter para que acceda el controlador
    public JTable getTablaVehiculo() {
        return TablaVehiculos;
    }

    public JTable getTablaPersona() {
        return TablaPersona;
    }

    public void keyBinding() {
        JRootPane rootpane = getRootPane();

        // Acción para cerrar la ventana con ESCAPE
        Action cerrarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        };

        // Acción para aumentar el tamaño de la ventana con CTRL + 
        Action aumentarTamano = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension tamanoActual = getSize();
                setSize(tamanoActual.width + 30, tamanoActual.height + 10);
            }
        };

        // Acción para disminuir el tamaño de la ventana con CTRL + -
        Action disminuirTamano = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension tamanoActual = getSize();
                setSize(tamanoActual.width - 30, tamanoActual.height - 10);
            }
        };

        //añadir keybinding para la tecla escape cierre programa
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CERRAR");
        rootpane.getActionMap().put("CERRAR", cerrarAction);

        //añadir keybinding para la tecla crtl + aumente el tamaño de la ventana
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK), "AUMENTAR");
        rootpane.getActionMap().put("AUMENTAR", aumentarTamano);

        //añadir keybinding para la tecla crtl - disminuya el tamaño de la ventana
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK), "DISMINUIR");
        rootpane.getActionMap().put("DISMINUIR", disminuirTamano);

        requestFocusInWindow();
        setFocusable(true);
    }

    public void refrescarTablas() {
        try {
            // Limpiar y recargar datos de las tablas
            modeloTablaPersona.setRowCount(0); // Limpia la tabla de personas
            controladorPersona.cargarPersonasEnLaTabla();

            modeloTablaVehiculo.setRowCount(0); // Limpia la tabla de vehículos
            controladorVehiculo.cargarVehiculosEnLaTabla();

            // Forzar redibujar las tablas
            TablaPersona.revalidate();
            TablaPersona.repaint();
            TablaVehiculos.revalidate();
            TablaVehiculos.repaint();

            System.out.println("Tablas actualizadas correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al actualizar las tablas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarModelosTablas() {
    modeloTablaPersona = new DefaultTableModel(new Object[][]{},
            new String[]{"DNI", "Nombre", "Género"});
    TablaPersona.setModel(modeloTablaPersona);

    modeloTablaVehiculo = new DefaultTableModel(new Object[][]{},
            new String[]{"Matrícula", "Año", "Marca", "Modelo"});
    TablaVehiculos.setModel(modeloTablaVehiculo);
}
private void cargarTablas() {
    controladorPersona.cargarPersonasEnLaTabla();
    controladorVehiculo.cargarVehiculosEnLaTabla();
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaVehiculos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPersona = new javax.swing.JTable();
        pAsociar = new javax.swing.JPanel();
        bAsociar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        chPersonasSinVehiculo = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        TablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TablaVehiculos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        TablaPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaPersona);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pAsociar.setLayout(new java.awt.BorderLayout());

        bAsociar.setText("Asociar");
        bAsociar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAsociarActionPerformed(evt);
            }
        });
        pAsociar.add(bAsociar, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(23, 0, 0, 0);
        getContentPane().add(pAsociar, gridBagConstraints);

        jPanel1.setLayout(new java.awt.BorderLayout());

        chPersonasSinVehiculo.setText("Vehiculos sin propietario");
        chPersonasSinVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chPersonasSinVehiculoActionPerformed(evt);
            }
        });
        jPanel1.add(chPersonasSinVehiculo, java.awt.BorderLayout.LINE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chPersonasSinVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chPersonasSinVehiculoActionPerformed
        PersonaController controladorPersona = new PersonaController(this);
        VehiculoController controladorVehiculo = new VehiculoController(this);
        // TODO add your handling code here:
        if (chPersonasSinVehiculo.isSelected()) {
            controladorVehiculo.cargarVehiculosSinAsignacionEnTabla();
            controladorPersona.cargarPersonasEnLaTabla();

        } else {
            // Si el checkbox no está seleccionado, mostrar todas las personas y vehículos.
            controladorPersona.cargarPersonasEnLaTabla();

            controladorVehiculo.cargarVehiculosEnLaTabla();
        }
    }//GEN-LAST:event_chPersonasSinVehiculoActionPerformed

    private void bAsociarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAsociarActionPerformed
        // Obtengo la fila seleccionada de ambas tablas
        int filaPersona = TablaPersona.getSelectedRow();
        int filaVehiculo = TablaVehiculos.getSelectedRow();

        // Valida que se haya seleccionado una persona y un vehículo
        if (filaPersona != -1 && filaVehiculo != -1) {
            // Llamar al controlador para llevar la asociación
            VehiculoPersonaController vehiculoPersonaController = new VehiculoPersonaController();
            boolean registrado = vehiculoPersonaController.procesarAsociacionVehiculo(filaPersona, filaVehiculo, TablaPersona, TablaVehiculos);

            //Mensaje de que es valido
            if (registrado) {
                JOptionPane.showMessageDialog(this, "Vehículo asociado con éxito.", "Asociación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al asociar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona y un vehículo para asociar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bAsociarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro de cerrar la ventana?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            System.out.println("Se ha cerrado corectamente");
        } else if (confirm == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(viewAsociarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewAsociarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewAsociarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewAsociarVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewAsociarVehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaPersona;
    private javax.swing.JTable TablaVehiculos;
    private javax.swing.JButton bAsociar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chPersonasSinVehiculo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pAsociar;
    // End of variables declaration//GEN-END:variables
}
