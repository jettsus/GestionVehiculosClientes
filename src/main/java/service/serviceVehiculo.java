/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.VehiculoDAO;
import java.util.List;
import model.Vehiculo;

/**
 *
 * @author JETTSUSHD
 */
public class serviceVehiculo {
    private VehiculoDAO vehiculoDAO;

    public serviceVehiculo() {
        this.vehiculoDAO = new VehiculoDAO();
    }

    /**
     * Método para insertar un vehículo en la base de datos.
     */
    public void insertarVehiculo(Vehiculo vehiculo) {
        try {
            vehiculoDAO.insertar(vehiculo);
        } catch (Exception e) {
            System.out.println("Error al insertar vehiculo: " + e.getMessage());
        }
    }

    /**
     * Método para actualizar un vehículo en la base de datos.
     */
    public void actualizarVehiculo(Vehiculo vehiculo) {
        try {
            vehiculoDAO.actualizar(vehiculo);
        } catch (Exception e) {
            System.out.println("Error al actualizar vehiculo: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar un vehículo de la base de datos.
     */
    public void eliminarVehiculo(int id) {
        try {
            Vehiculo vehiculo = vehiculoDAO.encontrarPorId(id);
            if (vehiculo != null) {
                vehiculoDAO.borrar(vehiculo);
            } else {
                System.out.println("Vehiculo no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar vehiculo: " + e.getMessage());
        }
    }

    /**
     * Método para obtener una lista de todos los vehículos.
     */
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> vehiculos = null; 

        try {
            vehiculos = vehiculoDAO.mostrar();
        } catch (Exception e) {
            System.out.println("Error al obtener vehiculos: " + e.getMessage());
        }
        return vehiculos;
    }

    /**
     * Método para obtener un vehículo por su ID.
     */
    public Vehiculo obtenerVehiculoPorId(int id) {
        Vehiculo vehiculo = null;
        try {
            vehiculo = vehiculoDAO.encontrarPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener vehiculo por ID: " + e.getMessage());
        }
        return vehiculo;
    }
    
    /**
     * Método para obtener vehiculos sin personas.
     */
    public List<Vehiculo> obtenerVehiculosSinAsignacion() {
        return vehiculoDAO.obtenerVehiculosSinAsignacion();
    }
    
    //Metodo para obtener id vehiculo en funcion de matricula
    public int obtenerIdVehiculoPorMatricula(String matricula) {
        return vehiculoDAO.obtenerIdPorMatricula(matricula);
    }
    
    public boolean matriculaExiste(String matricula) {
    return vehiculoDAO.existeMatricula(matricula);
}

}
