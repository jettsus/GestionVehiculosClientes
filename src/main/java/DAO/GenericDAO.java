/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package DAO;

import java.util.List;
public abstract class GenericDAO<T> {

    // Método para insertar un objeto
    public abstract void insertar(T objeto);

    // Método para borrar un objeto 
    public abstract void borrar(T objeto);

    // Método para mostrar todos los objetos 
    public abstract List<T> mostrar(); 

    // Método para actualizar un objeto
    public abstract void actualizar(T objeto);

    // Método para encontrar objeto por ID
    public abstract T encontrarPorId(int id);
}


