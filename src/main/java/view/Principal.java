/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.VehiculoPersonaController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JETTSUSHD
 */
public class Principal extends javax.swing.JFrame {

    //Inicializo el controlador
    private VehiculoPersonaController vehiculoPersonaController;

    //Creo el modelo para la tabla
    private static DefaultTableModel modeloTabla;

    //creo el modelo para el cbbox
    DefaultComboBoxModel<String> modeloCbBox;

    private boolean aplicarFiltroAvanzado = false;

    //Llevar la cuenta de la pagina
    private int paginaActual = 1;

    public Principal() {
        initComponents();

        pFiltrosAvanzados.setVisible(false);
        setTitle("Registro de Vehículos");
        buttonGroup1.add(rbHombre);
        buttonGroup1.add(rbMujer);

        if (modeloTabla == null) {
            modeloTabla = new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"Nombre", "Matricula", "Año", "Marca", "Modelo", "Nº propietarios"});
        }

        JTable1.setModel(modeloTabla);

        modeloCbBox = new DefaultComboBoxModel<>();
        cbMarca.setModel(modeloCbBox);
        jScrollPane1.setPreferredSize(new Dimension(800, 190));

        // Pasamos la tabla como parametroesté pasando correctamente al controlador
        vehiculoPersonaController = new VehiculoPersonaController(this);

        // Llenar la tabla con datos iniciales
        vehiculoPersonaController.mostrarPersonasConVehiculos();
        vehiculoPersonaController.mostrarcbMarcaEnVistaPrin();

        //Dejo en invisible el boton hasta que se cumpla la sentencia
        bMostrarRegistros.setEnabled(false);

        //mostrar pagina actual
        lPagina.setText("Página: " + paginaActual);
        this.pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Maximiza la ventana para que las tablas se vean bien
        keyBinding();
        JTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void keyBinding() {
        JRootPane rootpane = getRootPane();

        // Acción para cerrar la ventana con ESCAPE
        Action cerrarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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

        // Acción para aplicar los filtros cuando se presiona ENTER
        Action aplicarFiltrosAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bAplicarFiltros.doClick(); // Simula un click en aplicar filtro
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

        // Añadir keybinding para la tecla ENTER para aplicar los filtros
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "APLICAR_FILTROS");
        rootpane.getActionMap().put("APLICAR_FILTROS", aplicarFiltrosAction);

        requestFocusInWindow();
        setFocusable(true);
    }

    //getter para que acceda el controlador
    public javax.swing.JTable getJTable1() {
        return JTable1;
    }

    public JComboBox<String> getcbModelo() {
        return cbModelo;
    }

    public JComboBox<String> getcbMarca() {
        return cbMarca;
    }

    public JButton getbanterior() {
        return banterior;
    }

    public JButton getbsiguiente() {
        return bsiguiente;
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
        Pfil = new javax.swing.JPanel();
        pFiltros = new javax.swing.JPanel();
        lNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        rbHombre = new javax.swing.JRadioButton();
        rbMujer = new javax.swing.JRadioButton();
        lMarca = new javax.swing.JLabel();
        cbMarca = new javax.swing.JComboBox<>();
        pFilColor = new javax.swing.JPanel();
        lAbrirFiltros = new javax.swing.JLabel();
        lFiltrosAvanzados = new javax.swing.JLabel();
        bAplicarFiltros = new javax.swing.JButton();
        pFiltrosAvanzados = new javax.swing.JPanel();
        lModelo = new javax.swing.JLabel();
        cbModelo = new javax.swing.JComboBox<>();
        lAnoNacimiento = new javax.swing.JLabel();
        tfAnoMatriculacion = new javax.swing.JTextField();
        lNumVehiculos = new javax.swing.JLabel();
        tfNumVehiculos = new javax.swing.JTextField();
        pTodo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable1 = new javax.swing.JTable();
        pPaginacion = new javax.swing.JPanel();
        banterior = new javax.swing.JButton();
        lPagina = new javax.swing.JLabel();
        bsiguiente = new javax.swing.JButton();
        pBotones = new javax.swing.JPanel();
        bBorrarFila = new javax.swing.JButton();
        bLimpiarFiltros = new javax.swing.JButton();
        bMostrarRegistros = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Pfil.setLayout(new java.awt.BorderLayout());

        pFiltros.setLayout(new java.awt.GridBagLayout());

        lNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lNombre.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(lNombre, gridBagConstraints);

        tfNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tfNombre.setPreferredSize(new java.awt.Dimension(150, 40));
        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });
        tfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfNombreKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(tfNombre, gridBagConstraints);

        rbHombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbHombre.setText("Hombre");
        rbHombre.setContentAreaFilled(false);
        rbHombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHombreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(rbHombre, gridBagConstraints);

        rbMujer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbMujer.setText("Mujer");
        rbMujer.setContentAreaFilled(false);
        rbMujer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMujerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(rbMujer, gridBagConstraints);

        lMarca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lMarca.setText("Marca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(lMarca, gridBagConstraints);

        cbMarca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(cbMarca, gridBagConstraints);

        lAbrirFiltros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lAbrirFiltros.setText("+");
        lAbrirFiltros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAbrirFiltrosMouseClicked(evt);
            }
        });
        pFilColor.add(lAbrirFiltros);

        lFiltrosAvanzados.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lFiltrosAvanzados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lFiltrosAvanzados.setText("Filtros avanzados");
        pFilColor.add(lFiltrosAvanzados);

        pFiltros.add(pFilColor, new java.awt.GridBagConstraints());

        bAplicarFiltros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bAplicarFiltros.setText("Aplicar filtros");
        bAplicarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAplicarFiltrosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pFiltros.add(bAplicarFiltros, gridBagConstraints);

        Pfil.add(pFiltros, java.awt.BorderLayout.PAGE_START);

        lModelo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lModelo.setText("Modelo:");
        pFiltrosAvanzados.add(lModelo);

        cbModelo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModeloActionPerformed(evt);
            }
        });
        pFiltrosAvanzados.add(cbModelo);

        lAnoNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lAnoNacimiento.setText("Año de Matriculación");
        pFiltrosAvanzados.add(lAnoNacimiento);

        tfAnoMatriculacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tfAnoMatriculacion.setPreferredSize(new java.awt.Dimension(150, 40));
        pFiltrosAvanzados.add(tfAnoMatriculacion);

        lNumVehiculos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lNumVehiculos.setText("Nº de Vehiculos");
        pFiltrosAvanzados.add(lNumVehiculos);

        tfNumVehiculos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tfNumVehiculos.setPreferredSize(new java.awt.Dimension(150, 40));
        pFiltrosAvanzados.add(tfNumVehiculos);

        Pfil.add(pFiltrosAvanzados, java.awt.BorderLayout.CENTER);

        getContentPane().add(Pfil, java.awt.BorderLayout.NORTH);

        pTodo.setLayout(new java.awt.GridBagLayout());

        JTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        JTable1.setMinimumSize(new java.awt.Dimension(100, 80));
        JTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 6, 0);
        pTodo.add(jScrollPane1, gridBagConstraints);

        pPaginacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        banterior.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        banterior.setText("Anterior");
        banterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banteriorActionPerformed(evt);
            }
        });
        pPaginacion.add(banterior);

        lPagina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPagina.setText("Página: 1");
        pPaginacion.add(lPagina);

        bsiguiente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bsiguiente.setText("Siguiente");
        bsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsiguienteActionPerformed(evt);
            }
        });
        pPaginacion.add(bsiguiente);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pTodo.add(pPaginacion, gridBagConstraints);

        pBotones.setLayout(new java.awt.BorderLayout(20, 100));

        bBorrarFila.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bBorrarFila.setText("Borrar fila");
        bBorrarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBorrarFilaActionPerformed(evt);
            }
        });
        pBotones.add(bBorrarFila, java.awt.BorderLayout.CENTER);

        bLimpiarFiltros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bLimpiarFiltros.setText("Limpiar filtros");
        bLimpiarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarFiltrosActionPerformed(evt);
            }
        });
        pBotones.add(bLimpiarFiltros, java.awt.BorderLayout.PAGE_START);

        bMostrarRegistros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bMostrarRegistros.setText("Mostrar Registros");
        bMostrarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMostrarRegistrosActionPerformed(evt);
            }
        });
        pBotones.add(bMostrarRegistros, java.awt.BorderLayout.PAGE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pTodo.add(pBotones, gridBagConstraints);

        getContentPane().add(pTodo, java.awt.BorderLayout.CENTER);

        jMenu4.setText("Opciones");

        jMenuItem3.setText("Crear Persona");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem1.setText("Crear vehiculo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Asociar Vehiculo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbHombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbHombreActionPerformed

    }//GEN-LAST:event_rbHombreActionPerformed

    private void rbMujerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMujerActionPerformed

    }//GEN-LAST:event_rbMujerActionPerformed

    private void tfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreActionPerformed

    private void lAbrirFiltrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAbrirFiltrosMouseClicked
        if (pFiltrosAvanzados.isVisible()) {
            // Si el panel de filtros avanzados ya está visible, ocultarlo
            pFiltrosAvanzados.setVisible(false);
            pFilColor.setBackground(null);
            lAbrirFiltros.setText("+");
            aplicarFiltroAvanzado = false;

            // Resetear el ComboBox a "Todos" cuando el panel se oculta
            cbModelo.setSelectedItem("Todos");

        } else {
            // Si el panel de filtros avanzados no está visible, mostrarlo
            pFiltrosAvanzados.setVisible(true);
            pFilColor.setBackground(Color.cyan);
            lAbrirFiltros.setText("−");
            aplicarFiltroAvanzado = true;
        }
    }//GEN-LAST:event_lAbrirFiltrosMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        viewCrearVehiculo vistaCrearVehiculo = new viewCrearVehiculo();
        vistaCrearVehiculo.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bAplicarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAplicarFiltrosActionPerformed
        // Restablecer la página actual a 1 cuando se aplica un filtro
        paginaActual = 1;
        lPagina.setText("Página: " + paginaActual);

        

        // Obtener y validar los valores de los filtros de la vista
        String nombre = tfNombre.getText().trim();

        // Validar el nombre solo si no está vacío
        if (!nombre.isEmpty()) {
            if (!nombre.matches("^[\\p{L}]+(\\s[\\p{L}]+)*$")) { // Solo letras Unicode (incluye tildes) y espacios
                JOptionPane.showMessageDialog(null,
                        "El nombre solo puede contener letras y espacios.",
                        "Entrada inválida",
                        JOptionPane.ERROR_MESSAGE);
                tfNombre.setText("");
                return;
            }

            // Bloquear palabras clave en el nombre
            if (nombre.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
                JOptionPane.showMessageDialog(null,
                        "El nombre no puede contener palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc.",
                        "Entrada inválida",
                        JOptionPane.ERROR_MESSAGE);
                tfNombre.setText("");
                return;
            }
        }

        String marca = (String) cbMarca.getSelectedItem();
        String modeloVehiculo = (String) cbModelo.getSelectedItem();
        String genero = rbHombre.isSelected() ? "H" : (rbMujer.isSelected() ? "M" : null);
        Integer año = null;
        Integer numPropietarios = null;

        // Verificar si el panel de filtros avanzados es visible
        boolean filtrosAvanzadosVisible = pFiltrosAvanzados.isVisible();

        // Si el panel de filtros avanzados no está visible, resetear los campos
        if (!filtrosAvanzadosVisible) {
            tfAnoMatriculacion.setText("");  // Limpiar el campo de Año
            cbModelo.setSelectedItem("Todos"); // Establecer "Todos" en ComboBox de Modelo
            tfNumVehiculos.setText("");
        }

        // Manejo de la entrada numérica de año
        try {
            if (!tfAnoMatriculacion.getText().trim().isEmpty()) {
                String añoTexto = tfAnoMatriculacion.getText().trim();
                if (añoTexto.length() == 4 && añoTexto.matches("\\d{4}")) {
                    año = Integer.parseInt(añoTexto);
                    int añoActual = Calendar.getInstance().get(Calendar.YEAR);

                    // Validar que el año no sea menor a 1900
                    if (año < 1900) {
                        JOptionPane.showMessageDialog(null,
                                "El año no puede ser menor a 1900.",
                                "Entrada inválida",
                                JOptionPane.ERROR_MESSAGE);
                        tfAnoMatriculacion.setText("");
                    } // Validar que el año no sea mayor que el año actual
                    else if (año > añoActual) {
                        JOptionPane.showMessageDialog(null,
                                "El año no puede ser mayor al año actual.",
                                "Entrada inválida",
                                JOptionPane.ERROR_MESSAGE);
                        tfAnoMatriculacion.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El año debe ser un valor numérico de 4 dígitos.",
                            "Entrada inválida",
                            JOptionPane.ERROR_MESSAGE);
                    tfAnoMatriculacion.setText("");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: El número de año debe ser un valor numérico de 4 dígitos.",
                    "Entrada inválida",
                    JOptionPane.ERROR_MESSAGE);
            tfAnoMatriculacion.setText("");
        }

        // Manejo de la entrada numérica de num propietarios
        try {
            if (!tfNumVehiculos.getText().trim().isEmpty()) {
                String numPropietariosTexto = tfNumVehiculos.getText().trim();
                if (numPropietariosTexto.matches("\\d+")) { // Solo números
                    numPropietarios = Integer.parseInt(numPropietariosTexto);

                    // Validar que esté en el rango de 1 a 1000
                    if (numPropietarios < 1 || numPropietarios > 1000) {
                        JOptionPane.showMessageDialog(null,
                                "El número de propietarios debe estar entre 1 y 1000.",
                                "Entrada inválida",
                                JOptionPane.ERROR_MESSAGE);
                        tfNumVehiculos.setText("");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El número de propietarios debe contener solo números.",
                            "Entrada inválida",
                            JOptionPane.ERROR_MESSAGE);
                    tfNumVehiculos.setText("");
                    return;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: El número de propietarios debe ser un valor numérico.",
                    "Entrada inválida",
                    JOptionPane.ERROR_MESSAGE);
            tfNumVehiculos.setText("");
        }

        // Llamada al controlador para aplicar los filtros
        vehiculoPersonaController.aplicarFiltros(nombre, marca, modeloVehiculo, genero, año, numPropietarios);
    }//GEN-LAST:event_bAplicarFiltrosActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        viewAsociarVehiculo viewAsociarVehiculo = new viewAsociarVehiculo();
        viewAsociarVehiculo.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void cbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcaActionPerformed
        String marcaSeleccionada = (String) cbMarca.getSelectedItem();
        vehiculoPersonaController.mostrarModelosPorMarcaVistaPrincipal(marcaSeleccionada);
    }//GEN-LAST:event_cbMarcaActionPerformed

    private void cbModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModeloActionPerformed

    }//GEN-LAST:event_cbModeloActionPerformed

    private void bsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsiguienteActionPerformed
        vehiculoPersonaController.mostrarPaginaSiguiente();
        paginaActual++;
        lPagina.setText("Página: " + paginaActual);
    }//GEN-LAST:event_bsiguienteActionPerformed

    private void bLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarFiltrosActionPerformed
 // Limpiar los campos de filtro
    tfNombre.setText("");
    cbMarca.setSelectedIndex(0);
    cbModelo.setSelectedIndex(0);
    tfAnoMatriculacion.setText("");
    buttonGroup1.clearSelection();
    tfNumVehiculos.setText("");

    // Desactivar los filtros y reiniciar los valores almacenados en el controlador
    vehiculoPersonaController.resetearFiltros();

    // Reiniciar la paginación a la primera página tanto en la vista como en el controlador
    paginaActual = 1;  
    vehiculoPersonaController.reiniciarPaginacion(); // Llama a un método que reinicia paginaActual en el controlador
    lPagina.setText("Página: " + paginaActual); // Actualiza el texto del JLabel

    // Llamar al controlador para cargar todos los vehículos sin filtros
    vehiculoPersonaController.mostrarPersonasConVehiculos();
    }//GEN-LAST:event_bLimpiarFiltrosActionPerformed

    private void bBorrarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBorrarFilaActionPerformed
        VehiculoPersonaController personaVehiculo = new VehiculoPersonaController(this);
        personaVehiculo.eliminarVehiculoSeleccionado();
    }//GEN-LAST:event_bBorrarFilaActionPerformed

    private void banteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banteriorActionPerformed
        if (paginaActual > 1) {
            //mostrar pagina anterior mientras sea mayor de 1
            vehiculoPersonaController.mostrarPaginaAnterior();
            //disminuir contador
            paginaActual--;
            lPagina.setText("Página: " + paginaActual);
        }
    }//GEN-LAST:event_banteriorActionPerformed

    private void bMostrarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMostrarRegistrosActionPerformed
        int filaSeleccionada = JTable1.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtener la matrícula de la fila seleccionada
            String matricula = (String) JTable1.getValueAt(filaSeleccionada, 1);

            // Llama al controlador para mostrar los detalles del vehículo
            vehiculoPersonaController.mostrarDetallesVehiculo(matricula);
        }
    }//GEN-LAST:event_bMostrarRegistrosActionPerformed

    private void JTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable1MouseClicked
        int filaSeleccionada = JTable1.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtener el número de propietarios de la fila seleccionada
            int numeroPropietarios = (int) JTable1.getValueAt(filaSeleccionada, 5);

            // Habilitar el botón solo si el número de propietarios es mayor a 1
            bMostrarRegistros.setEnabled(numeroPropietarios > 1);
        } else {
            // Deshabilitar el botón si no hay ninguna fila seleccionada
            bMostrarRegistros.setEnabled(false);
        }

    }//GEN-LAST:event_JTable1MouseClicked

    private void tfNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bAplicarFiltros.doClick();
        }
    }//GEN-LAST:event_tfNombreKeyPressed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        viewCrearPersona vistaCrearPersona = new viewCrearPersona(null);
        vistaCrearPersona.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro de cerrar la ventana?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
            System.out.println("Se ha cerrado corectamente");
        } else if (confirm == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        }
    }//GEN-LAST:event_formWindowClosing

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentResized

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable1;
    private javax.swing.JPanel Pfil;
    private javax.swing.JButton bAplicarFiltros;
    private javax.swing.JButton bBorrarFila;
    private javax.swing.JButton bLimpiarFiltros;
    private javax.swing.JButton bMostrarRegistros;
    private javax.swing.JButton banterior;
    private javax.swing.JButton bsiguiente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbMarca;
    private javax.swing.JComboBox<String> cbModelo;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lAbrirFiltros;
    private javax.swing.JLabel lAnoNacimiento;
    private javax.swing.JLabel lFiltrosAvanzados;
    private javax.swing.JLabel lMarca;
    private javax.swing.JLabel lModelo;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lNumVehiculos;
    private javax.swing.JLabel lPagina;
    private javax.swing.JPanel pBotones;
    private javax.swing.JPanel pFilColor;
    private javax.swing.JPanel pFiltros;
    private javax.swing.JPanel pFiltrosAvanzados;
    private javax.swing.JPanel pPaginacion;
    private javax.swing.JPanel pTodo;
    private javax.swing.JRadioButton rbHombre;
    private javax.swing.JRadioButton rbMujer;
    private javax.swing.JTextField tfAnoMatriculacion;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfNumVehiculos;
    // End of variables declaration//GEN-END:variables
}
