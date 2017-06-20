/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.dao.sqlite;

import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.PacienteDAO;
import es.dentasmart.modelo.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author seridan
 */
public class SQLitePacienteDAO implements PacienteDAO {

    final String INSERT = "INSERT INTO paciente(nombre, primer_apellido, segundo_apellido, fecha_nac, direccion_calle, localidad, codigo_postal, email, telefono_fijo, telefono_movil, observaciones, patologias, dni_paciente) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE paciente SET nombre = ?, dni_paciente = ?, primer_apellido = ?, segundo_apellido = ?, fecha_nac = ?, direccion_calle = ?, localidad = ?, codigo_postal = ?, email = ?, telefono_fijo = ?, telefono_movil = ?, observaciones = ?, patologias = ? WHERE id_paciente = ?";
    final String DELETE = "DELETE FROM paciente WHERE id_paciente = ?";
    final String GETALL = "SELECT * FROM paciente";
    final String GETONE = "SELECT * FROM paciente WHERE id_paciente = ?";

    private Connection conn;

    public SQLitePacienteDAO(Connection conn) {

        this.conn = conn;
    }

    @Override
    public void insertar(Paciente p) throws DAOException {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            pstat = conn.prepareStatement(INSERT);
            pstat.setString(1, p.getNombrePaciente());
            pstat.setString(2, p.getPrimerApellido());
            pstat.setString(3, p.getSegundoApellido());
            pstat.setString(4, (p.getFechaNac()).toString());
            pstat.setString(5, p.getDireccionCalle());
            pstat.setString(6, p.getLocalidad());
            pstat.setString(7, (p.getCodigoPostal()));
            pstat.setString(8, p.getEmail());
            pstat.setString(9, p.getTelefonoFijo());
            pstat.setString(10, p.getTelefonoMovil());
            pstat.setString(11, p.getObservaciones());
            pstat.setString(12, p.getPatologias());
            pstat.setString(13, p.getDniPaciente());
            
            if (pstat.executeUpdate() == 0) {
                throw new DAOException("Error al guardar");
            }
            rs = pstat.getGeneratedKeys();
            if (rs.next()) {
                p.setIdPaciente(rs.getInt(1));
                System.out.println("el id del paciente generado es " + p.getIdPaciente());
            } else {
                throw new DAOException("El id del paciente ya existe");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    @Override
    public void modificar(Paciente p) throws DAOException {
        PreparedStatement pstat = null;
        try {
            pstat = conn.prepareStatement(UPDATE);
            pstat.setString(1, p.getNombrePaciente());
            pstat.setString(2, p.getDniPaciente());
            pstat.setString(3, p.getPrimerApellido());
            pstat.setString(4, p.getSegundoApellido());
            pstat.setString(5, (p.getFechaNac()).toString());
            pstat.setString(6, p.getDireccionCalle());
            pstat.setString(7, p.getLocalidad());
            pstat.setString(8, p.getCodigoPostal());
            pstat.setString(9, p.getEmail());
            pstat.setString(10, p.getTelefonoFijo());
            pstat.setString(11, p.getTelefonoMovil());
            pstat.setString(12, p.getObservaciones());
            pstat.setString(13, p.getPatologias());
            pstat.setInt(14, p.getIdPaciente());
            if (pstat.execute());
            {
                System.out.println("el resultado del executeUpdate es: "+ pstat.executeUpdate());
                //throw new DAOException("Error al actualizar");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    @Override
    public void eliminar(Paciente p) throws DAOException {
        PreparedStatement pstat = null;
        try {
            pstat = conn.prepareStatement(DELETE);
            pstat.setInt(1, p.getIdPaciente());
            if (pstat.executeUpdate() == 0);
            {
                //throw new DAOException("Error al eliminar");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }

    private Paciente getPacienteFromResulSet(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(rs.getInt("id_paciente"));
        paciente.setNombrePaciente(rs.getString("nombre"));
        paciente.setDniPaciente(rs.getString("dni_paciente"));
        paciente.setPrimerApellido(rs.getString("primer_apellido"));
        paciente.setSegundoApellido(rs.getString("segundo_apellido"));
        paciente.setFechaNac(LocalDate.parse(rs.getString("fecha_nac")));
        paciente.setDireccionCalle(rs.getString("direccion_calle"));
        paciente.setLocalidad(rs.getString("localidad"));
        paciente.setCodigoPostal(rs.getString("codigo_postal"));
        paciente.setEmail(rs.getString("email"));
        paciente.setTelefonoFijo(rs.getString("telefono_fijo"));
        paciente.setTelefonoMovil(rs.getString("telefono_movil"));
        paciente.setObservaciones(rs.getString("observaciones"));
        paciente.setPatologias(rs.getString("patologias"));
        return paciente;

    }

    @Override
    public ObservableList<Paciente> obtenerTodos() throws DAOException {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        ObservableList<Paciente> pacienteList = FXCollections.observableArrayList();
        try {
            pstat = conn.prepareStatement(GETALL);
            rs = pstat.executeQuery();
            while (rs.next()) {
                pacienteList.add(getPacienteFromResulSet(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        return pacienteList;
    }

    @Override
    public Paciente obtenerUno(Integer id) throws DAOException {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Paciente p;
        try {
            pstat = conn.prepareStatement(GETONE);
            pstat.setInt(1, id);
            rs = pstat.executeQuery();
            if (rs.next()) {
                p = getPacienteFromResulSet(rs);
            } else {
                throw new DAOException("No se ha encontrado el registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        return p;
    }

}
