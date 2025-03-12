/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PersonaDAO extends GenericDAO<Persona> {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    // Método para limpiar y validar el nombre de una persona
    private String limpiarEntradaNombre(String nombre) {
        boolean esValido = true;

        // Validar que el nombre no sea nulo y que cumpla con el formato de letras y espacios, entre 1 y 40 caracteres
        if (nombre == null || !nombre.matches("^[\\p{L}\\s]{1,40}$")) {
            JOptionPane.showMessageDialog(null,
                    "El nombre debe tener entre 1 y 40 caracteres, sin números, símbolos especiales y solo letras con espacios permitidos.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Validar que no contenga palabras clave peligrosas (mayúsculas o minúsculas)
        if (esValido && nombre.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
            JOptionPane.showMessageDialog(null,
                    "El nombre contiene palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc., lo cual no está permitido.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Si no es válido, devolver null
        if (!esValido) {
            return null;
        }

        // Si es válido, devolver el nombre limpio
        return nombre.trim();
    }

// Método para limpiar y validar el DNI
    private String limpiarEntradaDNI(String dni) {
        boolean esValido = true;

        // Validar que no sea nulo y cumpla con el formato 8 números seguidos de una letra
        if (dni == null || !dni.matches("^[0-9]{8}[A-Za-z]$")) {
            JOptionPane.showMessageDialog(null,
                    "El DNI debe tener exactamente 8 números seguidos por una letra.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Validar que no contenga palabras clave peligrosas
        if (esValido && dni.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
            JOptionPane.showMessageDialog(null,
                    "El DNI contiene palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc., lo cual no está permitido.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        }

        // Si no es válido, devolver null
        if (!esValido) {
            return null;
        }

        // Si es válido, devolver el DNI limpio
        return dni.trim();
    }

// Método para insertar una nueva persona en la base de datos
    @Override
    public void insertar(Persona persona) {
        String sql = "INSERT INTO personas (nombre, dni, genero) VALUES (?, ?, ?)";
        try {
            // Abre la conexión y prepara la sentencia SQL
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);

            // Limpiar y validar las entradas antes de usarlas en la consulta
            String nombreLimpio = limpiarEntradaNombre(persona.getNombre());
            String dniLimpio = limpiarEntradaDNI(persona.getDni());

            System.out.println(pstmt);
            // Si los datos no son válidos, no ejecutar la inserción
            if (nombreLimpio == null || dniLimpio == null) {
                System.out.println("Error: Los datos de la persona no son válidos. No se realizará la inserción.");
                return;
            }

            // Establecer los valores en el PreparedStatement
            pstmt.setString(1, nombreLimpio);
            pstmt.setString(2, dniLimpio);
            pstmt.setString(3, String.valueOf(persona.getGenero()));

            // Ejecutar la inserción en la base de datos
            pstmt.executeUpdate();

            System.out.println("Persona insertada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear persona: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            // Cierra recursos de base de datos
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
    }

    // Método para buscar una persona por su ID
    @Override
    public Persona encontrarPorId(int id) {
        String QUERY = "SELECT * FROM personas WHERE id = ?";
        Persona persona = null;
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY);

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            // Crear objeto Persona si se encuentra el registro
            if (rs.next()) {
                String generostr = rs.getString("Genero");
                char genero = generostr.charAt(0);

                persona = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni"), genero);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer persona: " + e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return persona;
    }

    // Método para obtener todas las personas en la base de datos
    @Override
    public List<Persona> mostrar() {
        String sql = "SELECT * FROM personas";
        List<Persona> personas = new ArrayList<>();
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Itera sobre los resultados y crea objetos Persona
            while (rs.next()) {
                String generostr = rs.getString("Genero");
                char genero = generostr.charAt(0);
                Persona persona = new Persona(rs.getInt("Id"), rs.getString("DNI"), rs.getString("Nombre"), genero);
                personas.add(persona);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer coche: " + e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return personas;
    }

    // Método para actualizar una persona en la base de datos
    @Override
    public void actualizar(Persona persona) {
        String QUERY = "UPDATE personas SET DNI = ?,  Nombre = ?, Genero = ?";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY);

            // Establece valores en la sentencia preparada
            pstmt.setString(2, persona.getNombre());
            pstmt.setString(1, persona.getDni());
            pstmt.setString(3, String.valueOf(persona.getGenero()));

            // Ejecuta la actualización
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
    }

    // Método para borrar una persona de la base de datos
    @Override
    public void borrar(Persona persona) {
        String QUERY = "DELETE FROM personas WHERE Id = ?";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY);

            pstmt.setInt(1, persona.getId());

            int filaAfectada = pstmt.executeUpdate();
            // Verifica si el registro fue encontrado y eliminado
            if (filaAfectada > 0) {
                System.out.println("Registro eliminado correctamente.");
            } else {
                System.out.println("No se encontró un registro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
    }

    // Método para obtener personas que no tienen vehículo asignado
    public List<Persona> obtenerPersonasSinVehiculo() {
        List<Persona> personasSinVehiculo = new ArrayList<>();
        String query = "SELECT * FROM personas p WHERE NOT EXISTS (SELECT 1 FROM historico h WHERE h.id_persona = p.id AND h.fecha_fin IS NULL)";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("genero").charAt(0)
                );
                personasSinVehiculo.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return personasSinVehiculo;
    }

    // Método para obtener el ID de una persona a partir de su DNI
    public int obtenerIdPorDNI(String dni) {
        int id = -1;
        String sql = "SELECT id FROM personas WHERE dni = ?";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dni); // Establecer el valor de 'dni' en el PreparedStatement
            rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return id;
    }

    public boolean dniExiste(String dni) {
        boolean existe = false; // Inicializamos la variable de retorno como false
        String sql = "SELECT COUNT(*) FROM personas WHERE dni = ?";

        try (Connection conn = ConexionBDD.abrirConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    existe = true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return existe;
    }

}
