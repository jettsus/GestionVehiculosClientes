/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DTO.PersonaVehiculoDTO;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author JETTSUSHD
 */
public class PersonaVehiculoDAO {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    // Obtengo  personas con vehículos a partir de la página y tamaño de página
    public List<PersonaVehiculoDTO> obtenerPersonasConVehiculos(int pagina, int tamanoPagina) {
        List<PersonaVehiculoDTO> lista = new ArrayList<>();
        int desplazamiento = (pagina - 1) * tamanoPagina;

        String QUERY_SELECT = "SELECT nombre, matricula, año, marca, modelo "
                + "FROM historico "
                + "WHERE fecha_fin IS NULL "
                + "LIMIT " + tamanoPagina + " OFFSET " + desplazamiento;

        System.out.println("Consulta ejecutada: " + QUERY_SELECT);

        try {
            conn = ConexionBDD.abrirConexion();
            pstmt = conn.prepareStatement(QUERY_SELECT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String matricula = rs.getString("matricula");
                int año = rs.getInt("año");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");

                PersonaVehiculoDTO personaVehiculo = new PersonaVehiculoDTO(nombre, matricula, año, marca, modelo);
                lista.add(personaVehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }

        return lista;
    }

    private boolean limpiarEntradaNombre(String filtro) {
        boolean filtroValido = true;

        if (filtro == null || filtro.isEmpty()) {
            return true; // Si el filtro está vacío, es válido
        }

        // Validar que solo contenga letras Unicode (incluye tildes), espacios, y longitud máxima de 40 caracteres
        if (!filtro.matches("^[\\p{L}\\s]{1,40}$")) {
            JOptionPane.showMessageDialog(null,
                    "El filtro de nombre contiene caracteres no permitidos. Solo se permiten letras y espacios, con un máximo de 40 caracteres.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            filtroValido = false;
        }

        // Validar que no contenga palabras clave peligrosas
        if (filtroValido && filtro.matches(".*\\b(?i)(select|insert|drop|delete|update|alter|create|exec|union|historico|personas|vehiculos)\\b.*")) {
            JOptionPane.showMessageDialog(null,
                    "El filtro de nombre contiene palabras reservadas como 'SELECT', 'INSERT', 'DROP', etc., lo cual no está permitido.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            filtroValido = false;
        }

        return filtroValido;
    }

    private boolean limpiarEntradaAnio(Integer filtro) {
        boolean filtroValido = true;

        // Verificar si el filtro es nulo
        if (filtro == null) {
            return true; // Si el filtro es nulo, es válido
        }

        // Verificar si el filtro está fuera del rango permitido (1900 a año actual)
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        if (filtro < 1900 || filtro > anioActual) {
            JOptionPane.showMessageDialog(null,
                    "El filtro de año debe estar entre 1900 y " + anioActual + ".",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            filtroValido = false;
        }

        // Verificar si contiene caracteres no válidos (solo números)
        if (!filtro.toString().matches("^[0-9]{4}$")) {
            JOptionPane.showMessageDialog(null,
                    "El filtro de año solo permite números del 0 al 9.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            filtroValido = false;
        }

        return filtroValido;
    }

    private boolean limpiarEntradaNumPropietarios(Integer filtro) {
        boolean filtroValido = true;

        // Verificar si el filtro es nulo
        if (filtro == null) {
            return true; // Si el filtro es nulo, es válido
        }

        // Verificar si el filtro está dentro del rango permitido (1 a 1000)
        if (filtro < 1 || filtro > 1000) {
            JOptionPane.showMessageDialog(null,
                    "El filtro de número de propietarios debe estar entre 1 y 1000.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            filtroValido = false;
        }

        // Verificar si contiene caracteres no válidos (solo números)
        if (filtroValido && !filtro.toString().matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null,
                    "El filtro de número de propietarios solo permite números del 0 al 9.",
                    "Error de filtro",
                    JOptionPane.ERROR_MESSAGE);
            filtroValido = false;
        }

        return filtroValido;
    }

    // Método para filtrar personas con vehículos
    public List<PersonaVehiculoDTO> filtrarPersonasConVehiculos(String nombre, String marca, String modelo, String genero, Integer año, Integer numPropietarios, int limit, int offset) {
        List<PersonaVehiculoDTO> listaPersonas = new ArrayList<>();

        // Validar entradas para evitar inyecciones SQL o entradas inválidas
        if (!limpiarEntradaNombre(nombre) || (año != null && !limpiarEntradaAnio(año)) || (numPropietarios != null && !limpiarEntradaNumPropietarios(numPropietarios))) {
            return listaPersonas; // Si falla, devolver una lista vacía
        }

        // Construir la consulta con  filtros 
        StringBuilder query = new StringBuilder(
                "SELECT nombre, matricula, año, marca, modelo "
                + "FROM historico h "
                + "WHERE h.fecha_fin IS NULL"
        );

        // Lista para almacenar los parámetros de la consulta
        List<Object> parametros = new ArrayList<>();

        // añado condiciones de filtro según los parámetros 
        if (nombre != null && !nombre.isEmpty()) {
            query.append(" AND nombre LIKE ?");
            parametros.add("%" + nombre.trim() + "%"); // Buscar nombre que contenga el valor
        }

        if (marca != null && !marca.equals("Todos")) {
            query.append(" AND marca = ?");
            parametros.add(marca.trim()); // Filtro por marca
        }

        if (modelo != null && !modelo.equals("Todos")) {
            query.append(" AND modelo = ?");
            parametros.add(modelo.trim()); // Filtro por modelo
        }

        if (genero != null && !genero.isEmpty()) {
            query.append(" AND genero = ?");
            parametros.add(genero.trim()); // Filtro por género
        }

        if (año != null && año > 0) {
            query.append(" AND año = ?");
            parametros.add(año); // Filtro por año del vehículo
        }

        if (numPropietarios != null && numPropietarios > 0) {
            query.append(" AND (SELECT COUNT(*) FROM historico h2 WHERE h2.nombre = h.nombre AND h2.fecha_fin IS NULL) = ?");
            parametros.add(numPropietarios); // Filtro por número de propietarios
        }

        // Validar paginación para evitar valores negativos o sea que sea mayor o igual a 0
        limit = Math.max(limit, 0);
        offset = Math.max(offset, 0);

        // Añadir la paginación a la consulta
        query.append(" LIMIT ? OFFSET ?");
        parametros.add(limit);  // Límite de resultados por página
        parametros.add(offset); // Desplazamiento de los resultados

        try {
            conn = ConexionBDD.abrirConexion();

            // Preparar la consulta y convertir a string
            pstmt = conn.prepareStatement(query.toString());

            // Añadir los parámetros a la consulta con un bucle 
            for (int i = 0; i < parametros.size(); i++) {
                pstmt.setObject(i + 1, parametros.get(i)); // Establecer el valor del parámetro en el PreparedStatement
            }

            rs = pstmt.executeQuery();

            // Procesar los resultados de la consulta
            while (rs.next()) {
                String nombrePersona = rs.getString("nombre");
                String matricula = rs.getString("matricula");
                int añoVehiculo = rs.getInt("año");
                String marcaVehiculo = rs.getString("marca");
                String modeloVehiculo = rs.getString("modelo");

                // Crear el objeto PersonaVehiculoDTO y agregarlo a la lista
                PersonaVehiculoDTO personaVehiculo = new PersonaVehiculoDTO(nombrePersona, matricula, añoVehiculo, marcaVehiculo, modeloVehiculo);
                listaPersonas.add(personaVehiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }

        return listaPersonas;
    }

    // Obtener una lista de marcas para llenar el ComboBox
    public List<String> obtenerMarcas() {
        // Inicializar la lista para almacenar las marcas obtenidas
        List<String> marcas = new ArrayList<>();

        // consulta SQL para obtener las marcas únicas con fecha fin null
        String query = "SELECT DISTINCT marca FROM historico WHERE fecha_fin IS NULL";

        try {
            conn = ConexionBDD.abrirConexion();

            pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                marcas.add(rs.getString("marca"));
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

        // Retornar la lista de marcas obtenidas
        return marcas;
    }

    // Obtener una lista de modelos a partir de una marca específica
    public List<String> obtenerModelosPorMarca(String marca) {
        // Inicializar la lista para almacenar los modelos 
        List<String> modelos = new ArrayList<>();

        // consulta SQL para obtener los modelos únicos de la marca indicada sin fecha_fin
        String query = "SELECT DISTINCT modelo FROM historico "
                + " WHERE marca = ? AND fecha_fin IS NULL";

        try {
            conn = ConexionBDD.abrirConexion();

            // Preparo la consulta con el valor de la marca proporcionada
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, marca);

            // Ejecuto la consulta y almaceno los resultados
            rs = pstmt.executeQuery();

            // Recorrer los resultados y añadir cada modelo a la lista
            while (rs.next()) {
                modelos.add(rs.getString("modelo"));
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

        return modelos;
    }

    public boolean eliminarVehiculo(String matricula) {
        String sql = "DELETE FROM historico WHERE matricula = ?";
        try (Connection conn = ConexionBDD.abrirConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, matricula.trim()); // Eliminar espacios adicionales

            int filasAfectadas = pstmt.executeUpdate(); // Ejecutar la consulta

            if (filasAfectadas > 0) {
                System.out.println("Vehículo eliminado con matrícula: " + matricula);
                return true;
            } else {
                System.out.println("No se encontró un vehículo con la matrícula proporcionada.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el vehículo: " + e.getMessage());
            return false;
        }
    }

    //Obtengo los detalles de cada vehiculo para mostrarlo en una tabla fuera de la vista un registro
    public List<PersonaVehiculoDTO> obtenerDetallesVehiculo(String matricula) {
        // Lista para almacenar los detalles del vehículo
        List<PersonaVehiculoDTO> detalles = new ArrayList<>();

        // Consulta para obtener los detalles del vehículo, quitando registros sin fecha de fin
        String query = "SELECT nombre, genero, fecha_inicio, fecha_fin FROM historico WHERE matricula = ? AND fecha_fin IS NOT NULL";

        try {
            conn = ConexionBDD.abrirConexion();

            // Preparo la consulta con el valor de la matrícula
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, matricula);

            // Ejecuto la consulta
            rs = pstmt.executeQuery();

            // Recorro los resultados y creo objetos PersonaVehiculoDTO
            while (rs.next()) {
                char genero = rs.getString("genero").charAt(0);
                PersonaVehiculoDTO detalle = new PersonaVehiculoDTO(
                        rs.getString("nombre"),
                        genero,
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin")
                );
                detalles.add(detalle);
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

        // Retornar la lista de detalles del vehículo
        return detalles;
    }

    public int obtenerNumeroPropietariosPorVehiculo(String matricula) {
        // Consulta para contar el número de propietarios únicos del vehículo en la tabla "historico"
        String query = "SELECT COUNT(DISTINCT id_persona) AS numeroPropietarios FROM historico WHERE matricula = ?";
        int numeroPropietarios = 0;

        try {
            // Abrir conexión a la base de datos
            conn = ConexionBDD.abrirConexion();

            // Preparar la consulta con el valor de la matrícula
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, matricula);

            // Ejecutar la consulta
            rs = pstmt.executeQuery();

            // Obtener el resultado si existe
            if (rs.next()) {
                numeroPropietarios = rs.getInt("numeroPropietarios");
            }
        } catch (SQLException x) {
            x.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Cerrar los recursos utilizados (ResultSet, PreparedStatement y conexión)
            ConexionBDD.cerrarResulset(rs);
            ConexionBDD.cerrarPreparedStatement(pstmt);
            ConexionBDD.cerrarConexion(conn);
        }

        // Retornar el número de propietarios encontrado
        return numeroPropietarios;
    }

    //Metodo para asociar vehiculos usando una transaccion
    public boolean asociarVehiculoConTransaccion(PersonaVehiculoDTO historico) {
        boolean esValido = false;

        try {
            conn = ConexionBDD.abrirConexion();
            conn.setAutoCommit(false);  //Desactivo commit para manejar la transaccion 

            // Verificaro si el vehículo ya tiene un registro con fecha_fin = NULL
            String sqlVerificar = "SELECT id FROM historico WHERE id_vehiculo = ? AND fecha_fin IS NULL";

            try (PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificar)) {
                //Establezco el valor del preparedstatement que recoge id del vehiculo en historico
                pstmtVerificar.setInt(1, historico.getId_vehiculo());

                //Lo ejecuto
                rs = pstmtVerificar.executeQuery();

                if (rs.next()) {
                    // Si hay un registro con fecha_fin = NULL, actualizar la fecha_fin a la actual
                    int idHistorico = rs.getInt("id");
                    String sqlActualizar = "UPDATE historico SET fecha_fin = ? WHERE id = ?";

                    try (PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizar)) {

                        pstmtActualizar.setDate(1, new Date(System.currentTimeMillis()));
                        pstmtActualizar.setInt(2, idHistorico);
                        pstmtActualizar.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                }
            }

            // Verificar si la persona existe en la tabla `personas`
            String sqlVerificarPersona = "SELECT id FROM personas WHERE id = ?";
            try (PreparedStatement pstmtVerificarPersona = conn.prepareStatement(sqlVerificarPersona)) {

                pstmtVerificarPersona.setInt(1, historico.getId_persona());

                ResultSet rsPersona = pstmtVerificarPersona.executeQuery();

                if (!rsPersona.next()) {
                    System.out.println("Error: No se pudo encontrar el ID de la persona en la base de datos.");
                    // Realizar un rollback de la transacción, ya que no puede continuar y vuelve a estado anterior
                    conn.rollback();
                    return false; // Salir si no existe
                }
            }

            // Crear el nuevo registro de asociación en la tabla historico que reoge todos los campos y fecha fin null
            String sqlInsertar = "INSERT INTO historico (id_persona, id_vehiculo, nombre, matricula, año, marca, modelo, genero, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL)";
            try (PreparedStatement pstmtInsertar = conn.prepareStatement(sqlInsertar)) {
                pstmtInsertar.setInt(1, historico.getId_persona());
                pstmtInsertar.setInt(2, historico.getId_vehiculo());
                pstmtInsertar.setString(3, historico.getNombre());
                pstmtInsertar.setString(4, historico.getMatricula());
                pstmtInsertar.setInt(5, historico.getAño());
                pstmtInsertar.setString(6, historico.getMarca());
                pstmtInsertar.setString(7, historico.getModelo());
                pstmtInsertar.setString(8, String.valueOf(historico.getGenero()));
                pstmtInsertar.setDate(9, historico.getFecha_inicio());

                //Comprueba que al ejecutar ha afectado en la tabla
                int filasAfectadas = pstmtInsertar.executeUpdate();
                if (filasAfectadas > 0) {
                    esValido = true;
                }
            }

            // Hacer commit si todo salió bien
            if (esValido) {
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de excepción, hacer rollback
            //Como una vez me dio error esto es mejor para prevenir y muestre el error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionBDD.cerrarConexion(conn);

        }

        return esValido;
    }

}
