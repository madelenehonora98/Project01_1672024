/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.dao.UserRoleDaoImpl;
import com.madelene.entity.User;
import com.madelene.entity.UserRole;
import com.madelene.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class EditCashierFormController implements Initializable {

    @FXML
    private BorderPane bpEditCashier;
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
    private RadioButton rbLaki;
    @FXML
    private ToggleGroup group;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtVerifyPass;
    @FXML
    private RadioButton rbPerempuan;

    private UserListController userListController;
    private ObservableList<UserRole> roles;
    private UserRoleDaoImpl roleDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIdUserRole.setItems(getRoles());
        txtIdPengguna.setText(userListController.selectedUser.getIdPengguna());
        txtNamaDepan.setText(userListController.selectedUser.getNamaDepan());
        txtNamaBelakang.setText(userListController.selectedUser.
                getNamaBelakang());
        txtAlamat.setText(userListController.selectedUser.getAlamat());
        txtNoTelepon.setText(userListController.selectedUser.getNoTelepon());
        txtPass.setText(userListController.selectedUser.getPassword());
        txtVerifyPass.setText(userListController.selectedUser.getPassword());
        if (userListController.selectedUser.getJenisKelamin().
                equals("Laki-laki")) {
            rbLaki.setSelected(true);
            rbPerempuan.setSelected(false);
        } else if (userListController.selectedUser.getJenisKelamin().equals(
                "Perempuan")) {
            rbPerempuan.setSelected(true);
            rbLaki.setSelected(false);
        }

    }

    public ObservableList<UserRole> getRoles() {
        if (roles == null) {
            roles = FXCollections.observableArrayList();
            roles.add(userListController.selectedUser.getIdUserRole());
        }
        return roles;

    }

    public UserRoleDaoImpl getRoleDao() {
        if (roleDao == null) {
            roleDao = new UserRoleDaoImpl();
        }
        return roleDao;
    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
        bpEditCashier.getScene().getWindow().hide();
    }

    public void setMainController(UserListController userListController) {
        this.userListController = userListController;
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

            if (group.getSelectedToggle().equals(rbLaki)) {
                user.setJenisKelamin("Laki-laki");
            } else if (group.getToggles().equals(rbPerempuan)) {
                user.setJenisKelamin("Perempuan");
            }
            user.setIdUserRole(cbIdUserRole.getValue());

            if (!isNumber(txtNoTelepon.getText(), "Nomor Telepon")) {
                txtNoTelepon.clear();

            } else {
                user.setNoTelepon(txtNoTelepon.getText().trim());
            }

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

            if (userListController.getUserDao().updateData(
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
            txtNoTelepon.clear();
            cbIdUserRole.setValue(null);

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

    public static final boolean isNumber(String number, String name) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            Utility.showAlert("Not a number", name + " is not a number",
                    Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

}