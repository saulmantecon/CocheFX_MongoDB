package org.example;

import DAO.CocheDAO;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoDatabase;
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

     ArrayList<String> listaTipos = new ArrayList<>(Arrays.asList("Coche", "Moto", "Camion", "Tanque"));
    ObservableList<Coche> listacoches;


    MongoClient con;



    @FXML
    void onClickCrear(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        String marca = textfieldMarca.getText();
        String modelo = textfieldModelo.getText();
        String tipo = comboboxTipo.getValue();

        Coche coche = new Coche(matricula, marca, modelo, tipo);
        Alerta.mostrarAlerta(CocheDAO.crearCoche(coche));
        actualizarTableView();
    }

    @FXML
    void onClickEliminar(ActionEvent event) {
        String matricula = textfieldMatricula.getText();
        Alerta.mostrarAlerta(CocheDAO.eliminarCoche(matricula));
        actualizarTableView();
    }

    @FXML
    void onClickLimpiar(ActionEvent event) {
        textfieldMatricula.clear();
        textfieldMarca.clear();
        textfieldModelo.clear();
        comboboxTipo.setValue(null);

    }

    @FXML
    void onClickModificar(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = DatabaseManager.getConexion();
        MongoDatabase database = con.getDatabase("prueba");
        // Me devuelve una colección si no existe la crea
        CocheDAO.collectionCoches = database.getCollection("coches");
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
        actualizarTableView();

    }//initialize

    public void clickFilaTableView(MouseEvent mouseEvent) {
            Coche c = idTableView.getSelectionModel().getSelectedItem();
            if (c!=null){
                textfieldMatricula.setText(c.getMatricula());
                textfieldMarca.setText(c.getMarca());
                textfieldModelo.setText(c.getModelo());
                comboboxTipo.setValue(c.getTipo());
            }


    }

    public void actualizarTableView(){
        listacoches = FXCollections.observableArrayList(CocheDAO.listarCoches());
        idTableView.setItems(listacoches);
    }
}
