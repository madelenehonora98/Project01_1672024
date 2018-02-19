/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.RelasiBarangNotaPenjualanDaoImpl;
import com.madelene.entity.RelasiBarangNotaPenjualan;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class SalesReportController implements Initializable {

    @FXML
    private Button btnBackOwner;
    @FXML
    private ComboBox<?> cmboSortBy;
    @FXML
    private TableView<RelasiBarangNotaPenjualan> tblePenjualan;
    @FXML
    private BorderPane bpSalesReportForm;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colIdPenjual;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colKodePenjualan;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colTanggal;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colKodeBarang;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, Integer> colJumlahBarang;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colTotal;

    private ObservableList<RelasiBarangNotaPenjualan> notaPenjualans;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, Double> colHargaSaatItu;
    private RelasiBarangNotaPenjualanDaoImpl relasiDao;

    /**
     * Initializes the controller class.
     */
    public ObservableList<RelasiBarangNotaPenjualan> getNotaPenjualans() {
        if (notaPenjualans == null) {
            notaPenjualans = FXCollections.observableArrayList();
        }
        return notaPenjualans;
    }

    public RelasiBarangNotaPenjualanDaoImpl getRelasiDao() {
        if (relasiDao == null) {
            relasiDao = new RelasiBarangNotaPenjualanDaoImpl();
        }
        return relasiDao;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        relasiDao = new RelasiBarangNotaPenjualanDaoImpl();
        notaPenjualans = FXCollections.observableArrayList();
        notaPenjualans = relasiDao.showAllData();
        tblePenjualan.setItems(getNotaPenjualans());

        colKodeBarang.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(
                        param.getValue().getKodeBarang().getKodeBarang() + " - "
                        + param.getValue().getKodeBarang().getNamaBarang()));

        colJumlahBarang.setCellValueFactory(new PropertyValueFactory<>(
                "JumlahBarang"));
        
        colTotal.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty("Rp." + String.valueOf(param.
                        getValue().getHargaJualSaatItu() * param.getValue().
                        getJumlahBarangTerjual())));
        colKodePenjualan.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getKodePenjualan().getKodePenjualan())));
        colIdPenjual.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getKodePenjualan().getIdPengguna().getIdPengguna())));

        colTanggal.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getKodePenjualan().getTanggalPenjualan())));

        colHargaSaatItu.setCellValueFactory(new PropertyValueFactory<>(
                "HargaSaatItu"));
    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
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
            bpSalesReportForm.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

}
