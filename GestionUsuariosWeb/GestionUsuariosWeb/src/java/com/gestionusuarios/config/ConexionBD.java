package com.gestionusuarios.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL  =
            "jdbc:mysql://192.168.10.2:3306/gestion_usuarios?useSSL=false&serverTimezone=UTC";
    private static final String USER = "Admin01";
    private static final String PASS = "abc.123";

    //BLOQUE PARA INSERTAR EN MYSQL
    static {
        try {
            //Este es el nombre del driver de MySQL (Connector/J 8.x/9.x)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //Si llega aquí, el .jar no está en el classpath
            throw new RuntimeException("No se pudo cargar el driver MySQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
