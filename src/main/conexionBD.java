/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    private static Connection conexion;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                String url = "jdbc:mariadb://127.0.0.1:3306/clinica_farmacia";
                String user = "admin";
                String password = "admin1234";
                conexion = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }
}