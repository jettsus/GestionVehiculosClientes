/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.servicePersona;
import model.Persona;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.viewAsociarVehiculo;

public class PersonaController {

    private servicePersona personaService;
    private viewAsociarVehiculo viewAsociarVehiculo;

    public PersonaController() {
        this.personaService = new servicePersona();
    }

    public PersonaController(viewAsociarVehiculo viewAsociarVehiculo) {
        this.viewAsociarVehiculo = viewAsociarVehiculo;
        this.personaService = new servicePersona();
    }

    // Método para cargar los vehículos en la tabla
    public void cargarPersonasEnLaTabla() {
        List<Persona> personas = personaService.obtenerPersonas();
        DefaultTableModel modeloTablaVehiculo = (DefaultTableModel) viewAsociarVehiculo.getTablaPersona().getModel();
        modeloTablaVehiculo.setRowCount(0);

        // Verificar si la lista de vehículos no está vacía
        if (personas != null && !personas.isEmpty()) {
            // Recorrer la lista de personas y agregar cada uno a la tabla
            for (Persona persona : personas) {
                // Agregar una fila a la tabla con los datos del persona
                modeloTablaVehiculo.addRow(new Object[]{
                    persona.getNombre(),
                    persona.getDni(),
                    persona.getGenero()

                });
            }
        } else {
            System.out.println("No hay vehículos disponibles.");
        }
    }

    //Metodo para cargar personas sin vehiculo 
    public void cargarPersonasSinVehiculoEnTabla() {
        //cargar lista con personas sin vehiculo
        List<Persona> personasSinVehiculo = personaService.obtenerPersonasSinVehiculo();
        //recojo tabla persona y el modelo
        DefaultTableModel modeloTablaVehiculo = (DefaultTableModel) viewAsociarVehiculo.getTablaPersona().getModel();
        modeloTablaVehiculo.setRowCount(0);

        // Verificar si la lista de vehículos no está vacía
        if (personasSinVehiculo != null && !personasSinVehiculo.isEmpty()) {
            // Recorrer la lista de personas y agregar cada uno a la tabla
            for (Persona persona : personasSinVehiculo) {
                // Agregar una fila a la tabla con los datos del persona
                modeloTablaVehiculo.addRow(new Object[]{
                    persona.getNombre(),
                    persona.getDni(),
                    persona.getGenero()

                });
            }
        } else {
            System.out.println("No hay vehículos disponibles.");
        }
    }
    
    // Método para insertar una nueva persona en la base de datos utilizando el servicio
    public void insertarPersona(Persona persona) {
        try {
            // Utilizamos el servicio para insertar la persona
            personaService.insertarPersona(persona);
            System.out.println("Persona insertada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        }
    }
    
     public boolean verificarDniExiste(String dni) {
        boolean existe = false;

        if (dni == null || dni.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El DNI no puede estar vacío.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            existe = personaService.dniExiste(dni);

            if (existe) {
                JOptionPane.showMessageDialog(null,
                        "El DNI ya está registrado en el sistema.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return existe;
    }
}
