/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.PersonaDAO;
import java.util.List;
import model.Persona;

public class servicePersona {

    private PersonaDAO personaDAO;

    public servicePersona() {
        this.personaDAO = new PersonaDAO();
    }

    /**
     * Método para insertar una persona.
     */
    public void insertarPersona(Persona persona) {
        try {
            personaDAO.insertar(persona);
        } catch (Exception e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
        }
    }

    /**
     * Método para actualizar una persona.
     */
    public void actualizarPersona(Persona persona) {
        try {
            personaDAO.actualizar(persona);
        } catch (Exception e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar una persona.
     */
    public void eliminarPersona(int id) {
        try {
            Persona persona = personaDAO.encontrarPorId(id);
            if (persona != null) {
                personaDAO.borrar(persona);
            } else {
                System.out.println("Persona no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
        }
    }

    /**
     * Método para obtener una lista de todas las personas.
     */
    public List<Persona> obtenerPersonas() {
        List<Persona> personas = null; 

        try {
            personas = personaDAO.mostrar();
        } catch (Exception e) {
            System.out.println("Error al obtener personas: " + e.getMessage());
        }
        return personas;
    }

    /**
     * Método para obtener una persona por su ID.
     */
    public Persona obtenerPersonaPorId(int id) {
        Persona persona = null;
        try {
            persona = personaDAO.encontrarPorId(id);
        } catch (Exception e) {
            System.out.println("Error al obtener persona por ID: " + e.getMessage());
        }
        return persona;
    }
    
    /**
     * Método para obtener personas sin vehiculo.
     */
    public List<Persona> obtenerPersonasSinVehiculo() {
        return personaDAO.obtenerPersonasSinVehiculo();
    }
    
    //Metodo para obtener id persona en funcion de su dni
    public int obtenerIdPersonaPorDNI(String dni) {
        return personaDAO.obtenerIdPorDNI(dni);
    }
    
    //Metodo para obtener dni si existe
    public boolean dniExiste(String dni) {
        return personaDAO.dniExiste(dni);
    }
}
