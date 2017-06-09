/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.controlador;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.sqlite.SQLiteDaoManager;
import es.dentasmart.modelo.Paciente;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    private JFXTextField txtBuscar;

    ObservableList<Paciente> pacientes = null;
    Paciente pacienteSeleccionado = null;
    SQLiteDaoManager man;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTablaPacientes();
        busqueda();
        getPacienteSeleccionado();
    }

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

    @FXML
    void editarPaciente(ActionEvent event) throws IOException {
        abrirVentanaEdicion();
    }

    @FXML
    void eliminarPaciente(ActionEvent event) throws DAOException {


        System.out.println("este es el pacienteToEdit para eliminar " + pacienteSeleccionado);
        man.getPacienteDAO().eliminar(pacienteSeleccionado);

    }

    @FXML
    void dobleClick(MouseEvent event) throws IOException {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                abrirVentanaEdicion();
            }
        }
    }

    private void busqueda(){

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> tablaPaciente.setPredicate(pacienteTreeItem -> {
            Boolean flag = pacienteTreeItem.getValue().getNombrePaciente().toLowerCase().contains(newValue.toLowerCase())||
                    pacienteTreeItem.getValue().getPrimerApellido().toLowerCase().contains(newValue.toLowerCase())||
                    pacienteTreeItem.getValue().getSegundoApellido().toLowerCase().contains(newValue.toLowerCase())||
                    pacienteTreeItem.getValue().getDniPaciente().toLowerCase().contains(newValue.toLowerCase())||
                    pacienteTreeItem.getValue().IdPacienteProperty().asString().getValue().contains(newValue);
            return flag;
        }));
    }

    private void cargarTablaPacientes(){
        JFXTreeTableColumn<Paciente, Integer> idPaciente = new JFXTreeTableColumn<>("Id");
        idPaciente.setPrefWidth(50);
        idPaciente.setCellValueFactory(cellData -> cellData.getValue().getValue().IdPacienteProperty().asObject());//el metodo asObject se utiliza para integer

        JFXTreeTableColumn<Paciente, String> dni = new JFXTreeTableColumn<>("DNI");
        dni.setPrefWidth(85);
        dni.setCellValueFactory(cellData -> cellData.getValue().getValue().DniPacienteProperty());

        JFXTreeTableColumn<Paciente, String> nombrePaciente = new JFXTreeTableColumn<>("Nombre");
        nombrePaciente.setPrefWidth(100);
        nombrePaciente.setCellValueFactory(cellData -> cellData.getValue().getValue().NombrePacienteProperty());

        JFXTreeTableColumn<Paciente, String> primerApellido = new JFXTreeTableColumn<>("Primer apellido");
        primerApellido.setPrefWidth(110);
        primerApellido.setCellValueFactory(cellData -> cellData.getValue().getValue().PrimerApellidoProperty());

        JFXTreeTableColumn<Paciente, String> segundoApellido = new JFXTreeTableColumn<>("Segundo apellido");
        segundoApellido.setPrefWidth(120);
        segundoApellido.setCellValueFactory(cellData -> cellData.getValue().getValue().SegundoApellidoProperty());

        JFXTreeTableColumn<Paciente, LocalDate> fechaNac = new JFXTreeTableColumn<>("Fecha nacimiento");
        fechaNac.setPrefWidth(100);
        fechaNac.setCellValueFactory(cellData -> cellData.getValue().getValue().FechaNacProperty());

        JFXTreeTableColumn<Paciente, String> tfnoFijo = new JFXTreeTableColumn<>("Tfno. Fijo");
        tfnoFijo.setPrefWidth(90);
        tfnoFijo.setCellValueFactory(cellData -> cellData.getValue().getValue().TelefonoFijoProperty());

        JFXTreeTableColumn<Paciente, String> tfnoMovil = new JFXTreeTableColumn<>("Tfno. Móvil");
        tfnoMovil.setPrefWidth(90);
        tfnoMovil.setCellValueFactory(cellData -> cellData.getValue().getValue().TelefonoMovilProperty());

        JFXTreeTableColumn<Paciente, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(200);
        email.setCellValueFactory(cellData -> cellData.getValue().getValue().emailProperty());


        man = new SQLiteDaoManager();

        try {
            pacientes = man.getPacienteDAO().obtenerTodos();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        final TreeItem<Paciente> root = new RecursiveTreeItem<>(pacientes, RecursiveTreeObject::getChildren);
        tablaPaciente.getColumns().setAll(idPaciente, dni, nombrePaciente, primerApellido, segundoApellido, fechaNac, tfnoFijo, tfnoMovil, email);
        tablaPaciente.setRoot(root);
        tablaPaciente.setShowRoot(false);
    }

    private Paciente getPacienteSeleccionado(){
        tablaPaciente.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null)
                System.out.println(newValue.getValue());
            pacienteSeleccionado = newValue.getValue();
            System.out.println("el paciente seleccionado es: " + pacienteSeleccionado + "\nel id es: "+ pacienteSeleccionado.getIdPaciente());

        });
        return pacienteSeleccionado;
    }

    private void abrirVentanaEdicion() throws IOException {
        if (getPacienteSeleccionado()!= null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/dentasmart/vista/FXMLRegistroPaciente.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage panelRegistro = new Stage();
            FXMLRegistroPacienteController controller = fxmlLoader.getController();
            controller.setPacienteSeleccionado((getPacienteSeleccionado()));//Cargo el paciente seleccionado de la tabla en RegistroPaciente.
            panelRegistro.initModality(Modality.APPLICATION_MODAL);
            panelRegistro.setTitle("Gestión de Pacientes");
            panelRegistro.setScene(new Scene(root1));
            panelRegistro.show();
        }
    }
}

