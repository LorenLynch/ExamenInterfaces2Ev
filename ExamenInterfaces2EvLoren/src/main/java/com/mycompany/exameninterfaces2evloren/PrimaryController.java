package com.mycompany.exameninterfaces2evloren;

import static java.lang.Double.parseDouble;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;

public class PrimaryController implements Initializable{

    private Informe informe = new Informe();
    
    @FXML
    private TextField tvNombre;
    @FXML
    private TextField tvApellidos;
    @FXML
    private TextField tvAcceso;
    @FXML
    private TextField tvSistemas;
    @FXML
    private TextField tvInterfaces;
    @FXML
    private TextField tvMultimedia;
    @FXML
    private TextField tvServicios;
    @FXML
    private TextField tvEmpresa;
    @FXML
    private TextField tvBigdata;
    @FXML
    private TableView<Alumno> tabla;
    @FXML
    private TableColumn<Alumno, String> cNombre;
    @FXML
    private TableColumn<Alumno, String> cApellidos;
    @FXML
    private TableColumn<Alumno, Double> cAcceso;
    @FXML
    private TableColumn<Alumno, Double> cSistemas;
    @FXML
    private TableColumn<Alumno, Double> cInterfaces;
    @FXML
    private TableColumn<Alumno, Double> cMultimedia;
    @FXML
    private TableColumn<Alumno, Double> cServicios;
    @FXML
    private TableColumn<Alumno, Double> cEmpresa;
    @FXML
    private TableColumn<Alumno, Double> cBigdata;
    @FXML
    private Label labelInfo;
    
    List<Alumno> listaAlumnos = new ArrayList<Alumno>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alumno alumno1 = new Alumno("Juan", "Pérez López", 8.5, 7.2, 9.1, 6.8, 7.5, 8.9, 6.3);
        Alumno alumno2 = new Alumno("María", "González Sánchez", 6.3, 7.8, 8.2, 9.3, 6.9, 7.5, 8.6);
        Alumno alumno3 = new Alumno("Pedro", "Martínez García", 9.2, 8.5, 6.7, 7.8, 8.2, 9.1, 7.5);
        listaAlumnos.add(alumno1);
        listaAlumnos.add(alumno2);
        listaAlumnos.add(alumno3);
        inicializarTabla();
        tabla.getItems().clear();
        tabla.getItems().addAll(listaAlumnos);
    }
    
    private void inicializarTabla(){
        cNombre.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre"));
        cApellidos.setCellValueFactory(new PropertyValueFactory<Alumno,String>("apellidos"));
        cAcceso.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("AD"));
        cSistemas.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("SGE"));
        cInterfaces.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("DI"));
        cMultimedia.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("PMDM"));
        cServicios.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("PSP"));
        cEmpresa.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("EIE"));
        cBigdata.setCellValueFactory(new PropertyValueFactory<Alumno,Double>("HLC"));
    }
    
    @FXML
    private void insertarAlumno(ActionEvent event) {
        Alumno alumnoAnadir = crearAlumno();
        if(alumnoAnadir!=null){
            listaAlumnos.add(alumnoAnadir);
        }

        tabla.getItems().clear();
        tabla.getItems().addAll(listaAlumnos);
    }
    
    private Alumno crearAlumno() {
        Alumno alumno = new Alumno();
        
        alumno.setNombre(tvNombre.getText());
        if(alumno.getNombre().equals("")){
            labelInfo.setText("Hay un campo vacío");
            return null;
        }
        
        alumno.setApellidos(tvApellidos.getText());
        if(alumno.getApellidos().equals("")){
            labelInfo.setText("Hay un campo vacío");
            return null;
        }
        
        alumno.setAD(parseDouble(tvAcceso.getText()));
        
        if(alumno.getAD()>10.0 || alumno.getAD() < 0.0 || alumno.getAD().equals("")){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        alumno.setSGE(parseDouble(tvSistemas.getText()));
        if(alumno.getSGE()>10.0 || alumno.getSGE() < 0.0){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        alumno.setDI(parseDouble(tvInterfaces.getText()));
        if(alumno.getDI()>10.0 || alumno.getDI() < 0.0){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        alumno.setPMDM(parseDouble(tvMultimedia.getText()));
        if(alumno.getPMDM()>10.0 || alumno.getPMDM() < 0.0){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        alumno.setPSP(parseDouble(tvServicios.getText()));
        if(alumno.getPSP()>10.0 || alumno.getPSP() < 0.0){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        alumno.setEIE(parseDouble(tvEmpresa.getText()));
        if(alumno.getEIE()>10.0 || alumno.getEIE() < 0.0){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        alumno.setHLC(parseDouble(tvBigdata.getText()));
        if(alumno.getHLC()>10.0 || alumno.getHLC() < 0.0){
            labelInfo.setText("No pueden existir notas fuera del rango 0-10");
            return null;
        }
        vaciarTextViews();
        labelInfo.setText("Alumno añadido correctamente");
        return alumno;
    }
    
    @FXML
    private void jaspersoft(){
        try {
            informe.showReport();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            informe.pdfReport();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void salir(){
        System.exit(0);
    }

    private void vaciarTextViews() {
        tvNombre.setText("");
        tvApellidos.setText("");
        tvAcceso.setText("");
        tvSistemas.setText("");
        tvInterfaces.setText("");
        tvMultimedia.setText("");
        tvServicios.setText("");
        tvEmpresa.setText("");
        tvBigdata.setText("");
    }
    
    @FXML
    private void alumnoSeleccionado(MouseEvent event){
        Alumno alumnoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Datos del alumno ");
        alert.setHeaderText(alumnoSeleccionado.getNombre()+" "+alumnoSeleccionado.getApellidos());
        Double media = calcularMedia(alumnoSeleccionado);
        Integer suspensas = numeroAsignaturasSuspensas(alumnoSeleccionado);
        
        if(suspensas > 0){
            alert.setContentText("La media del alumno es de: "+media+"\nEl alumno tiene "+numeroAsignaturasSuspensas(alumnoSeleccionado)+" asignaturas suspensas");
        }else{
            alert.setContentText("La media del alumno es de: "+media+"\nEl alumno no tiene asignaturas suspensas");
        }
        alert.showAndWait();
    }
    
    private Double calcularMedia(Alumno alumno){
        Double media = (alumno.getAD()+alumno.getDI()+alumno.getEIE()+alumno.getHLC()+alumno.getPMDM()+alumno.getPSP()+alumno.getSGE())/7;
        return media;
    }
    
    private Integer numeroAsignaturasSuspensas(Alumno alumno){
        Integer asignaturasSuspensas = 0;
        List<Double> notas = new ArrayList<Double> ();
        notas.add(alumno.getAD());
        notas.add(alumno.getDI());
        notas.add(alumno.getEIE());
        notas.add(alumno.getHLC());
        notas.add(alumno.getPMDM());
        notas.add(alumno.getPSP());
        notas.add(alumno.getSGE());
        
        for(Double d: notas){
            if(d < 5.0){
                asignaturasSuspensas++;
            }
        }
        return asignaturasSuspensas;
    }
}
