package org.example.cochefx_mongodb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MenuViewController {

    @FXML
    private ComboBox<?> comboboxTipo;

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

    @FXML
    void onClickCrear(ActionEvent event) {

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

}
