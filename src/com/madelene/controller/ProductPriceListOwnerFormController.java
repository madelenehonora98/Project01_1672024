/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.BarangDaoImpl;
import com.madelene.entity.Barang;
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
public class ProductPriceListOwnerFormController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnEditProduct;
    @FXML
    private Button btnDelProduct;
    @FXML
    private TableView<Barang> tbleProduct;
    @FXML
    private BorderPane bpPPLO;
    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;
    private Stage addProductStage;
    @FXML
    private TableColumn<Barang, String> colKodeBarang;
    @FXML
    private TableColumn<Barang, String> colNamaBarang;
    @FXML
    private TableColumn<Barang, Double> colHargaBeli;
    @FXML
    private TableColumn<Barang, Double> colHargaJual;
    @FXML
    private TableColumn<Barang, Integer> colStok;

    private Stage editBarangStage;

    public Barang selectedBarang;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        barangDao = new BarangDaoImpl();
        barangs = FXCollections.observableArrayList();
        barangs = barangDao.showAllData();

        tbleProduct.setItems(barangs);

        colKodeBarang.setCellValueFactory(new PropertyValueFactory<>(
                "KodeBarang"));
        colNamaBarang.
                setCellValueFactory(new PropertyValueFactory<>("NamaBarang"));
        colHargaBeli.setCellValueFactory(new PropertyValueFactory<>(
                "HargaBeli"));
        colHargaJual.
                setCellValueFactory(new PropertyValueFactory<>("HargaJual"));
        colStok.
                setCellValueFactory(new PropertyValueFactory<>("Stok"));

    }

    @FXML
    private void btnBackAct(ActionEvent event) {
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

            bpPPLO.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddProductAct(ActionEvent event) {
        if (addProductStage == null) {
            try {
                if (addProductStage == null) {
                    addProductStage = new Stage();
                    addProductStage.setTitle("Add Product Form");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource(
                            "view/AddProductForm.fxml"));
                    BorderPane root = loader.load();
                    Scene scene = new Scene(root);
                    AddProductFormController addProductFormController = loader.
                            getController();
                    addProductFormController.setMainController(this);
                    addProductStage.setScene(scene);
                    addProductStage.initOwner(bpPPLO.getScene().getWindow());
                    addProductStage.initModality(Modality.WINDOW_MODAL);

                }
            } catch (IOException ex) {
                Logger.getLogger(ProductPriceListOwnerFormController.class.
                        getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        if (!addProductStage.isShowing()) {
            addProductStage.show();
        } else {
            addProductStage.toFront();
        }
    }

    @FXML
    private void btnEditProductAct(ActionEvent event) {
        if (editBarangStage == null) {
            try {
                if (editBarangStage == null) {
                    editBarangStage = new Stage();
                    editBarangStage.setTitle("Edit Product Form");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource(
                            "view/EditProductForm.fxml"));
                    BorderPane root = loader.load();
                    Scene scene = new Scene(root);
                    EditProductFormController editProductFormController
                            = loader.
                            getController();
                    editProductFormController.setMainController(this);
                    editBarangStage.setScene(scene);
                    editBarangStage.initOwner(bpPPLO.getScene().getWindow());
                    editBarangStage.initModality(Modality.WINDOW_MODAL);
                }
            } catch (IOException ex) {
                Logger.getLogger(ProductPriceListOwnerFormController.class.
                        getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        if (!editBarangStage.isShowing()) {
            editBarangStage.show();
        } else {
            editBarangStage.toFront();
        }
        selectedBarang = null;
        btnDelProduct.setDisable(true);
    }

    @FXML
    private void btnDelProductAct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this data?");
        alert.showAndWait().ifPresent(jawaban -> {
            if (jawaban == ButtonType.OK) {
                if (getBarangDao().deleteData(selectedBarang) == 1) {
                    getBarangs().clear();
                    getBarangs().addAll(getBarangDao().showAllData());

                    tbleProduct.refresh();
                    btnDelProduct.setDisable(true);
                    btnEditProduct.setDisable(true);
                }
            }

        });

        selectedBarang = null;
        btnDelProduct.setDisable(true);
    }

    @FXML
    private void tbProductMouseClicked(MouseEvent event) {
        selectedBarang = tbleProduct.getSelectionModel().getSelectedItem();
        if (selectedBarang != null) {
            btnDelProduct.setDisable(false);

        }
    }

}
