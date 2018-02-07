/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.UserDaoImpl;
import com.madelene.entity.User;
import com.madelene.entity.UserRole;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class LoginFormController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private BorderPane bpLogin;

    public UserDaoImpl getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    private ObservableList<User> users;

    public ObservableList<User> getUser() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }
        return users;
    }

    private UserDaoImpl userDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnLogInAct(ActionEvent event) throws IOException {
        User user = new User();
        user.setIdPengguna(txtUsername.getText());
        user.setPassword(txtPassword.getText());
        if (getUserDao().getData(user) != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Login berhasil");
            alert.showAndWait();

            FXMLLoader loader = new FXMLLoader();

            UserRole userRole = user.getIdUserRole();

            //Antara owner atau cashier
            loader.setLocation(MainApp.class.getResource(
                    "view/LoginForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Login Form");
            secondStage.show();

            //Close login stage
            bpLogin.getScene().getWindow().hide();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username atau Password anda salah!");
            alert.showAndWait();

        }
    }

}
