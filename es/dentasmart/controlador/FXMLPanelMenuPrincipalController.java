/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.controlador;

import com.jfoenix.controls.JFXListView;
import es.dentasmart.main.DentaSmart;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author seridan
 */
public class FXMLPanelMenuPrincipalController implements Initializable {

    @FXML
    private JFXListView<Label> listViewMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            crearMenuListView();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLPanelMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void crearMenuListView() throws FileNotFoundException {
        Label lblHome = new Label("Home", (new ImageView(new Image(DentaSmart.class.getResourceAsStream("/es/dentasmart/images/home.png")))));
        listViewMenu.getItems().add(lblHome);
        lblHome.setAccessibleText("Home");
        Label lblPacientes = new Label("Pacientes", (new ImageView(new Image(DentaSmart.class.getResourceAsStream("/es/dentasmart/images/paciente.png")))));
        listViewMenu.getItems().add(lblPacientes);
        lblPacientes.setAccessibleText("Pacientes");
        Label lblCitas = new Label("Citas", (new ImageView(new Image(DentaSmart.class.getResourceAsStream("/es/dentasmart/images/citas.png")))));
        listViewMenu.getItems().add(lblCitas);
        lblCitas.setAccessibleText("Citas");
        Label lblAgenda = new Label("Agenda", (new ImageView(new Image(DentaSmart.class.getResourceAsStream("/es/dentasmart/images/agenda.png")))));
        listViewMenu.getItems().add(lblAgenda);
        lblAgenda.setAccessibleText("Agenda");

    }

    /*
    public void funcionalidadMenu() {
        listViewMenu.setOnMouseClicked(e -> {
            ObservableList selectedIndices
                    = listViewMenu.getSelectionModel().getSelectedIndices();
            selectedIndices.forEach((o) -> {//sin la programcion funcional seria: for (Object o : selectedIndices) {
                System.out.println("o = " + o + " (" + o.getClass() + ")");
            });
        });
        
    }*/
    
   

    
}
