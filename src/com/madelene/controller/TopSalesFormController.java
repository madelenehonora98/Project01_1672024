/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.BarangDaoImpl;
import com.madelene.dao.RelasiBarangNotaPenjualanDaoImpl;
import com.madelene.entity.Barang;
import com.madelene.entity.NotaPenjualan;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class TopSalesFormController implements Initializable {

    @FXML
    private Button btnBackSalesReport;
    @FXML
    private TableColumn<Barang, String> colNominal;
    @FXML
    private TableColumn<Barang, String> colTotal;
    public ObservableList<NotaPenjualan> notaPenjualansByYears;
    @FXML
    private TableView<Barang> tbleTopSales;
    @FXML
    private DatePicker dpTo;
    @FXML
    private DatePicker dpFrom;
    @FXML
    private Button btnGo;
    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;
    private RelasiBarangNotaPenjualanDaoImpl relasiDao;
    @FXML
    private TableColumn<Barang, String> colIdBarang;
    @FXML
    private TableColumn<Barang, String> colNamaBarang;

    @FXML
    private BorderPane bpTopSalesForm;

    /**
     * Initializes the controller class.
     */
    public BarangDaoImpl getBarangDao() {
        if (barangDao == null) {
            barangDao = new BarangDaoImpl();
        }
        return barangDao;
    }

    public ObservableList<Barang> getBarang(String from, String akhir) {
        if (barangs == null) {
            barangs = FXCollections.observableArrayList();
            barangs.addAll(getBarangDao().showTopData(from, akhir));
        }
        return barangs;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tbleTopSales.setItems(barangs);

        colIdBarang.setCellValueFactory(new PropertyValueFactory<>(
                "KodeBarang"));

        colNamaBarang.setCellValueFactory(new PropertyValueFactory<>(
                "NamaBarang"));

        colNominal.
                setCellValueFactory(new PropertyValueFactory<>("HargaJual"));
        colTotal.
                setCellValueFactory(new PropertyValueFactory<>("Stok"));
    }

    @FXML
    private void btnGoAct(ActionEvent event) {
        if (dpFrom.getValue().isBefore(dpTo.getValue())) {
            if (barangs != null) {
                barangs.clear();
                barangs.addAll(getBarangDao().showTopData(dpFrom.getValue().
                        toString(), dpTo.getValue().toString()));
            } else {
                getBarang(dpFrom.getValue().
                        toString(),
                        dpTo.getValue().toString());
                tbleTopSales.refresh();
                tbleTopSales.setItems(barangs);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Date Range you entered is invalid");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnBackSalesReportAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(
                    "view/SalesReport.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.setTitle("Sales Report Form");
            secondStage.show();

            bpTopSalesForm.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

}
