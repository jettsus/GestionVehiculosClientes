/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.PersonaController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import model.Persona;

/**
 *
 * @author JETTSUSHD
 */
public class viewCrearPersona extends javax.swing.JFrame {

    /**
     * Creates new form viewCrearPersona
     */
    private final viewAsociarVehiculo ventanaAsociarVehiculo;

// Constructor que recibe la referencia de `viewAsociarVehiculo`
    public viewCrearPersona(viewAsociarVehiculo ventanaAsociarVehiculo) {
        initComponents();
        this.ventanaAsociarVehiculo = ventanaAsociarVehiculo; // Almacena la referencia
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buttonGroup1.add(rbHombre);
        buttonGroup1.add(rbMujer);

        setTitle("Crear persona");
        this.pack();
        keyBinding();
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
        PersonaController controladorPersona = new PersonaController();

        controladorPersona.cargarPersonasEnLaTabla();
    }

    private boolean validarNombre(String nombre) {
        boolean esValido = true;

        nombre = nombre.trim();

        //  Comprueb lo que mide
        if (nombre.isEmpty() || nombre.length() < 1 || nombre.length() > 40) {
            JOptionPane.showMessageDialog(this,
                    "El nombre debe tener entre 1 y 40 caracteres.",
                    "Error en el nombre", JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Compruebo que solo tenga letras, espacios y caracteres válidos
        if (esValido && !nombre.matches("^[\\p{L}]+(\\s[\\p{L}]+)*$")) {
            JOptionPane.showMessageDialog(this,
                    "El nombre solo puede contener letras (incluyendo tildes) y espacios, sin espacios consecutivos.",
                    "Error en el nombre", JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Compruebo palabras prohibidas
        if (esValido && nombre.matches("(?i).*\\b(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
            JOptionPane.showMessageDialog(this,
                    "El nombre no puede contener palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc.",
                    "Error en el nombre", JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        return esValido;
    }

    private boolean validarDNI(String dni) {
        boolean esValido = true;

        // Convertir a mayúsculas y eliminar espacios
        dni = dni.trim().toUpperCase();

        // compruebo longitud exacta
        if (dni.length() != 9) {
            JOptionPane.showMessageDialog(this,
                    "El DNI debe tener 9 caracteres (8 números y 1 letra mayúscula).",
                    "Error en el DNI",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // compruebo formato: 8 números seguidos de 1 letra mayúscula
        if (esValido && !dni.matches("^[0-9]{8}[A-Z]$")) {
            JOptionPane.showMessageDialog(this,
                    "El DNI debe debe tener en 8 números seguidos de una letra mayúscula.",
                    "Error en el DNI",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // compruebo que no contenga palabras prohibidas
        if (esValido && dni.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
            JOptionPane.showMessageDialog(this,
                    "El DNI no puede contener palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc.",
                    "Error en el DNI",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Verificar si el DNI ya existe
        if (esValido) {
            PersonaController controllerPersona = new PersonaController();
            if (controllerPersona.verificarDniExiste(dni)) {
                JOptionPane.showMessageDialog(this,
                        "El DNI ya está registrado en el sistema. Por favor, ingrese un DNI diferente.",
                        "Error en el DNI",
                        JOptionPane.ERROR_MESSAGE);
                esValido = false;
            }
        }

        // Si es válido, actualizar el campo visual a mayúsculas
        if (esValido) {
            tfDNI.setText(dni);
        }

        return esValido;
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
        lNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        lDNI = new javax.swing.JLabel();
        tfDNI = new javax.swing.JTextField();
        rbHombre = new javax.swing.JRadioButton();
        rbMujer = new javax.swing.JRadioButton();
        bConfirmar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lNombre.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 17);
        getContentPane().add(lNombre, gridBagConstraints);

        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfNombre, gridBagConstraints);

        lDNI.setText("DNI:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 17);
        getContentPane().add(lDNI, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(tfDNI, gridBagConstraints);

        rbHombre.setText("Hombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 17);
        getContentPane().add(rbHombre, gridBagConstraints);

        rbMujer.setText("Mujer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(rbMujer, gridBagConstraints);

        bConfirmar.setText("OK");
        bConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(bConfirmar, gridBagConstraints);

        bCancelar.setText("Cancel");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        getContentPane().add(bCancelar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarActionPerformed
        boolean esValido = true;

        // Obtengo los datos de la vista
        String nombre = tfNombre.getText().trim();
        String dni = tfDNI.getText().trim();
        char genero = 0;

        // Validar nombre
        if (!validarNombre(nombre)) {
            esValido = false;
        }

        // Validar DNI
        if (!validarDNI(dni)) {
            esValido = false;
        }

        // Validar género
        if (!rbHombre.isSelected() && !rbMujer.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un género.",
                    "Error en el género",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        } else {
            genero = rbHombre.isSelected() ? 'H' : 'M';
        }

        // Procesar si todo es válido
        if (esValido) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea agregar la persona?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombre(nombre);
                nuevaPersona.setDni(dni);
                nuevaPersona.setGenero(genero);

                // Después de realizar el insert
                PersonaController controller = new PersonaController();
                controller.insertarPersona(nuevaPersona);
                viewAsociarVehiculo ventanaAsociarVehiculo = new viewAsociarVehiculo();

                ventanaAsociarVehiculo.setVisible(true);
// Refrescar la tabla en viewAsociarVehiculo
                if (ventanaAsociarVehiculo != null) {
                    ventanaAsociarVehiculo.refrescarTablas();
                }

                JOptionPane.showMessageDialog(this,
                        "Persona agregada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpio los campos
                tfNombre.setText("");
                tfDNI.setText("");
                buttonGroup1.clearSelection();
            }
        }
    }//GEN-LAST:event_bConfirmarActionPerformed

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
            java.util.logging.Logger.getLogger(viewCrearPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewCrearPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewCrearPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewCrearPersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewCrearPersona(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bConfirmar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lDNI;
    private javax.swing.JLabel lNombre;
    private javax.swing.JRadioButton rbHombre;
    private javax.swing.JRadioButton rbMujer;
    private javax.swing.JTextField tfDNI;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
