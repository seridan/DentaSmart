/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.database;

import java.sql.*;

/**
 *
 * @author seridan
 */
public class SqliteConnection {
    public static Connection Conectar(){
        try {
          //Class.forName("org.sqlite.JDBC"); este método ya no es necesario
          Connection conn = DriverManager.getConnection("jdbc:sqlite:DentasmartDb.sqlite");
          return conn;
            
        } catch (SQLException e) {
        return null;
        }
    }
}
        
        
    

