/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.PersonaVehiculoDAO;
import DTO.PersonaVehiculoDTO;
import java.util.List;

/**
 *
 * @author JETTSUSHD
 */
public class servicePersonaVehiculo {

    private PersonaVehiculoDAO personaVehiculoDAO;

    // Constructor que inicializa el DAO
    public servicePersonaVehiculo() {
        this.personaVehiculoDAO = new PersonaVehiculoDAO();
    }

    // Método para los filtros y la paginación llamando al 
    public List<PersonaVehiculoDTO> filtrarPersonasConVehiculos(String nombre, String marca, String modelo, String genero, Integer año, Integer numVehiculos, int page, int tamanoPagina) {
        int desplazamiento = (page - 1) * tamanoPagina; // Calcular el offset cogiendo página actual y el tamaño de página
        return personaVehiculoDAO.filtrarPersonasConVehiculos(nombre, marca, modelo, genero, año, numVehiculos, tamanoPagina, desplazamiento);
    }

    // Método para obtener personas con sus vehículos con paginación
    public List<PersonaVehiculoDTO> obtenerPersonasConVehiculos(int page, int size) {
        return personaVehiculoDAO.obtenerPersonasConVehiculos(page, size);
    }

    // Método para obtener las marcas desde el DAO
    public List<String> obtenerMarcas() {
        return personaVehiculoDAO.obtenerMarcas();
    }

    // Método para obtener los modelos de vehículos según la marca seleccionada
    public List<String> obtenerModelosPorMarca(String marca) {
        return personaVehiculoDAO.obtenerModelosPorMarca(marca);
    }

    //Método para obtener un registro de cada vehiculo a partir de la matricula
    public List<PersonaVehiculoDTO> obtenerDetallesVehiculo(String matricula) {
        return personaVehiculoDAO.obtenerDetallesVehiculo(matricula);
    }

    // Método para obtener el número de propietarios únicos por vehículo
    public int obtenerNumeroPropietariosPorVehiculo(String matricula) {
        return personaVehiculoDAO.obtenerNumeroPropietariosPorVehiculo(matricula);
    }

    //Metodo para obtener la asociacion con transaccion del historico
    public boolean asociarVehiculoConTransaccion(PersonaVehiculoDTO historico) {
        // Llamar al DAO para realizar la transacción 
        return personaVehiculoDAO.asociarVehiculoConTransaccion(historico);
    }

    public boolean eliminarVehiculo(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            System.err.println("Matrícula no válida para eliminación.");
            return false;
        }

        // Llama al DAO para eliminar el vehículo
        return personaVehiculoDAO.eliminarVehiculo(matricula);
    }
}
