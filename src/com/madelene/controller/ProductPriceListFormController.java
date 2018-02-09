/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class ProductPriceListFormController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private TableView<?> tbleProduct;
    @FXML
    private TableColumn<?, ?> colKodeBarang;
    @FXML
    private TableColumn<?, ?> colNamaBarang;
    @FXML
    private TableColumn<?, ?> colHargaBeli;
    @FXML
    private TableColumn<?, ?> colHargaJual;
    @FXML
    private TableColumn<?, ?> colStok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnBackAct(ActionEvent event) {

    }

    @FXML
    private void tbProductMouseClicked(MouseEvent event) {
    }

}
