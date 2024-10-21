package org.example;

import DAO.CocheDAO;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Coche;
import org.bson.Document;
import util.Alerta;
import util.DatabaseManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static DAO.CocheDAO.collectionTipos;

public class MenuViewController implements Initializable {

    @FXML
    private TableView<Coche> idTableView;

    @FXML
    private TableColumn<Coche, String> idColumnMarca;

    @FXML
    private TableColumn<Coche, String> idColumnMatricula;

    @FXML
    private TableColumn<Coche, String> idColumnModelo;

    @FXML
    private TableColumn<Coche, String> idColumnTipo;

    @FXML
    private ComboBox<String> comboboxTipo;

    @FXML
    private TextField textfieldMarca;

    @FXML
    private TextField textfieldMatricula;

    @FXML
    private TextField textfieldModelo;

    private ArrayList<String> listaTipos = new ArrayList<>(Arrays.asList("Coche", "Moto", "Camion", "Tanque"));

    private ObservableList<Coche> listacoches;

    static Coche cocheSeleccionado;

    MongoClient con;


    @FXML
    void onClickCrear(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        String marca = textfieldMarca.getText();
        String modelo = textfieldModelo.getText();
        String tipo = comboboxTipo.getValue();
        Coche coche = new Coche(matricula, marca, modelo, tipo);
        if (CocheDAO.crearCoche(coche)){
            listacoches.add(coche);
            Alerta.mostrarAlerta("Coche Registrado con exito");
        }else {
            Alerta.mostrarAlerta("Error al registrar el coche");
        }
    }//onClickCrear


    @FXML
    void onClickEliminar(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        String marca = textfieldMarca.getText();
        String modelo = textfieldModelo.getText();
        String tipo = comboboxTipo.getValue();
        Coche coche = new Coche(matricula, marca, modelo, tipo);
        if (CocheDAO.eliminarCoche(matricula)){
            listacoches.remove(coche);
            Alerta.mostrarAlerta("Coche Eliminado con exito");
        }else {
            Alerta.mostrarAlerta("Error al eliminar el coche");
        }
      //  actualizarTableView();
    }//onCLickEliminar


    @FXML
    void onClickLimpiar(ActionEvent event) {
        textfieldMatricula.clear();
        textfieldMarca.clear();
        textfieldModelo.clear();
        comboboxTipo.setValue(null);

    }//onClickLimpiar


    @FXML
    void onClickModificar(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        String marca = textfieldMarca.getText();
        String modelo = textfieldModelo.getText();
        String tipo = comboboxTipo.getValue();

        Coche coche = new Coche(matricula, marca, modelo, tipo);
        if (CocheDAO.actualizarCoche(coche,cocheSeleccionado)){
            listacoches.add(listacoches.indexOf(cocheSeleccionado),coche);
            listacoches.remove(cocheSeleccionado);
            cocheSeleccionado = coche;//cambio el valor del coche seleccionado al modificado correctamente para que no me de error.
            Alerta.mostrarAlerta("Coche Modificado con exito");
        }else {
            Alerta.mostrarAlerta("Error al modificar el coche, ");
        }
    }//onClickModificar


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = DatabaseManager.getConexion();
        MongoDatabase database = con.getDatabase("Concesionario");
        // Me devuelve una colección si no existe la crea
        CocheDAO.collectionCoches = database.getCollection("coches");
        CocheDAO.collectionCoches.createIndex(new Document("matricula",1), new IndexOptions().unique(true)); //le meto un indice ascemdente y le digo que sea unique para hacer comprobaciones
        collectionTipos = database.getCollection("tipos");
        Document tipos = new Document();
        tipos.append("tipos", listaTipos);
        comboboxTipo.getItems().addAll(listaTipos);
        try {
            //La función ".insertOne()" se utiliza para insertar el documento en la colección.
            collectionTipos.insertOne(tipos);
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("El documento con esa identificación ya existe");
            }
        }
        idColumnMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        idColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        idColumnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        idColumnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        listacoches = FXCollections.observableArrayList(CocheDAO.listarCoches());
        idTableView.setItems(listacoches);
    }//initialize

    public void clickFilaTableView(MouseEvent mouseEvent) {
             Coche c = idTableView.getSelectionModel().getSelectedItem();
            if (c!=null){
                textfieldMatricula.setText(c.getMatricula());
                textfieldMarca.setText(c.getMarca());
                textfieldModelo.setText(c.getModelo());
                comboboxTipo.setValue(c.getTipo());
                cocheSeleccionado=c; //igualo el coche seleccionado a una variable static para poder utilizarlo
            }
    }//clickFilaTableView

}//menuViewController
