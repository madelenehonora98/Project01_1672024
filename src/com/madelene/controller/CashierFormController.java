/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class CashierFormController implements Initializable {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnTransaction;
    @FXML
    private Button btnProductPriceList;
    @FXML
    private BorderPane bpCashierForm;
    @FXML
    private ImageView imgCashier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgCashier.setImage(new Image("/com/madelene/pictures/Transaction.png"));
    }

    @FXML
    private void btnLogOut(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/LoginForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Login Form");
            secondStage.show();

            //Close login stage
            bpCashierForm.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnTransactionAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/TransactionForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Transaction Form");
            secondStage.show();

            //Close login stage
            bpCashierForm.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnProductPriceListAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/ProductPriceListForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Product and Price List Form");
            secondStage.show();

            //Close login stage
            bpCashierForm.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

}
