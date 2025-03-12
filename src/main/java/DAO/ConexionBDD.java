/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.ucp.jdbc.PoolDataSource;

import oracle.ucp.jdbc.PoolDataSourceFactory;

/**
 *
 * @author JETTSUSHD
 */
public class ConexionBDD {

    private static String db_url = "jdbc:mysql://localhost/jdbc2";
    private static String user = "root";
    private static String pass = "";
    private static Connection connection = null;

    //para conexion pool
    private static PoolDataSource pds = null;

    public static void inicializaPool(String url, String usuario, String password) {
        try {
            if (pds == null) {
                pds = PoolDataSourceFactory.getPoolDataSource();
                pds.setConnectionFactoryClassName("com.mysql.cj.jdbc.MysqlDataSource");
                pds.setURL(url); // Usa el parámetro recibido
                pds.setUser(usuario);
                pds.setPassword(password);
            }
        } catch (SQLException e) {
            System.err.println("Error al inicializar el pool de conexiones: " + e);
        }
    }

    public ConexionBDD(String url, String usuario, String password) {
        db_url = url;
        user = usuario;
        pass = password;
    }

    //abrir conexión sin pool
//    public static Connection abrirConexion() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(db_url, user, pass);
//            System.out.println("Conexión establecida");
//        } catch (ClassNotFoundException e) {
//            System.err.println("Driver no encontrado " + e);
//        } catch (SQLException e) {
//            System.err.println("Error de conexión " + e);
//        }
//        return connection;
//    }
    // Abre una conexión desde el pool
    public static Connection abrirConexion() {
        Connection connection = null; // Inicializamos la variable de conexión
        try {
            if (pds == null) {
                inicializaPool(db_url, user, pass); // Inicializa el pool si no está configurado
            }
            connection = pds.getConnection();
            System.out.println("Conexión establecida desde el pool");
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión del pool: " + e);
        }
        return connection;
    }

    //cerrar conexión
    public static void cerrarConexion(Connection connection) {
        if (connection != null) {
            try {
                connection.close(); // Libera la conexión al pool
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e);
            }
        } else {
            System.out.println("No hay ninguna conexión abierta");
        }
    }

    public static void cerrarPreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
                System.out.println("PreparedStatement cerrado correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e);
            }
        } else {
            System.out.println("No hay PreparedStatement abierto para cerrar");
        }
    }

    public static void cerrarResulset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("Resulset cerrado correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar Resulset: " + e);
            }
        } else {
            System.out.println("No hay Resulset abierto para cerrar");
        }
    }
}
