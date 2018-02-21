/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.BarangDaoImpl;
import com.madelene.entity.Barang;
import com.madelene.utility.Utility;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class EditProductFormController implements Initializable {

    @FXML
    private Button btnBackOwner;
    @FXML
    private TextField txtNamaBarang;
    @FXML
    private TextField txtHargaBeli;
    @FXML
    private TextField txtHargaJual;
    @FXML
    private TextField txtStok;

    private ProductPriceListOwnerFormController productPriceListOwnerFormController;
    @FXML
    private BorderPane bpEditProduct;
    @FXML
    private Button btnSubmitEditProduct;
    @FXML
    private ComboBox<Barang> cbKodeBarang;
    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;

    /**
     * Initializes the controller class.
     */
    public ObservableList<Barang> getBarangs() {
        if (barangs == null) {
            barangs = FXCollections.observableArrayList();
            barangs.addAll(getBarangDao().showAllData());
        }
        return barangs;

    }

    public BarangDaoImpl getBarangDao() {
        if (barangDao == null) {
            barangDao = new BarangDaoImpl();
        }
        return barangDao;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbKodeBarang.setItems(getBarangs());

    }

    public void setMainController(
            ProductPriceListOwnerFormController productPriceListOwnerFormController) {
        this.productPriceListOwnerFormController
                = productPriceListOwnerFormController;

    }

    @FXML
    private void btnSubmitEditProductAct(ActionEvent event) {
        if (!Utility.isEmptyField(txtNamaBarang, txtHargaBeli,
                txtHargaJual, txtStok)) {
            Barang barang = new Barang();
            barang.setKodeBarang(cbKodeBarang.getValue().getKodeBarang());
            barang.setNamaBarang(txtNamaBarang.getText().trim());
            if (!isNumberDouble(txtHargaBeli.getText(), "Harga Beli")) {
                txtHargaBeli.clear();
            } else {
                barang.setHargaBeli(Double.
                        valueOf(txtHargaBeli.getText().trim()));
            }

            if (!isNumberDouble(txtHargaJual.getText(), "Harga Jual")) {
                txtHargaJual.clear();
            } else {
                barang.setHargaJual(Double.
                        valueOf(txtHargaJual.getText().trim()));
            }

            if (!isNumberInt(txtStok.getText(), "Stok")) {
                txtHargaJual.clear();
            } else {
                barang.setStok(Integer.valueOf(txtStok.getText().trim()));
            }

            if (productPriceListOwnerFormController.getBarangDao().updateData(
                    barang) == 1) {

                productPriceListOwnerFormController.getBarangs().clear();
                productPriceListOwnerFormController.getBarangs().addAll(
                        productPriceListOwnerFormController.
                        getBarangDao().showAllData());
            }

            txtNamaBarang.clear();
            txtHargaBeli.clear();
            txtHargaJual.clear();
            txtStok.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Masih ada yang kosong");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/ProductPriceListOwnerForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Product And Price List Form");
            secondStage.show();

            bpEditProduct.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public static final boolean isNumberInt(String number, String name) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            Utility.showAlert("Not a number", name + " is not a number",
                    Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public static final boolean isNumberDouble(String number, String name) {
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException numberFormatException) {
            Utility.showAlert("Not a number", name + " is not a number",
                    Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    @FXML
    private void cbKodeBarangAct(ActionEvent event) {
        txtNamaBarang.setText(cbKodeBarang.getValue().getNamaBarang());
        txtHargaBeli.setText(String.valueOf(
                cbKodeBarang.getValue().getHargaBeli()));
        txtHargaJual.setText(String.valueOf(
                cbKodeBarang.getValue().getHargaJual()));
        txtStok.setText(String.valueOf(
                cbKodeBarang.getValue().getStok()));
    }

}
