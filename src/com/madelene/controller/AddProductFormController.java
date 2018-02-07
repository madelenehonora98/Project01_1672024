/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.entity.Barang;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

            //Close login stage
            bpAddProduct.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSubmitAddProductAct(ActionEvent event) {

        if (!Utility.isEmptyField(txtKodeBarang, txtNamaBarang, txtHargaBeli,
                txtHargaJual, txtStok)) {
            Barang barang = new Barang();
            barang.setKodeBarang(txtKodeBarang.getText().trim());
            barang.setNamaBarang(txtNamaBarang.getText().trim());
            barang.setHargaBeli(Double.valueOf(txtHargaBeli.getText().trim()));
            barang.setHargaJual(Double.valueOf(txtHargaJual.getText().trim()));
            barang.setStok(Integer.valueOf(txtStok.getText().trim()));

            if (productPriceListOwnerFormController.getBarangDao().addData(
                    barang) == 1) {

                productPriceListOwnerFormController.getBarangs().clear();
                productPriceListOwnerFormController.getBarangs().addAll(
                        productPriceListOwnerFormController.
                        getBarangDao().showAllData());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Masih ada yang kosong");
            alert.showAndWait();
        }
    }

}
