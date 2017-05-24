/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.sqlite.SQLiteDaoManager;
import es.dentasmart.modelo.Paciente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private JFXButton nuevoBtn;

    @FXML
    void nuevoPaciente(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/dentasmart/vista/FXMLRegistroPaciente.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage panelRegistro = new Stage();
        panelRegistro.initModality(Modality.APPLICATION_MODAL);
        panelRegistro.setTitle("Gestión de Pacientes");
        panelRegistro.setScene(new Scene(root1));
        panelRegistro.show();

    }

    ObservableList<Paciente> pacientes = null;




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        JFXTreeTableColumn<Paciente, Integer> idPaciente = new JFXTreeTableColumn<>("Id");
        idPaciente.setPrefWidth(50);
        idPaciente.setCellValueFactory(cellData -> cellData.getValue().getValue().IdPacienteProperty().asObject());

        JFXTreeTableColumn<Paciente, String> dni = new JFXTreeTableColumn<>("DNI");
        dni.setPrefWidth(85);
        dni.setCellValueFactory(cellData -> cellData.getValue().getValue().DniPacienteProperty());

        JFXTreeTableColumn<Paciente, String> nombrePaciente = new JFXTreeTableColumn<>("Nombre");
        nombrePaciente.setPrefWidth(100);
        nombrePaciente.setCellValueFactory(cellData -> cellData.getValue().getValue().NombrePacienteProperty());

        JFXTreeTableColumn<Paciente, String> primerApellido = new JFXTreeTableColumn<>("Primer apellido");
        primerApellido.setPrefWidth(120);
        primerApellido.setCellValueFactory(cellData -> cellData.getValue().getValue().PrimerApellidoProperty());

        JFXTreeTableColumn<Paciente, String> segundoApellido = new JFXTreeTableColumn<>("Segundo apellido");
        segundoApellido.setPrefWidth(120);
        segundoApellido.setCellValueFactory(cellData -> cellData.getValue().getValue().SegundoApellidoProperty());

        JFXTreeTableColumn<Paciente, LocalDate> fechaNac = new JFXTreeTableColumn<>("Fecha nacimiento");
        fechaNac.setPrefWidth(120);
        fechaNac.setCellValueFactory(cellData -> cellData.getValue().getValue().FechaNacProperty());

        JFXTreeTableColumn<Paciente, String> tfnoFijo = new JFXTreeTableColumn<>("Tfno. Fijo");
        tfnoFijo.setPrefWidth(120);
        tfnoFijo.setCellValueFactory(cellData -> cellData.getValue().getValue().TelefonoFijoProperty());

        SQLiteDaoManager man = null;
        man = new SQLiteDaoManager();


        try {
            pacientes = man.getPacienteDAO().obtenerTodos();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        final TreeItem<Paciente> root = new RecursiveTreeItem<Paciente>(pacientes, RecursiveTreeObject::getChildren);
        tablaPaciente.getColumns().setAll(idPaciente, dni, nombrePaciente, primerApellido, segundoApellido, fechaNac, tfnoFijo);
        tablaPaciente.setRoot(root);
        tablaPaciente.setShowRoot(false);

        getPacienteTableClicked();


    }

    private void getPacienteTableClicked(){

       //Paciente pacienteSeleccionado = tablaPaciente.getSelectionModel().getSelectedItems;
        //System.out.println(pacienteSeleccionado);
    }






}

