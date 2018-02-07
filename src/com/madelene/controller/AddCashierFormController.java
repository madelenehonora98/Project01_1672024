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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class AddCashierFormController implements Initializable {

    @FXML
    private BorderPane bpAddCashier;
    @FXML
    private Button btnBackOwner;
    @FXML
    private Button btnSubmitAddCashier;
    @FXML
    private TextField txtIdPengguna;
    @FXML
    private TextField txtNamaDepan;
    @FXML
    private TextField txtNamaBelakang;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtNoTelepon;
    @FXML
    private ComboBox<?> cbIdUserRole;
    @FXML
    private ComboBox<?> cbJnsKelamin;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtVerifyPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/UserList.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("User List Form");
            secondStage.show();

            //Close login stage
            bpAddCashier.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSubmitAddCashierAct(ActionEvent event) {
    }

    @FXML
    private void cbIdUserRoleAct(ActionEvent event) {
    }

    @FXML
    private void cbJnsKelaminAct(ActionEvent event) {
    }

    @FXML
    private void txtVerifyPassAct(ActionEvent event) {
    }

}
