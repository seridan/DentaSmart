package es.dentasmart.controlador;

import com.jfoenix.controls.*;
import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.sqlite.SQLiteDaoManager;
import es.dentasmart.modelo.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URL;
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Da formato de 24h. al timePicker
        horaCitaTxt._24HourViewProperty().setValue(true);
        man = new SQLiteDaoManager();
    }


    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {

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
    void editarPaciente(ActionEvent event) throws DAOException {

       /* try {
            man.getPacienteDAO().modificar(pSeleccionado);
        } catch (DAOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void eliminarPaciente(ActionEvent event) {

    }

    @FXML
    void guardarPaciente(ActionEvent event) {

    }

    private void getPacienteFromTextField(){
        //pacienteToEdit.setIdPaciente(Integer.parseInt(String.valueOf(idPacienteTxt.getText())));
        pacienteToEdit.setIdPaciente(Integer.parseInt(idPacienteTxt.getText()));
        pacienteToEdit.setDniPaciente(dniTxt.getText());
        pacienteToEdit.setNombrePaciente(nombreTxt.getText());
        pacienteToEdit.setPrimerApellido(apellido1Txt.getText());
        pacienteToEdit.setSegundoApellido(apellido2Txt.getText());
        pacienteToEdit.setDireccionCalle(direccionTxt.getText());
        pacienteToEdit.setLocalidad(localidadTxt.getText());
        pacienteToEdit.setCodigoPostal(Integer.parseInt(codPostalTxt.getText()));
        pacienteToEdit.setTelefonoFijo(tfnoFijoTxt.getText());
        pacienteToEdit.setTelefonoMovil(tfnoMovilTxt.getText());
        pacienteToEdit.setEmail(emailTxt.getText());
        pacienteToEdit.setFechaNac(fechaNacTxt.getValue());
        pacienteToEdit.setPatologias(patologiasTxt.getText());
        pacienteToEdit.setObservaciones(observacionesTex.getText());
    }
}