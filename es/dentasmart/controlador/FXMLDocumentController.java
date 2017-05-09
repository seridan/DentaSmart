package es.dentasmart.controlador;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import es.dentasmart.dao.DAOException;
import es.dentasmart.dao.PacienteDAO;
import es.dentasmart.dao.sqlite.SQLitePacienteDAO;
import es.dentasmart.database.SqliteConnection;
import es.dentasmart.modelo.Paciente;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author serid
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane anchorCentral;

    @FXML
    private ListView listViewMenu;
    
    Connection conn;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
            ConectarSQlite();
            try{
            PacienteDAO pDao = new SQLitePacienteDAO(conn);
            ObservableList<Paciente> pacientes = pDao.obtenerTodos();
            for (Paciente p: pacientes){
                System.out.println(p);
            }/*
            Paciente paciente =new Paciente();
            paciente.setNombrePaciente("Ana");
            paciente.setDniPaciente("42564987f");
            paciente.setPrimerApellido("Perez");
            paciente.setSegundoApellido("Alamo");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            paciente.setFechaNac(LocalDate.parse("2-12-2000", formatter));
            pDao.insertar(paciente);*/
            
            } catch (DAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                     if(conn != null){
                         try {
                             conn.close();
                         } catch (SQLException ex) {
                             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                     }       
                    try {
                        //Creamos un objeto listview con la configuracion del panelMenuPrincipal y lo insertamos en el drawer
                        listViewMenu = FXMLLoader.load(getClass().getResource("/es/dentasmart/vista/FXMLPanelMenuPrincipal.fxml"));
                        drawer.setSidePane(listViewMenu);
                        funcionalidadMenu();
                        //drawer.open(); //lo activamos si queremos que el menu se muestre al inicio de la aplicacion
                        funcionamientoHamburguesa();
                        //Hacemos que al iniciar el programa, se muestre la ventana home
                        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/es/dentasmart/vista/FXMLPanelHome.fxml"));
                        anchorCentral.getChildren().setAll(pane);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         

    }

    /**
     * Metodo para el funcionamiento del botón de la hamburguesa
     */
    private void funcionamientoHamburguesa() {
        HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();

            if (drawer.isHidden()) {
                drawer.open();
            } else {
                drawer.close();
            }

        });
    }

    /**
     * Metodo que carga el controller del FXML del PanelMenuprincipal y le da la
     * funcionalidad a las labels para cambiar de pantallas.
     *
     * @throws IOException
     */
    private void funcionalidadMenu() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/dentasmart/vista/FXMLPanelMenuPrincipal.fxml"));
        loader.load();
        //FXMLPanelMenuPrincipalController controlerPanelPrincipal = loader.getController();
        listViewMenu.setOnMouseClicked(e -> {
            ObservableList selectedIndices
                    = listViewMenu.getSelectionModel().getSelectedIndices();
            for (Object o : selectedIndices) {
                try {
                    System.out.println("o = " + o + " (" + o.getClass() + ")");
                    switch (o.hashCode()) {
                        case 0:
                            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/es/dentasmart/vista/FXMLPanelHome.fxml"));
                            anchorCentral.getChildren().setAll(pane);
                            break;
                        case 1:
                            pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/es/dentasmart/vista/FXMLpanelCliente.fxml"));
                            anchorCentral.getChildren().setAll(pane);
                            break;
                        case 2:
                            pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/es/dentasmart/vista/FXMLPanelCitas.fxml"));
                            anchorCentral.getChildren().setAll(pane);
                            break;
                        case 3:
                            pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/es/dentasmart/vista/FXMLPanelAgenda.fxml"));
                            anchorCentral.getChildren().setAll(pane);
                            break;

                    }
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }
    
    /**
     * Método de prueba de la conexión a SQlite
     */
    private void ConectarSQlite(){
        conn = SqliteConnection.Conectar();
        if(conn != null){
            System.out.println("conectado");
            
        }else{
            System.out.println("error al conectar");
        }
    }
    
    
}