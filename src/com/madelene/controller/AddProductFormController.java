/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.entity.Barang;
import com.madelene.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class AddProductFormController implements Initializable {

    @FXML
    private Button btnBackOwner;
    @FXML
    private BorderPane bpAddProduct;
    @FXML
    private Button btnSubmitAddProduct;
    @FXML
    private TextField txtKodeBarang;
    @FXML
    private TextField txtNamaBarang;
    @FXML
    private TextField txtHargaBeli;
    @FXML
    private TextField txtHargaJual;
    @FXML
    private TextField txtStok;
    private ProductPriceListOwnerFormController productPriceListOwnerFormController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMainController(
            ProductPriceListOwnerFormController productPriceListOwnerFormController) {
        this.productPriceListOwnerFormController
                = productPriceListOwnerFormController;

    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
        bpAddProduct.getScene().getWindow().hide();

    }

    @FXML
    private void btnSubmitAddProductAct(ActionEvent event) {

        if (!Utility.isEmptyField(txtKodeBarang, txtNamaBarang, txtHargaBeli,
                txtHargaJual, txtStok)) {
            Barang barang = new Barang();
            barang.setKodeBarang(txtKodeBarang.getText().trim());
            barang.setNamaBarang(txtNamaBarang.getText().trim());

            if (!isNumber(txtHargaBeli.getText(), "Harga Beli")) {
                txtHargaBeli.clear();
            } else {
                barang.setHargaBeli(Double.
                        valueOf(txtHargaBeli.getText().trim()));
            }

            if (!isNumber(txtHargaJual.getText(), "Harga Jual")) {
                txtHargaJual.clear();
            } else {
                barang.setHargaJual(Double.
                        valueOf(txtHargaJual.getText().trim()));
            }

            if (!isNumber(txtStok.getText(), "Stok")) {
                txtHargaJual.clear();
            } else {
                barang.setStok(Integer.valueOf(txtStok.getText().trim()));
            }

            if (productPriceListOwnerFormController.getBarangDao().addData(
                    barang) == 1) {

                productPriceListOwnerFormController.getBarangs().clear();
                productPriceListOwnerFormController.getBarangs().addAll(
                        productPriceListOwnerFormController.
                        getBarangDao().showAllData());

                txtKodeBarang.clear();
                txtNamaBarang.clear();
                txtHargaBeli.clear();
                txtHargaJual.clear();
                txtStok.clear();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Masih ada yang kosong");
            alert.showAndWait();
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
