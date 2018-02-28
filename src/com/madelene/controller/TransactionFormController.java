/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import com.madelene.MainApp;
import com.madelene.dao.BarangDaoImpl;
import com.madelene.dao.NotaPenjualanDaoImpl;
import com.madelene.dao.RelasiBarangNotaPenjualanDaoImpl;
import com.madelene.entity.Barang;
import com.madelene.entity.NotaPenjualan;
import com.madelene.entity.RelasiBarangNotaPenjualan;
import com.madelene.entity.User;
import com.madelene.utility.Utility;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
    private TableColumn<RelasiBarangNotaPenjualan, String> colKodeBarang;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colJumlahBarang;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colHargaSatuan;
    @FXML
    private TableColumn<RelasiBarangNotaPenjualan, String> colTotal;

    private ObservableList<RelasiBarangNotaPenjualan> relasiNotaPenjualans;
    private ObservableList<NotaPenjualan> notaPenjualans;

    private double tmpNominal = 0;
    private String tmpKodeTransaksi;
    private RelasiBarangNotaPenjualanDaoImpl relasiDao;
    private NotaPenjualanDaoImpl notaPenjualanDao;
    private ObservableList<Integer> tmpStock;
    public RelasiBarangNotaPenjualan relasiBarangNotaPenjualan;
    public RelasiBarangNotaPenjualan selectedProduct;

    private int noUrut = 1;
    @FXML
    private TextField txtIdCashier;

    private BarangDaoImpl barangDao;
    private ObservableList<Barang> barangs;

    /**
     * Initializes the controller class.
     */
    // <editor-fold defaultstate="collapsed" desc="initiate getter">
    public ObservableList<RelasiBarangNotaPenjualan> getRelasiNotaPenjualans() {
        if (relasiNotaPenjualans == null) {
            relasiNotaPenjualans = FXCollections.observableArrayList();
//            relasiNotaPenjualans.addAll(getRelasiDao().showAllData());
        }
        return relasiNotaPenjualans;
    }

    public ObservableList<NotaPenjualan> getNotaPenjualans() {
        if (notaPenjualans == null) {
            notaPenjualans = FXCollections.observableArrayList();
            notaPenjualans.addAll(getNotaPenjualanDao().showAllData());
        }
        return notaPenjualans;
    }

    public RelasiBarangNotaPenjualanDaoImpl getRelasiDao() {
        if (relasiDao == null) {
            relasiDao = new RelasiBarangNotaPenjualanDaoImpl();
        }
        return relasiDao;
    }

    public NotaPenjualanDaoImpl getNotaPenjualanDao() {
        if (notaPenjualanDao == null) {
            notaPenjualanDao = new NotaPenjualanDaoImpl();
        }
        return notaPenjualanDao;
    }

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

    // </editor-fold>
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtKodeTransaksi.setText(String.
                valueOf(getNotaPenjualans().size() + 1));
        txtIdCashier.setText(LoginFormController.tmpIdUser);
        lblTotal.setText(String.valueOf(tmpNominal));
        cbKodeBarang.setItems(getBarang());
        tbCart.setItems(getRelasiNotaPenjualans());

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
        RelasiBarangNotaPenjualan relasi = new RelasiBarangNotaPenjualan();
        NotaPenjualan notaPenjualan = new NotaPenjualan();
        User user = new User();
        Barang barang = new Barang();

        Timestamp t = new Timestamp(System.currentTimeMillis());
        user.setIdPengguna(LoginFormController.tmpIdUser);
        notaPenjualan.setKodePenjualan(getNotaPenjualans().size() + 1);
        notaPenjualan.setIdPengguna(user);
        notaPenjualan.setNominal(tmpNominal);
        notaPenjualan.setTanggalPenjualan(String.valueOf(t));

        getNotaPenjualanDao().addData(notaPenjualan);

        for (RelasiBarangNotaPenjualan relasiNotaPenjualan
                : relasiNotaPenjualans) {
            relasi.
                    setHargaJualSaatItu(relasiNotaPenjualan.
                            getHargaJualSaatItu());
            relasi.setJumlahBarangTerjual(relasiNotaPenjualan.
                    getJumlahBarangTerjual());
            relasi.setKodeBarang(relasiNotaPenjualan.getKodeBarang());

            relasi.setKodePenjualan(notaPenjualan);

            barang.setKodeBarang(relasi.getKodeBarang().getKodeBarang());
            barang.setNamaBarang(relasi.getKodeBarang().getNamaBarang());
            barang.setHargaBeli(relasi.getKodeBarang().getHargaBeli());
            barang.setHargaJual(relasi.getKodeBarang().getHargaJual());
            barang.setStok(relasi.getKodeBarang().getStok() - relasi.
                    getJumlahBarangTerjual());

            getBarangDao().updateData(barang);
            getRelasiDao().addData(relasi);

        }

        try {
            Map param = new HashMap();

            JasperPrint jp = JasperFillManager.fillReport(
                    "iReport\\TransactionReportProject01.jasper", param,
                    Utility.creatConnection());
            JasperViewer view = new JasperViewer(jp, false);
            view.setTitle("Transaction Report");
            view.setVisible(true);
            txtJumlahBarang.clear();
            relasiNotaPenjualans.clear();
            txtKodeTransaksi.setText(String.
                    valueOf(getNotaPenjualanDao().showAllData().size() + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void cbKodeBarangAct(ActionEvent event) {
        int i = 0;
        boolean isFound = false;
        for (RelasiBarangNotaPenjualan relasi : relasiNotaPenjualans) {
            if (cbKodeBarang.getValue().getKodeBarang().equals(relasi.
                    getKodeBarang().getKodeBarang())) {
                txtJumlahBarang.setText(String.valueOf(relasiNotaPenjualans.get(
                        i).getJumlahBarangTerjual()));
                isFound = true;
                i++;
            }
        }
        if (!isFound) {
            txtJumlahBarang.setText("0");
        }

    }

    @FXML
    private void btnAddCartAct(ActionEvent event
    ) {

        if (isNumber((txtJumlahBarang.getText())) && cbKodeBarang.getValue()
                != null) {

            if (Integer.parseInt(txtJumlahBarang.getText())
                    > getBarangDao().
                    getData(
                            cbKodeBarang.getValue()).getStok()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Product stock is not enough");
                alert.showAndWait();
            } else {

                relasiBarangNotaPenjualan = new RelasiBarangNotaPenjualan(
                        Integer.parseInt(
                                txtJumlahBarang.getText()), cbKodeBarang.
                        getValue().getHargaJual(),
                        cbKodeBarang.
                        getValue());

                tmpNominal += Double.parseDouble(txtJumlahBarang.getText())
                        * cbKodeBarang.getValue().getHargaJual();
                boolean isFind = false;
                for (RelasiBarangNotaPenjualan rbnp : relasiNotaPenjualans) {
                    if (rbnp.getKodeBarang().getKodeBarang().
                            equalsIgnoreCase(
                                    relasiBarangNotaPenjualan.
                                    getKodeBarang().
                                    getKodeBarang())) {
                        if (rbnp.
                                getJumlahBarangTerjual() + Integer.valueOf(
                                        txtJumlahBarang.getText())
                                > getBarangDao().
                                getData(cbKodeBarang.getValue()).getStok()) {

                            Alert alert = new Alert(
                                    Alert.AlertType.INFORMATION);
                            alert.setContentText(
                                    "Product stock is not enough");
                            alert.showAndWait();

                        } else {
                            rbnp.setJumlahBarangTerjual(rbnp.
                                    getJumlahBarangTerjual() + Integer.
                                    valueOf(
                                            txtJumlahBarang.getText()));
                        }
                        isFind = true;
                        tbCart.refresh();
                    }
                }
                if (!isFind) {
                    getRelasiNotaPenjualans().add(relasiBarangNotaPenjualan);
                }
                lblTotal.setText(String.valueOf(tmpNominal));
                noUrut++;
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missiong product code");
            alert.setContentText("You haven't pick your product code");
            alert.show();

        }

    }

    @FXML
    private void btnEditCartAct(ActionEvent event
    ) {

        selectedProduct.setJumlahBarangTerjual(Integer.parseInt(txtJumlahBarang.
                getText()));
        tmpNominal = 0;
        for (RelasiBarangNotaPenjualan relasiNotaPenjualan
                : relasiNotaPenjualans) {
            System.out.println(relasiNotaPenjualan.getHargaJualSaatItu());
            tmpNominal += relasiNotaPenjualan.getJumlahBarangTerjual()
                    * relasiNotaPenjualan.getHargaJualSaatItu();
        }
        tbCart.refresh();
        lblTotal.setText(String.valueOf(tmpNominal));
        selectedProduct = null;
        btnAddCart.setDisable(false);
        btnEditCart.setDisable(true);
        btnDelCart.setDisable(true);
        cbKodeBarang.setDisable(false);
    }

    @FXML
    private void btnDelCartAct(ActionEvent event
    ) {
        tmpNominal -= selectedProduct.getJumlahBarangTerjual()
                * cbKodeBarang.getValue().getHargaJual();
        relasiNotaPenjualans.remove(selectedProduct);
        tbCart.refresh();
        selectedProduct = null;
        btnAddCart.setDisable(false);
        btnEditCart.setDisable(true);
        btnDelCart.setDisable(true);
        lblTotal.setText(String.valueOf(tmpNominal));
        cbKodeBarang.setDisable(false);
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

    @FXML
    private void tbCartClickedAction(MouseEvent event) {
        selectedProduct = tbCart.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            cbKodeBarang.setDisable(true);
            cbKodeBarang.setValue(selectedProduct.getKodeBarang());
            btnDelCart.setDisable(false);
            btnEditCart.setDisable(false);
            btnAddCart.setDisable(true);
        }
    }

}
