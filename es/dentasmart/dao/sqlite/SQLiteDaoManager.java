package es.dentasmart.dao.sqlite;

import es.dentasmart.dao.DAOManager;
import es.dentasmart.dao.PacienteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by seridan on 09/05/2017.
 */
public class SQLiteDaoManager implements DAOManager{

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
    public static Connection getConnection()  {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection("jdbc:sqlite:DentasmartDb.sqlite");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public SQLiteDaoManager() throws SQLException {
        //conn = DriverManager.getConnection("jdbc:sqlite:DentasmartDb.sqlite");

    }

    @Override
    public PacienteDAO getPacienteDAO() {
        if (paciente == null){
            paciente = new SQLitePacienteDAO(getConnection());
            System.out.println("se ha creado una nueva conexion");
        }else {
            System.out.println("ya existe una conexion abierta");
        }
        return paciente;
    }
}
