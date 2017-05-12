/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.controlador;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.sqlite.SQLiteDaoManager;
import es.dentasmart.modelo.Paciente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author seridan
 */
public class FXMLpanelPacienteController implements Initializable {

    @FXML
    private JFXTreeTableView<Paciente> tablaPaciente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXTreeTableColumn<Paciente, Integer> idPaciente = new JFXTreeTableColumn<>("Id");
        idPaciente.setPrefWidth(150);
        idPaciente.setCellValueFactory(cellData -> cellData.getValue().getValue().IdPacienteProperty().asObject());

        JFXTreeTableColumn<Paciente, String> nombrePaciente = new JFXTreeTableColumn<>("Nombre");
        nombrePaciente.setPrefWidth(150);
        nombrePaciente.setCellValueFactory(cellData -> cellData.getValue().getValue().NombrePacienteProperty());

        JFXTreeTableColumn<Paciente, String> dni = new JFXTreeTableColumn<>("DNI");
        dni.setPrefWidth(150);
        dni.setCellValueFactory(cellData -> cellData.getValue().getValue().DniPacienteProperty());

        JFXTreeTableColumn<Paciente, String> primerApellido = new JFXTreeTableColumn<>("Primer apellido");
        primerApellido.setPrefWidth(150);
        primerApellido.setCellValueFactory(cellData -> cellData.getValue().getValue().PrimerApellidoProperty());

        JFXTreeTableColumn<Paciente, String> segundoApellido = new JFXTreeTableColumn<>("Segundo apellido");
        segundoApellido.setPrefWidth(150);
        segundoApellido.setCellValueFactory(cellData -> cellData.getValue().getValue().SegundoApellidoProperty());

        JFXTreeTableColumn<Paciente, LocalDate> fechaNac = new JFXTreeTableColumn<>("Fecha nacimiento");
        fechaNac.setPrefWidth(150);
        fechaNac.setCellValueFactory(cellData -> cellData.getValue().getValue().FechaNacProperty());

        SQLiteDaoManager man = null;
        man = new SQLiteDaoManager();

        ObservableList<Paciente> pacientes = null;
        try {
            pacientes = man.getPacienteDAO().obtenerTodos();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        final TreeItem<Paciente> root = new RecursiveTreeItem<Paciente>(pacientes, RecursiveTreeObject::getChildren);
        tablaPaciente.getColumns().setAll(idPaciente, nombrePaciente, dni, primerApellido, segundoApellido, fechaNac);
        tablaPaciente.setRoot(root);
        tablaPaciente.setShowRoot(false);


    }






}

