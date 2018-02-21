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
import com.madelene.entity.RelasiBarangNotaPenjualan;
import com.madelene.utility.Utility;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class TransactionFormController implements Initializable {

    @FXML
    private Button btnBackCashier;
    @FXML
    private BorderPane bpTransaction;
    @FXML
    private Button btnSubmit;
    @FXML
    private ComboBox<Barang> cbKodeBarang;
    @FXML
    private Button btnAddCart;
    @FXML
    private Button btnEditCart;
    @FXML
    private Button btnDelCart;
    @FXML
    private TableView<RelasiBarangNotaPenjualan> tbCart;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField txtJumlahBarang;
    @FXML
    private TextField txtKodeTransaksi;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, Integer> colKodeTransaksi;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colKodeBarang;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colJumlahBarang;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colHargaSatuan;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colTotal;

    private ObservableList<RelasiBarangNotaPenjualan> notaPenjualans;

    private int tmpNominal = 0;
    private String tmpKodeTransaksi;
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
        //notaPenjualans = relasiDao.showAllData();
        if (getNotaPenjualans().isEmpty()) {
            tmpKodeTransaksi = "1";
        } else {
            tmpKodeTransaksi = String.valueOf(getNotaPenjualans().size() + 1);
        }

        txtKodeTransaksi.setText(tmpKodeTransaksi);
        lblTotal.setText(String.valueOf(tmpNominal));
        cbKodeBarang.setItems(getBarang());
        tbCart.setItems(getNotaPenjualans());
        colKodeTransaksi.setCellValueFactory(new PropertyValueFactory<>(
                txtKodeTransaksi.getText()));

        colKodeBarang.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(
                        param.getValue().getKodeBarang().getKodeBarang() + " - "
                        + param.getValue().getKodeBarang().getNamaBarang()));

        colJumlahBarang.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(String.valueOf(param.getValue().
                        getJumlahBarangTerjual())));

        colHargaSatuan.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty("Rp." + String.valueOf(param.
                        getValue().getKodeBarang().getHargaJual())));

        colTotal.setCellValueFactory((
                TableColumn.CellDataFeatures<RelasiBarangNotaPenjualan, String> param)
                -> new SimpleStringProperty(
                        "Rp." + String.valueOf(param.getValue().getKodeBarang().
                                getHargaJual() * param.getValue().
                                getJumlahBarangTerjual())));
    }
    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;

    public ObservableList<Barang> getBarang() {
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

    @FXML
    private void btnBackCashierAct(ActionEvent event) {
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

            bpTransaction.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginFormController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSubmitAct(ActionEvent event) {

    }

    @FXML
    private void cbKodeBarangAct(ActionEvent event) {

    }

    @FXML
    private void btnAddCartAct(ActionEvent event) {

        if (isNumber((txtJumlahBarang.getText()))) {

            if (Integer.parseInt(txtJumlahBarang.getText()) > getBarangDao().
                    getData(
                            cbKodeBarang.getValue()).getStok()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Product stock is not enough");
                alert.showAndWait();
            } else {
                RelasiBarangNotaPenjualan relasiBarangNotaPenjualan
                        = new RelasiBarangNotaPenjualan(Integer.parseInt(
                                txtJumlahBarang.getText()), 5.00, cbKodeBarang.
                                getValue());

                tmpNominal += Integer.parseInt(txtJumlahBarang.getText())
                        * cbKodeBarang.getValue().getHargaJual();
                getNotaPenjualans().add(relasiBarangNotaPenjualan);
                lblTotal.setText(String.valueOf(tmpNominal));
            }

        }

    }

    @FXML
    private void btnEditCartAct(ActionEvent event) {
    }

    @FXML
    private void btnDelCartAct(ActionEvent event) {
    }

    public static final boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            Utility.showAlert("Not a number", "Your input is not a number",
                    Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

}
