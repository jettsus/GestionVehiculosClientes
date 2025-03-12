/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Vehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

public class VehiculoDAO extends GenericDAO<Vehiculo> {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    // Método para limpiar la entrada de la matrícula y verificar si es válida
    private String limpiarEntradaMatricula(String matricula) {
        boolean esValida = true;

        if (matricula == null || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "La matrícula no puede estar vacía.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValida = false;
        } else if (!matricula.matches("^[A-Za-z0-9]{6,12}$")) {
            JOptionPane.showMessageDialog(null,
                    "La matrícula debe contener solo letras y números, con un mínimo de 6 y un máximo de 12 caracteres.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValida = false;
        } else if (matricula.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
            JOptionPane.showMessageDialog(null,
                    "La matrícula contiene palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc., lo cual no está permitido.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValida = false;
        }

        // Si la matrícula es válida, la devuelve; si no, devuelve null
        return esValida ? matricula : null;
    }

    // Método para validar el año
    private Integer limpiarEntradaAnio(Integer año) {
        boolean esValido = true;

        if (año == null) {
            JOptionPane.showMessageDialog(null,
                    "El año no puede ser nulo.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            esValido = false;
        } else {
            // Convertir el año a cadena para validarlo con una expresión regular
            String añoStr = año.toString();

            // Validar que tenga exactamente 4 dígitos
            if (!añoStr.matches("^[0-9]{4}$")) {
                JOptionPane.showMessageDialog(null,
                        "El año debe ser un número de exactamente 4 dígitos.",
                        "Error de filtro",
                        JOptionPane.ERROR_MESSAGE);
                esValido = false;
            }

            // Validar que no contenga palabras clave peligrosas
            if (añoStr.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
                JOptionPane.showMessageDialog(null,
                        "El año no puede contener palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc.",
                        "Error de filtro",
                        JOptionPane.ERROR_MESSAGE);
                esValido = false;
            }

            // Validar rango entre 1900 y el año actual
            int añoActual = Calendar.getInstance().get(Calendar.YEAR);
            if (año < 1900 || año > añoActual) {
                JOptionPane.showMessageDialog(null,
                        "El año debe estar entre 1900 y " + añoActual + ".",
                        "Error de filtro",
                        JOptionPane.ERROR_MESSAGE);
                esValido = false;
            }
        }

        // Si es válido, devolver el año; si no, devolver null
        return esValido ? año : null;
    }

// Método para insertar un vehículo en la base de datos
    @Override
    public void insertar(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (matricula, año, marca, modelo) VALUES (?, ?, ?, ?)";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);

            // Limpiar las entradas antes de usarlas en la consulta
            String matriculaLimpia = limpiarEntradaMatricula(vehiculo.getMatricula());
            Integer añoLimpio = limpiarEntradaAnio(vehiculo.getAno());

            // Si los datos no son válidos, no ejecutar la inserción
            if (matriculaLimpia == null || añoLimpio == null) {
                System.out.println("Error: Los datos del vehículo no son válidos. No se realizará la inserción.");
                return;
            }

            // Establecer los valores en el PreparedStatement
            pstmt.setString(1, matriculaLimpia);
            pstmt.setInt(2, añoLimpio);
            pstmt.setString(3, vehiculo.getMarca());
            pstmt.setString(4, vehiculo.getModelo());

            int filaAfectada = pstmt.executeUpdate();
            if (filaAfectada > 0) {
                System.out.println("Registro insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el registro.");
            }
        } catch (SQLException x) {
            System.err.println("Error al crear vehículo: " + x.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
    }

    // Método para leer un vehículo por su ID
    @Override
    public Vehiculo encontrarPorId(int id) {
        String sql = "SELECT * FROM vehiculos WHERE id_vehiculo = ?";
        Vehiculo vehiculo = null;
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getInt("id_vehiculo"),
                        rs.getString("matricula"),
                        rs.getInt("año"),
                        rs.getString("marca"),
                        rs.getString("modelo")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al leer vehiculo: " + e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return vehiculo;
    }

    // Método para obtener todos los vehículos
    @Override
    public List<Vehiculo> mostrar() {
        String QUERY = "SELECT * FROM vehiculos";
        List<Vehiculo> vehiculos = new ArrayList<>();

        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("id"),
                        rs.getString("matricula"),
                        rs.getInt("año"),
                        rs.getString("marca"),
                        rs.getString("modelo")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer vehículos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
        return vehiculos;
    }

    // Método para actualizar un vehículo en la base de datos
    @Override
    public void actualizar(Vehiculo vehiculo) {
        String QUERY = "UPDATE vehiculos SET matricula = ?, año = ?, marca = ?, modelo = ? WHERE id_vehiculo = ?";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY);

            pstmt.setString(1, vehiculo.getMatricula());
            pstmt.setInt(2, vehiculo.getAno());
            pstmt.setString(3, vehiculo.getMarca());
            pstmt.setString(4, vehiculo.getModelo());
            pstmt.setInt(6, vehiculo.getId());

            int filaAfectada = pstmt.executeUpdate();
            if (filaAfectada > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("No se encontró un registro con los parámetros proporcionados.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar vehiculo: " + e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
    }

    // Método para eliminar un vehículo de la base de datos
    @Override
    public void borrar(Vehiculo vehiculo) {
        String QUERY = "DELETE FROM vehiculo WHERE id_vehiculo = ?";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY);

            pstmt.setInt(1, vehiculo.getId());

            int filaAfectada = pstmt.executeUpdate();

            if (filaAfectada > 0) {
                System.out.println("Registro eliminado correctamente.");
            } else {
                System.out.println("No se encontró un registro con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar vehiculo: " + e);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }
    }

    public List<Vehiculo> obtenerVehiculosSinAsignacion() {
        List<Vehiculo> vehiculosSinAsignacion = new ArrayList<>();
        String query = "SELECT * FROM vehiculos v WHERE NOT EXISTS (SELECT 1 FROM historico h WHERE h.matricula = v.matricula AND h.fecha_fin IS NULL)";

        try {
            conn = ConexionBDD.abrirConexion();

            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getInt("año"),
                        rs.getString("marca"),
                        rs.getString("modelo")
                );
                vehiculosSinAsignacion.add(vehiculo);
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
        return vehiculosSinAsignacion;
    }

    public int obtenerIdPorMatricula(String matricula) {
        int id = -1;
        String sql = "SELECT id FROM vehiculos WHERE matricula = ?";
        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, matricula);

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

    public boolean existeMatricula(String matricula) {
        String sql = "SELECT COUNT(*) FROM vehiculos WHERE matricula = ?";

        try {

            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, matricula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; 
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

        return false; // Retorna false si no se encuentra la matrícula o hay un error
    }

}
