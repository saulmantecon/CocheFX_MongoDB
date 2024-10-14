package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Coche;
import org.bson.Document;
import util.DatabaseManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuViewController implements Initializable {

    @FXML
    private ComboBox<String> comboboxTipo;

    @FXML
    private TableColumn<?, ?> idColumnMarca;

    @FXML
    private TableColumn<?, ?> idColumnMatricula;

    @FXML
    private TableColumn<?, ?> idColumnModelo;

    @FXML
    private TableColumn<?, ?> idColumnTipo;

    @FXML
    private TableView<?> idTableView;

    @FXML
    private TextField textfieldMarca;

    @FXML
    private TextField textfieldMatricula;

    @FXML
    private TextField textfieldModelo;

    MongoClient con;
    MongoCollection<Document> collection=null;
    String json;
    Document doc;

    @FXML
    void onClickCrear(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        String marca = textfieldMarca.getText();
        String modelo = textfieldModelo.getText();
        String tipo = comboboxTipo.getValue();
        Coche coche = new Coche(matricula, marca, modelo, tipo);



    }

    @FXML
    void onClickEliminar(ActionEvent event) {

    }

    @FXML
    void onClickLimpiar(ActionEvent event) {

    }

    @FXML
    void onClickModificar(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            con= DatabaseManager.conectar();
            MongoDatabase database= con.getDatabase("taller");
            database.createCollection("Concesionario");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
