/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.dentasmart.modelo;

import java.time.LocalDate;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author seridan
 */
public class Paciente extends RecursiveTreeObject<Paciente> {
    //Declare Employees Table Columns
    private final IntegerProperty id_paciente;
    private final StringProperty dni_paciente;
    private final StringProperty localidad;
    private final StringProperty nombre_paciente;
    private final StringProperty primer_apellido;
    private final StringProperty segundo_apellido;
    private final StringProperty direccion_calle;
    private final IntegerProperty codigo_postal;
    private final StringProperty email;
    private final StringProperty telefono_fijo;
    private final StringProperty telefono_movil;
    private final SimpleObjectProperty<LocalDate> fecha_nac;
    private final StringProperty observaciones;


    //Constructor
    public Paciente() {
        this.id_paciente = new SimpleIntegerProperty();
        this.dni_paciente = new SimpleStringProperty();
        this.nombre_paciente = new SimpleStringProperty();
        this.primer_apellido = new SimpleStringProperty();
        this.segundo_apellido = new SimpleStringProperty();
        this.direccion_calle = new SimpleStringProperty();
        this.localidad = new SimpleStringProperty();
        this.codigo_postal = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
        this.telefono_fijo = new SimpleStringProperty();
        this.telefono_movil = new SimpleStringProperty();
        this.fecha_nac = new SimpleObjectProperty<>();
        this.observaciones = new SimpleStringProperty();

    }


    //paciente_id
    public int getIdPaciente() {
        return id_paciente.get();
    }

    public void setIdPaciente(int idPaciente) {
        this.id_paciente.set(idPaciente);
    }

    public IntegerProperty IdPacienteProperty() {
        return id_paciente;
    }

    //dni_paciente
    public String getDniPaciente() {
        return dni_paciente.get();
    }

    public void setDniPaciente(String dniPaciente) {
        this.dni_paciente.set(dniPaciente);
    }

    public StringProperty DniPacienteProperty() {
        return dni_paciente;
    }

    //nombre_paciente
    public String getNombrePaciente() {
        return nombre_paciente.get();
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombre_paciente.set(nombrePaciente);
    }

    public StringProperty NombrePacienteProperty() {
        return nombre_paciente;
    }

    //primer_apellido
    public String getPrimerApellido() {
        return primer_apellido.get();
    }

    public void setPrimerApellido(String primerApellido) {
        this.primer_apellido.set(primerApellido);
    }

    public StringProperty PrimerApellidoProperty() {
        return primer_apellido;
    }

    //segundo_apellido
    public String getSegundoApellido() {
        return segundo_apellido.get();
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundo_apellido.set(segundoApellido);
    }

    public StringProperty SegundoApellidoProperty() {
        return segundo_apellido;
    }

    //direccion_calle
    public String getDireccionCalle() {
        return direccion_calle.get();
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccion_calle.set(direccionCalle);
    }

    public StringProperty DireccionCalleProperty() {
        return direccion_calle;
    }

    //localidad
    public String getLocalidad() {
        return localidad.get();
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public StringProperty LocalidadProperty() {
        return localidad;
    }

    //codigo_postal
    public int getCodigoPostal() {
        return codigo_postal.get();
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigo_postal.set(codigoPostal);
    }

    public IntegerProperty CodigoPostalProperty() {
        return codigo_postal;
    }

    //email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    //telefono_fijo
    public String getTelefonoFijo() {
        return telefono_fijo.get();
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefono_fijo.set(telefonoFijo);
    }

    public StringProperty TelefonoFijoProperty() {
        return telefono_fijo;
    }

    //telefono_movil
    public String getTelefonoMovil() {
        return telefono_movil.get();
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefono_movil.set(telefonoMovil);
    }

    public StringProperty TelefonoMovilProperty() {
        return telefono_movil;
    }

    //fecha_nacimiento
    public LocalDate getFechaNac() {
        return fecha_nac.get();
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fecha_nac.set(fechaNac);
    }

    public SimpleObjectProperty<LocalDate> FechaNacProperty() {
        return fecha_nac;
    }

    //direccion_calle
    public String getObservaciones() {
        return observaciones.get();
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }

    public StringProperty ObservacionesProperty() {
        return observaciones;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id_paciente=" + id_paciente + ", dni_paciente=" + dni_paciente + ", localidad=" + localidad + ", nombre_paciente=" + nombre_paciente + ", primer_apellido=" + primer_apellido + ", segundo_apellido=" + segundo_apellido + ", direccion_calle=" + direccion_calle + ", codigo_postal=" + codigo_postal + ", email=" + email + ", telefono_fijo=" + telefono_fijo + ", telefono_movil=" + telefono_movil + ", fecha_nac=" + fecha_nac + '}';
    }

}
