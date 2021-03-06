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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class UserListController implements Initializable {

    private AddCashierFormController addCashierController;
    private EditCashierFormController editCashierController;

    private Stage secondStage;
    private Stage editStage;

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

    public User selectedUser;

    private UserDaoImpl userDao;
    private ObservableList<User> users;
    @FXML
    private TableView<User> tbUserList;
    @FXML
    private TableColumn<?, ?> colPassword;

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
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));

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
        if (secondStage == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(
                        "view/AddCashierForm.fxml"));
                BorderPane pane = loader.load();
                Scene scene = new Scene(pane);
                secondStage = new Stage();
                addCashierController = loader.getController();
                addCashierController.setMainController(this);
                secondStage.setScene(scene);
                secondStage.initOwner(bpUserListForm.getScene().getWindow());
                secondStage.initModality(Modality.APPLICATION_MODAL);
                secondStage.setTitle("Add Cashier Form");
                secondStage.show();

            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        if (secondStage.isShowing() && !secondStage.isFocused()) {
            secondStage.toFront();
        } else {
            secondStage.show();
        }
    }

    @FXML
    private void btnEditUserAct(ActionEvent event) {
        if (editStage == null) {
            try {
                if (editStage == null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource(
                            "view/EditCashierForm.fxml"));
                    BorderPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    editStage = new Stage();
                    editCashierController = loader.getController();
                    editCashierController.setMainController(this);
                    editStage.setScene(scene);
                    editStage.initOwner(bpUserListForm.getScene().getWindow());
                    editStage.initModality(Modality.APPLICATION_MODAL);
                    editStage.setTitle("Edit Cashier Form");
                    editStage.show();

                }

            } catch (IOException ex) {
                Logger.getLogger(LoginFormController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            tbUserList.refresh();
        }
        if (!editStage.isShowing()) {
            editStage.show();

        } else {
            editStage.toFront();
        }
        selectedUser = null;
        btnDelUser.setDisable(true);
    }

    @FXML
    private void btnDelUserAct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this data?");
        alert.showAndWait().ifPresent(jawaban -> {
            if (jawaban == ButtonType.OK) {
                if (getUserDao().deleteData(selectedUser) == 1) {
                    getUsers().clear();
                    getUsers().addAll(getUserDao().showAllData());

                    tbUserList.refresh();
                }
            }

        });

        selectedUser = null;
        btnDelUser.setDisable(true);
    }

    @FXML
    private void tbUserClickedAct(MouseEvent event) {
        selectedUser = tbUserList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            btnDelUser.setDisable(false);

        }

    }

}
