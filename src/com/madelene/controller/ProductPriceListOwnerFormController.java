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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<?> tbleProduct;
    @FXML
    private BorderPane bpPPLO;
    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;
    private Stage addProductStage;
    @FXML
    private TableColumn<?, ?> colKodeBarang;
    @FXML
    private TableColumn<?, ?> colNamaBarang;
    @FXML
    private TableColumn<?, ?> colHargaBeli;
    @FXML
    private TableColumn<?, ?> colHargaJual;
    @FXML
    private TableColumn<?, ?> colStok;

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
        // TODO
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

            //Close login stage
            bpPPLO.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddProductAct(ActionEvent event) {
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
            if (!addProductStage.isShowing()) {
                addProductStage.show();
            } else {
                addProductStage.toFront();
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEditProductAct(ActionEvent event) {
    }

    @FXML
    private void btnDelProductAct(ActionEvent event) {
    }

    @FXML
    private void tbProductMouseClicked(MouseEvent event) {
    }

}
