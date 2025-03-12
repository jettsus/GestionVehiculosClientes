/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Vehiculo;
import service.serviceVehiculo;
import view.viewAsociarVehiculo;

/**
 *
 * @author JETTSUSHD
 */
public class VehiculoController {

    private serviceVehiculo serviceVehiculo; // Instancia del servicio de vehículos
    private viewAsociarVehiculo viewAsociarVehiculo;

    public VehiculoController() {
        this.serviceVehiculo = new serviceVehiculo();
    }

    public VehiculoController(viewAsociarVehiculo viewAsociarVehiculo) {
        this.viewAsociarVehiculo = viewAsociarVehiculo;
        this.serviceVehiculo = new serviceVehiculo();
    }

    // Método para cargar los vehículos en la tabla
    public void cargarVehiculosEnLaTabla() {
        List<Vehiculo> vehiculos = serviceVehiculo.obtenerVehiculos();
        // Obtener el modelo de la tabla
        DefaultTableModel modeloTablaVehiculo = (DefaultTableModel) viewAsociarVehiculo.getTablaVehiculo().getModel();
        // Limpiar la tabla antes de agregar nuevos datos
        modeloTablaVehiculo.setRowCount(0);

        // Verificar si la lista de vehículos no está vacía
        if (vehiculos != null && !vehiculos.isEmpty()) {
            // Recorrer la lista de vehículos y agregar cada uno a la tabla
            for (Vehiculo vehiculo : vehiculos) {
                // Agregar una fila a la tabla con los datos del vehículo
                modeloTablaVehiculo.addRow(new Object[]{
                    vehiculo.getMatricula(),
                    vehiculo.getAno(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                });
            }
        } else {
            // Si no hay vehículos, mostrar un mensaje en consola (opcional)
            System.out.println("No hay vehículos disponibles.");
        }
    }

    // Método para agregar un nuevo vehículo, con los datos pasados como parámetros
    public void agregarVehiculo(String matricula, int ano, String marca, String modelo) {
        Vehiculo vehiculo = new Vehiculo();

        // Establecer los valores del vehículo
        vehiculo.setMatricula(matricula);
        vehiculo.setAno(ano);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);

        // Llamar al servicio para insertar el vehículo
        try {
            serviceVehiculo.insertarVehiculo(vehiculo);
            System.out.println("Vehículo agregado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar el vehículo: " + e.getMessage());
        }
    }

    // Método para actualizar un vehículo, con los nuevos datos pasados como parámetros
    public void actualizarVehiculo(int id, String matricula, int ano, String marca, String modelo) {
        // Obtener el vehículo actual mediante el ID
        Vehiculo vehiculo = serviceVehiculo.obtenerVehiculoPorId(id);

        if (vehiculo != null) {
            // Establecer los nuevos valores del vehículo
            vehiculo.setMatricula(matricula);
            vehiculo.setAno(ano);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);

            // Llamar al servicio para actualizar el vehículo
            try {
                serviceVehiculo.actualizarVehiculo(vehiculo);
                System.out.println("Vehículo actualizado correctamente.");
            } catch (Exception e) {
                System.out.println("Error al actualizar el vehículo: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró un vehículo con ID " + id);
        }
    }

    // Método para eliminar un vehículo, con el ID pasado como parámetro
    public void eliminarVehiculo(int id) {
        try {
            serviceVehiculo.eliminarVehiculo(id);
            System.out.println("Vehículo eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el vehículo: " + e.getMessage());
        }
    }
    
    
    //cargar Vehiculos sin personas
    public void cargarVehiculosSinAsignacionEnTabla() {
        List<Vehiculo> vehiculosSinAsignacion = serviceVehiculo.obtenerVehiculosSinAsignacion();
        DefaultTableModel modelo = (DefaultTableModel) viewAsociarVehiculo.getTablaVehiculo().getModel();
        modelo.setRowCount(0);

        for (Vehiculo vehiculo : vehiculosSinAsignacion) {
            modelo.addRow(new Object[]{
                vehiculo.getMatricula(), 
                vehiculo.getAno(), 
                vehiculo.getMarca(), 
                vehiculo.getModelo()
            });
        }
    }
    
    public boolean verificarMatriculaExiste(String matricula) {
    boolean existe = false;

    if (matricula == null || matricula.isEmpty()) {
        JOptionPane.showMessageDialog(null,
                "La matrícula no puede estar vacía.",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
    } else {
        existe = serviceVehiculo.matriculaExiste(matricula);

        if (existe) {
            JOptionPane.showMessageDialog(null,
                    "La matrícula ya está registrada en el sistema.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    return existe;
}

}
