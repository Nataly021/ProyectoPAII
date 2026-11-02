/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author User
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

private static final String URL = "jdbc:sqlserver://Nataly:1433;"
        + "databaseName=OpticaDB;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "Avanzada";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(
                    URL, USER, PASSWORD
            );

            System.out.println("Conexión a OpticaDB exitosa.");
            return conexion;

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }

}
