/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.dao.UserDaoImpl;
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
    @FXML
    private ComboBox<User> cbIdPengguna;
    private UserDaoImpl userDaoImpl;
    private ObservableList<User> users;
    @FXML
    private Button btnSubmitEditCashier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbIdPengguna.setItems(getUsers());

    }

    public ObservableList<User> getUsers() {
        if (users == null) {
            users = FXCollections.observableArrayList();
            users.addAll(getUserDao().showAllData());
        }

        return users;
    }

    public UserDaoImpl getUserDao() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }

    public ObservableList<UserRole> getRoles() {
        if (roles == null) {
            roles = FXCollections.observableArrayList();
            roles.add(cbIdPengguna.getValue().getIdUserRole());
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

    @FXML
    private void cbIdPenggunaAct(ActionEvent event) {
        cbIdUserRole.setItems(getRoles());
        cbIdUserRole.setValue(cbIdPengguna.getValue().getIdUserRole());
        txtNamaDepan.setText(cbIdPengguna.getValue().getNamaDepan());
        txtNamaBelakang.setText(cbIdPengguna.getValue().
                getNamaBelakang());
        txtAlamat.setText(cbIdPengguna.getValue().getAlamat());
        txtNoTelepon.setText(cbIdPengguna.getValue().getNoTelepon());
        txtPass.setText(cbIdPengguna.getValue().getPassword());
        txtVerifyPass.setText(cbIdPengguna.getValue().getPassword());
        if (cbIdPengguna.getValue().getJenisKelamin().
                equals("Laki-laki")) {
            rbLaki.setSelected(true);
            rbPerempuan.setSelected(false);
        } else if (cbIdPengguna.getValue().getJenisKelamin().equals(
                "Perempuan")) {
            rbPerempuan.setSelected(true);
            rbLaki.setSelected(false);
        }

    }

    @FXML
    private void btnSubmitEditCashierAct(ActionEvent event) {

        if (!Utility.isEmptyField(txtNamaDepan, txtNamaBelakang,
                txtAlamat, txtNoTelepon, txtPass, txtVerifyPass)) {
            User user = new User();
            user.setIdPengguna(cbIdPengguna.getValue().getIdPengguna());
            user.setNamaDepan(txtNamaDepan.getText().trim());
            user.setNamaBelakang(txtNamaBelakang.getText().trim());
            user.setAlamat(txtAlamat.getText().trim());

            if (group.getSelectedToggle().equals(rbLaki)) {
                user.setJenisKelamin("Laki-laki");
            } else if (group.getSelectedToggle().equals(rbPerempuan)) {
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

            if (userListController.getUserDao().updateData(user) == 1) {

                userListController.getUsers().clear();
                userListController.getUsers().addAll(userListController.
                        getUserDao().showAllData());
            }

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

}
