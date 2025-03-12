/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Vehiculo {

    private int id;
    private String matricula;
    private int año;
    private String marca;
    private String modelo;
    public Vehiculo() {
    }

    public Vehiculo(String matricula, int año, String marca, String modelo) {
        this.matricula = matricula;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Vehiculo(int id, String matricula, int ano, String marca, String modelo) {
        this.id = id;
        this.matricula = matricula;
        this.año = ano;
        this.marca = marca;
        this.modelo = modelo;
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

    public int getAno() {
        return año;
    }

    public void setAno(int ano) {
        this.año = ano;
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

}
