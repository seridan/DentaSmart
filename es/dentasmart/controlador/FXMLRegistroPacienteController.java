package es.dentasmart.controlador;

import com.jfoenix.controls.*;
import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.sqlite.SQLiteDaoManager;
import es.dentasmart.modelo.Paciente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by seridan on 16/05/2017.
 */
public class FXMLRegistroPacienteController implements Initializable {

    @FXML
    private JFXTextField dniTxt;

    @FXML
    private JFXTextField nombreTxt;

    @FXML
    private JFXTextField apellido1Txt;

    @FXML
    private JFXTextField apellido2Txt;

    @FXML
    private JFXTextField direccionTxt;

    @FXML
    private JFXTextField tfnoMovilTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXDatePicker fechaNacTxt;

    @FXML
    private JFXTextField localidadTxt;

    @FXML
    private JFXTextField codPostalTxt;

    @FXML
    private JFXTextField tfnoFijoTxt;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnLimpiar;

    @FXML
    private JFXTextField patologiasTxt;

    @FXML
    private JFXDatePicker fechaCitaTxt;

    @FXML
    private JFXTimePicker horaCitaTxt;

    @FXML
    private SplitPane splitPane;

    @FXML
    private JFXTextField idPacienteTxt;

    @FXML
    private JFXTextArea observacionesTex;

