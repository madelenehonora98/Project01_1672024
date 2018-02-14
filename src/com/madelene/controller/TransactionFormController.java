/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.entity.Barang;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class TransactionFormController implements Initializable {

    @FXML
    private Button btnBackCashier;
    @FXML
    private BorderPane bpTransaction;
    @FXML
    private Button btnSubmit;
    @FXML
    private ComboBox<Barang> cbKodeBarang;
    @FXML
    private DatePicker dpTanggal;
    @FXML
    private Button btnAddCart;
    @FXML
    private Button btnEditCart;
    @FXML
    private Button btnDelCart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnBackCashierAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/CashierForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Cashier Form");
            secondStage.show();

            bpTransaction.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSubmitAct(ActionEvent event) {
    }

    @FXML
    private void cbKodeBarangAct(ActionEvent event) {
    }

    @FXML
    private void dpTanggalAct(ActionEvent event) {
    }

    @FXML
    private void btnAddCartAct(ActionEvent event) {
    }

    @FXML
    private void btnEditCartAct(ActionEvent event) {
    }

    @FXML
    private void btnDelCartAct(ActionEvent event) {
    }

}
