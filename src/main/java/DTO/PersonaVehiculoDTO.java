/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

public class PersonaVehiculoDTO {

    private int id;
    private int id_persona;
    private int id_vehiculo;
    private String nombre;
    private String dni;
    private char genero;
    private String matricula;
    private int año;
    private String marca;
    private String modelo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int numeroVehiculos;

    public PersonaVehiculoDTO() {
    }

    public PersonaVehiculoDTO(String nombre, String matricula, int año, String marca, String modelo) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
    }

    public PersonaVehiculoDTO(String nombre, String matricula, char genero, int año, String marca) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.genero = genero;
        this.año = año;
        this.marca = marca;
    }

    public PersonaVehiculoDTO(String nombre, char genero, Date fecha_inicio, Date fecha_fin) {
        this.nombre = nombre;
        this.genero = genero;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
    
    //constructor para asociar
    public PersonaVehiculoDTO(int id, int id_persona, int id_vehiculo, String nombre,String matricula, 
            int año, String marca, String modelo, char genero, Date fecha_inicio) {
        this.id = id;
        this.id_persona = id_persona;
        this.id_vehiculo = id_vehiculo;
        this.nombre = nombre;
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
        this.genero = genero;
        this.fecha_inicio = fecha_inicio;
        
    }

    // Constructor con todos los campos
    public PersonaVehiculoDTO(int id, int id_persona, int id_vehiculo, String nombre, String dni, char genero,
            String matricula, int año, String marca, String modelo, Date fecha_inicio, Date fecha_fin) {
        this.id = id;
        this.id_persona = id_persona;
        this.id_vehiculo = id_vehiculo;
        this.nombre = nombre;
        this.dni = dni;
        this.genero = genero;
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getNumeroVehiculos() {
        return numeroVehiculos;
    }

    public void setNumeroVehiculos(int numeroVehiculos) {
        this.numeroVehiculos = numeroVehiculos;
    }

}