    SQLiteDaoManager man;
    Paciente pacienteToEdit;
    private ObservableList<Paciente> listaPacientes;
    Paciente pacienteSeleccionado;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Da formato de 24h. al timePicker
        horaCitaTxt._24HourViewProperty().setValue(true);
        man = new SQLiteDaoManager();
    }

    public void setListaPacientes(ObservableList<Paciente> listaPacientes){

        this.listaPacientes = listaPacientes;
    }


    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;

        idPacienteTxt.setText(String.valueOf(pacienteSeleccionado.getIdPaciente()));
        dniTxt.setText(pacienteSeleccionado.getDniPaciente());
        nombreTxt.setText(pacienteSeleccionado.getNombrePaciente());
        apellido1Txt.setText(pacienteSeleccionado.getPrimerApellido());
        apellido2Txt.setText(pacienteSeleccionado.getSegundoApellido());
        direccionTxt.setText(pacienteSeleccionado.getDireccionCalle());
        localidadTxt.setText(pacienteSeleccionado.getLocalidad());
        codPostalTxt.setText(String.valueOf(pacienteSeleccionado.getCodigoPostal()));
        tfnoFijoTxt.setText(pacienteSeleccionado.getTelefonoFijo());
        tfnoMovilTxt.setText(pacienteSeleccionado.getTelefonoMovil());
        emailTxt.setText(pacienteSeleccionado.getEmail());
        fechaNacTxt.setValue(pacienteSeleccionado.getFechaNac());
    }


    @FXML
    void limpiarCampos(ActionEvent event) {
        dniTxt.setText("");
        nombreTxt.setText("");
        apellido1Txt.setText("");
        apellido2Txt.setText("");
        direccionTxt.setText("");
        localidadTxt.setText("");
        codPostalTxt.setText("");
        tfnoFijoTxt.setText("");
        tfnoMovilTxt.setText("");
        emailTxt.setText("");
        fechaNacTxt.setValue(null);
    }

    @FXML
    void deshacer(ActionEvent event) {
        System.out.println("clicked");
        if(pacienteSeleccionado != null){
            setPacienteSeleccionado(pacienteSeleccionado);
        }else {
            Notifications notificationBuilder = Notifications.create()
                    .title("No hay un paciente seleccionado")
                    .text("Debe seleccionar un paciente en la tabla")
                    .graphic(null)
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.showWarning();
        }
    }


    @FXML
    void guardarPaciente(ActionEvent event) throws DAOException {

        if (pacienteSeleccionado == null) {
            getPacienteToAddFromTextField();
            try {
                man.getPacienteDAO().insertar(pacienteToEdit);
                System.out.println("este es el paciente nuevo " + pacienteToEdit);
                listaPacientes.add(pacienteToEdit);
                cerrarVentana(event);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }else {

            try {
                getPacienteToEditFromTextField();
                System.out.println("este es el pacienteToEdit " + pacienteToEdit);
                man.getPacienteDAO().modificar(pacienteToEdit);
                listaPacientes.set(listaPacientes.indexOf(pacienteSeleccionado), pacienteToEdit);
                //listaPacientes.add(pacienteToEdit);
                pacienteToEdit = null;

                cerrarVentana(event);
                System.out.println("el indice del objeto a editar es: " +listaPacientes.indexOf(pacienteToEdit));
            } catch (DAOException e) {
                e.printStackTrace();
            }

        }

    }

    private void getPacienteToEditFromTextField(){
        //pacienteToEdit.setIdPaciente(Integer.parseInt(String.valueOf(idPacienteTxt.getText())));
        pacienteToEdit = new Paciente();
        pacienteToEdit.setIdPaciente((Integer.parseInt(idPacienteTxt.getText())));
        pacienteToEdit.setDniPaciente(dniTxt.getText());
        pacienteToEdit.setNombrePaciente(nombreTxt.getText());
        pacienteToEdit.setPrimerApellido(apellido1Txt.getText());
        pacienteToEdit.setSegundoApellido(apellido2Txt.getText());
        pacienteToEdit.setDireccionCalle(direccionTxt.getText());
        pacienteToEdit.setLocalidad(localidadTxt.getText());
        pacienteToEdit.setCodigoPostal(codPostalTxt.getText());
        //pacienteToEdit.setCodigoPostal(codPostalTxt.getText() != "" ? (Integer.parseInt(codPostalTxt.getText())) : 0);
        pacienteToEdit.setTelefonoFijo(tfnoFijoTxt.getText());
        pacienteToEdit.setTelefonoMovil(tfnoMovilTxt.getText());
        pacienteToEdit.setEmail(emailTxt.getText());
        //pacienteToEdit.setFechaNac(fechaNacTxt.getValue());
        pacienteToEdit.setFechaNac(fechaNacTxt.getValue() != null ? (fechaNacTxt.getValue()) : LocalDate.of(1, 1 , 1));
        pacienteToEdit.setPatologias(patologiasTxt.getText());
        pacienteToEdit.setObservaciones(observacionesTex.getText());

    }

    private void getPacienteToAddFromTextField(){
        //pacienteToEdit.setIdPaciente(Integer.parseInt(String.valueOf(idPacienteTxt.getText())));
        pacienteToEdit = new Paciente();
        pacienteToEdit.setDniPaciente(dniTxt.getText());
        pacienteToEdit.setNombrePaciente(nombreTxt.getText());
        pacienteToEdit.setPrimerApellido(apellido1Txt.getText());
        pacienteToEdit.setSegundoApellido(apellido2Txt.getText());
        pacienteToEdit.setDireccionCalle(direccionTxt.getText());
        pacienteToEdit.setLocalidad(localidadTxt.getText());
        pacienteToEdit.setCodigoPostal(codPostalTxt.getText());
        //pacienteToEdit.setCodigoPostal(codPostalTxt.getText() != "" ? (Integer.parseInt(codPostalTxt.getText())) : 0 );
        pacienteToEdit.setTelefonoFijo(tfnoFijoTxt.getText());
        pacienteToEdit.setTelefonoMovil(tfnoMovilTxt.getText());
        pacienteToEdit.setEmail(emailTxt.getText());
        //pacienteToEdit.setFechaNac(fechaNacTxt.getValue());
        pacienteToEdit.setFechaNac(fechaNacTxt.getValue() != null ? (fechaNacTxt.getValue()) : LocalDate.of(1, 1 , 1));
        pacienteToEdit.setPatologias(patologiasTxt.getText());
        pacienteToEdit.setObservaciones(observacionesTex.getText());

    }

    private void cerrarVentana(ActionEvent event){
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();

    }
}