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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class ProductPriceListFormController implements Initializable {

    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<Barang> tbleProduct;
    @FXML
    private TableColumn<Barang, String> colKodeBarang;
    @FXML
    private TableColumn<Barang, String> colNamaBarang;

    @FXML
    private TableColumn<Barang, Double> colHargaJual;
    @FXML
    private TableColumn<Barang, Integer> colStok;
    @FXML
    private BorderPane bpPPL;

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
                    "view/CashierForm.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Cashier Form");
            secondStage.show();

            bpPPL.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tbProductMouseClicked(MouseEvent event) {
    }

}
