package es.dentasmart.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URL;
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
    private JFXTextField localidad2;

    @FXML
    private JFXTextField codPostalTxt2;

    @FXML
    private JFXTextField tfnoFijoTxt2;

    @FXML
    private JFXTextField tfnoMovilTxt2;

    @FXML
    private JFXTextField emailTxt2;

    @FXML
    private JFXDatePicker fechaNacTxt;

    @FXML
    private JFXTextField localidad;

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
    private JFXDatePicker fechaCitaTxt;

    @FXML
    private JFXTimePicker horaCitaTxt;

    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Da formato de 24h. al timePicker
        horaCitaTxt._24HourViewProperty().setValue(true);




    }
}
