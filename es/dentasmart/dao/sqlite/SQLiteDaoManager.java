package es.dentasmart.dao.sqlite;

import es.dentasmart.dao.DAOManager;
import es.dentasmart.dao.PacienteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by seridan on 09/05/2017.
 */
public class SQLiteDaoManager implements DAOManager {

    private static Connection conn;

    private PacienteDAO paciente = null;


//***************************Metodo de Makigas*****************************
    /*
    public SQLiteDaoManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:DentasmartDb.sqlite");

    }

    @Override
    public PacienteDAO getPacienteDAO() {
        if (paciente == null){
            paciente = new SQLitePacienteDAO(conn);
            System.out.println("se ha creado una nueva conexion");
        }else {
            System.out.println("ya existe una conexion abierta");
        }
        return paciente;
    }*/

    //***********Metodo de prueba creado por mi tipo singletone estricto*************
    public static Connection getConnection() {
        try {
            if (conn == null) {
                Runtime.getRuntime().addShutdownHook(new getClose());
                conn = DriverManager.getConnection("jdbc:sqlite:DentasmartDb.sqlite");
                System.out.println("se ha creado una nueva conexion");
            } else {
                System.out.println("ya existe una conexion abierta");
            }
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Conexion fallida");
        }

    }

   /* public SQLiteDaoManager() throws SQLException {
        //conn = DriverManager.getConnection("jdbc:sqlite:DentasmartDb.sqlite");

    }*/

    @Override
    public PacienteDAO getPacienteDAO() {
        if (paciente == null) {
            paciente = new SQLitePacienteDAO(getConnection());
        }
        return paciente;
    }

    /**
     * Metodo que cierra la conexion cuando la aplicacion es cerrada o bien por el usuario
     * o inesparadamente, el metodo se ejecutara.
     */
    static class getClose extends Thread {
        @Override
        public void run() {
            Connection connection = SQLiteDaoManager.getConnection();
            try {
                connection.close();
                System.out.println("se ha cerrado la conexion");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
