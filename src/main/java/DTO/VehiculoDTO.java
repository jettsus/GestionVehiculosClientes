/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author JETTSUSHD
 */
public class VehiculoDTO {
    private int id;
    private String matricula;
    private int año;
    private String marca;
    private String modelo;
    private int id_persona;

    public VehiculoDTO() {
    }

    public VehiculoDTO(int id, String matricula, int año, String marca, String modelo, int id_persona) {
        this.id = id;
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
        this.id_persona = id_persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    @Override
    public String toString() {
        return "VehiculoDTO{" + "id=" + id + ", matricula=" + matricula + ", a\u00f1o=" + año + ", marca=" + marca + ", modelo=" + modelo + ", id_persona=" + id_persona + '}';
    }
    
}
