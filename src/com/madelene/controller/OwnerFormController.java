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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class OwnerFormController implements Initializable {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnSeeStatistic;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnSeeTopSales;
    @FXML
    private Button btnAddCashier;
    @FXML
    private Button btnSeeStatistic1;
    @FXML
    private Button btnSeeStatistic11;
    @FXML
    private BorderPane brdPaneOwner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSeeStatisticAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/SeeStatisticForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("See Statistic Form");
            secondStage.show();

            //Close login stage
            brdPaneOwner.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddProductAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/AddProductForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Add Product Form");
            secondStage.show();

            //Close login stage
            brdPaneOwner.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSeeTopSalesAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/SeeTopSalesForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("See Top Sales Form");
            secondStage.show();

            //Close login stage
            brdPaneOwner.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddCashierAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/AddCashierForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Add Cashier Form");
            secondStage.show();

            //Close login stage
            brdPaneOwner.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnLogOutAct(ActionEvent event) {

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
            brdPaneOwner.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

}
