/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import app_services.AppServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import view_model.UsuarioCreateViewModel;
import view_model.UsuarioPublicViewModel;
import view_model.UsuarioUpdateViewModel;
import view_model.UsuarioViewModel;

/**
 * FXML Controller class
 *
 * @author solis
 */
public class UsuarioControlador implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<UsuarioViewModel> tvUsuariosInterno;
    @FXML
    private TableColumn<UsuarioViewModel, Integer> tciID;
    @FXML
    private TableColumn<UsuarioViewModel, String> tciNombre;
    @FXML
    private TableColumn<UsuarioViewModel, String> tciNombreUsuario;
    @FXML
    private TableColumn<UsuarioViewModel, String> tciCorreo;
    @FXML
    private TableView<UsuarioPublicViewModel> tvUsuariosPublico;
    @FXML
    private TableColumn<UsuarioPublicViewModel, Integer> tcpID;
    @FXML
    private TableColumn<UsuarioPublicViewModel, String> tcpNombreUsuario;
    @FXML
    private TableColumn<UsuarioPublicViewModel, String> tcpCorreo;

    private ObservableList<UsuarioViewModel> listaInterna;
    private ObservableList<UsuarioPublicViewModel> listaPublica;
    @FXML
    private TableColumn<UsuarioViewModel, String> tciFecha;
    @FXML
    private TextField txtFecha;
    
    int id = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaInterna = FXCollections.observableArrayList();
        this.listaPublica = FXCollections.observableArrayList();
        listarUsuarios();
        listarUsuariosPublicos();
    }    
    
    public void registrar(String nombre, String nombreUsuario, String fechaNac, String correo)
    {
        AppServices app = new AppServices();
        UsuarioCreateViewModel u = new UsuarioCreateViewModel(nombre, nombreUsuario, fechaNac, correo);
        String respuesta = app.save(u);
        
        if(respuesta.equals("OK"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Exito");
            alert.setContentText("El usuario se guardo con exito");
            alert.showAndWait();
            listarUsuarios();
            listarUsuariosPublicos();
            this.id = 0;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(respuesta);
            alert.showAndWait();
        }
        
    }
    
    public void actualizar (int id, String nombreUsuario, String correo)
    {
        AppServices app = new AppServices();
        UsuarioUpdateViewModel u = new UsuarioUpdateViewModel(id, nombreUsuario, correo);
        String respuesta = app.update(u);
        
        if(respuesta.equals("OK"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Exito");
            alert.setContentText("El usuario se guardo con exito");
            alert.showAndWait();
            listarUsuarios();
            listarUsuariosPublicos();
            this.id = 0;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(respuesta);
            alert.showAndWait();
        }
    }
    
    public void listarUsuarios()
    {
        AppServices app = new AppServices();
        this.listaInterna = app.getUsers();
        
        this.tvUsuariosInterno.setItems(listaInterna);
        
        this.tciID.setCellValueFactory(new PropertyValueFactory("id"));
        this.tciNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tciNombreUsuario.setCellValueFactory(new PropertyValueFactory("nombreUsuario"));
        this.tciFecha.setCellValueFactory(new PropertyValueFactory("fechaNac"));
        this.tciCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    public void listarUsuariosPublicos()
    {
        AppServices app = new AppServices();
        this.listaPublica = app.getUsersPublic();
        
        this.tvUsuariosPublico.setItems(listaPublica);
        
        this.tcpID.setCellValueFactory(new PropertyValueFactory("id"));
        this.tcpNombreUsuario.setCellValueFactory(new PropertyValueFactory("nombreUsuario"));
        this.tcpCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    public void limpiar()
    {
        this.id = 0;
        this.txtNombre.setText("");
        this.txtNombreUsuario.setText("");
        this.txtCorreo.setText("");
        this.txtFecha.setText("");
    }

    @FXML
    private void guardar(ActionEvent event) {
        
        if(this.id == 0)
        {
            registrar(this.txtNombre.getText(), this.txtNombreUsuario.getText(), this.txtFecha.getText(), this.txtCorreo.getText());
            limpiar();
        }
        else
        {
            actualizar(this.id, this.txtNombreUsuario.getText(), this.txtCorreo.getText());
            limpiar();
        }
        
    }

    @FXML
    private void seleccionarInterno(MouseEvent event) {
        
        UsuarioViewModel modelo = this.tvUsuariosInterno.getSelectionModel().getSelectedItem();
        if(modelo != null)
        {
            this.id = modelo.getId();
            this.txtNombre.setText(modelo.getNombre());
            this.txtNombreUsuario.setText(modelo.getNombreUsuario());
            this.txtFecha.setText(modelo.getFechaNac());
            this.txtCorreo.setText(modelo.getCorreo());
            
        }
        
        
    }

    @FXML
    private void seleccionarPublico(MouseEvent event) {
        UsuarioPublicViewModel modelo = this.tvUsuariosPublico.getSelectionModel().getSelectedItem();
        
        if(modelo != null)
        {
            this.id = modelo.getId();
            this.txtNombreUsuario.setText(modelo.getNombreUsuario());
            this.txtCorreo.setText(modelo.getCorreo());
            
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiar();
    }
    
}
