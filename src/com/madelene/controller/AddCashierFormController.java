/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.entity.User;
import com.madelene.entity.UserRole;
import com.madelene.utility.Utility;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private ComboBox<UserRole> cbIdUserRole;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtVerifyPass;
    @FXML
    private RadioButton rbLaki;
    @FXML
    private RadioButton rbPerempuan;

    public ToggleGroup group;

    private UserListController userListController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIdUserRole.setItems(value);

//        group = new ToggleGroup();
//        rbLaki.setToggleGroup(group);
//        rbPerempuan.setToggleGroup(group);
//        rbLaki.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
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

    public void setMainController(
            UserListController userListController) {
        this.userListController
                = userListController;

    }

    @FXML
    private void btnSubmitAddCashierAct(ActionEvent event) {
        if (!Utility.isEmptyField(txtIdPengguna, txtNamaDepan, txtNamaBelakang,
                txtAlamat, txtNoTelepon, txtPass, txtVerifyPass)) {
            User user = new User();
            user.setIdPengguna(txtIdPengguna.getText().trim());
            user.setNamaDepan(txtNamaDepan.getText().trim());
            user.setNamaBelakang(txtNamaBelakang.getText().trim());
            user.setAlamat(txtAlamat.getText().trim());
            user.setNoTelepon(txtNoTelepon.getText().trim());

            //Buat ID ROLE SAMA JENIS KELAMIN
//            user.setIdPengguna(txtIdPengguna.getText().trim());
//            user.setIdPengguna(txtIdPengguna.getText().trim());
            //buat cek pass yang sudah sama atau belum
            if (!txtPass.getText().trim().equals(txtVerifyPass.getText().
                    trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Password tidak sama");
                alert.showAndWait();

                txtPass.clear();
                txtVerifyPass.clear();
            } else {
                user.setPassword(txtPass.getText().trim());
            }

            if (userListController.getUserDao().addData(
                    user) == 1) {

                userListController.getUsers().clear();
                userListController.getUsers().addAll(userListController.
                        getUserDao().showAllData());
            }

            txtIdPengguna.clear();
            txtNamaDepan.clear();
            txtNamaBelakang.clear();
            txtAlamat.clear();
            txtPass.clear();
            txtVerifyPass.clear();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Masih ada yang kosong");
            alert.showAndWait();
        }
    }

    @FXML
    private void cbIdUserRoleAct(ActionEvent event) {
    }

    @FXML
    private void txtVerifyPassAct(ActionEvent event) {
        if (!txtPass.equals(txtVerifyPass)) {

        }
    }

}
