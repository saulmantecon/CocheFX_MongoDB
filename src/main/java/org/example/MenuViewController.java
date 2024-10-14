package org.example;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.bson.Document;
import util.DatabaseManager;

import java.net.URL;
import java.util.ArrayList;
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

     String listaTipos[] = {"Coche", "fragoneta", "Camion", "tanque"};

    MongoCollection<Document> collectionCoches;
    MongoCollection<Document> collectionTipos;



    @FXML
    void onClickCrear(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        String marca = textfieldMarca.getText();
        String modelo = textfieldModelo.getText();
        String tipo = comboboxTipo.getValue();
        Document coche = new Document();
        coche.append("matricula", matricula).append("marca", marca).append("modelo", modelo);
        Document tipoCoche = new Document();
        tipoCoche.append("tipo", tipo);

        try {
            //La función ".insertOne()" se utiliza para insertar el documento en la colección.
            collectionCoches.insertOne(coche);
            collectionTipos.insertOne(tipoCoche);
            System.out.println("Documento Insertado Correctamente. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("El documento con esa identificación ya existe");
            }
        }
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
            MongoDatabase database =DatabaseManager.getDatabase();
            //Me devuelve una coleccion si no existe la crea
            collectionCoches = database.getCollection("coches");
            collectionTipos = database.getCollection("tipos");
            comboboxTipo.getItems().addAll(listaTipos);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
