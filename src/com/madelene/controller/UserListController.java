/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.UserDaoImpl;
import com.madelene.entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class UserListController implements Initializable {

    @FXML
    private Button btnBackOwner;
    @FXML
    private BorderPane bpUserListForm;
    @FXML
    private Button btnAddUser;
    @FXML
    private Button btnEditUser;
    @FXML
    private Button btnDelUser;
    @FXML
    private TableColumn<User, String> colIdPengguna;
    @FXML
    private TableColumn<User, String> colNamaDepan;
    @FXML
    private TableColumn<User, String> colNamaBelakang;
    @FXML
    private TableColumn<User, String> colAlamat;
    @FXML
    private TableColumn<User, String> colNoTelepon;
    @FXML
    private TableColumn<User, String> colJabatan;

    private UserDaoImpl userDao;
    ObservableList<User> users;
    @FXML
    private TableView<User> tbUserList;

    /**
     * Initializes the controller class.
     */
    public UserDaoImpl getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public ObservableList<User> getUsers() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }
        return users;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDao = new UserDaoImpl();
        users = FXCollections.observableArrayList();
        users = userDao.showAllData();

        tbUserList.setItems(users);

        colIdPengguna.setCellValueFactory(new PropertyValueFactory<>(
                "IdPengguna"));
        colNamaDepan.
                setCellValueFactory(new PropertyValueFactory<>("NamaDepan"));
        colNamaBelakang.setCellValueFactory(new PropertyValueFactory<>(
                "NamaBelakang"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("Alamat"));
        colNoTelepon.
                setCellValueFactory(new PropertyValueFactory<>("NoTelepon"));
        colJabatan.setCellValueFactory(p -> p.getValue().getIdUserRole().
                JabatanProperty());

    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/OwnerForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Owner Form");
            secondStage.show();

            bpUserListForm.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddUserAct(ActionEvent event) {
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

        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEditUserAct(ActionEvent event) {
    }

    @FXML
    private void btnDelUserAct(ActionEvent event) {
    }

}
