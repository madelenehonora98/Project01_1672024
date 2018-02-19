/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Madelene
 */
public class RelasiBarangNotaPenjualan {

    private final ObjectProperty<Barang> KodeBarang
            = new SimpleObjectProperty<>();

    public Barang getKodeBarang() {
        return KodeBarang.get();
    }

    public void setKodeBarang(Barang value) {
        KodeBarang.set(value);
    }

    public ObjectProperty KodeBarangProperty() {
        return KodeBarang;
    }
    private final ObjectProperty<NotaPenjualan> KodePenjualan
            = new SimpleObjectProperty<>();

    public NotaPenjualan getKodePenjualan() {
        return KodePenjualan.get();
    }

    public void setKodePenjualan(NotaPenjualan value) {
        KodePenjualan.set(value);
    }

    public ObjectProperty KodePenjualanProperty() {
        return KodePenjualan;
    }

    private final IntegerProperty JumlahBarangTerjual
            = new SimpleIntegerProperty();

    public int getJumlahBarangTerjual() {
        return JumlahBarangTerjual.get();
    }

    public void setJumlahBarangTerjual(int value) {
        JumlahBarangTerjual.set(value);
    }

    public IntegerProperty JumlahBarangTerjualProperty() {
        return JumlahBarangTerjual;
    }
    private final DoubleProperty HargaJualSaatItu = new SimpleDoubleProperty();

    public double getHargaJualSaatItu() {
        return HargaJualSaatItu.get();
    }

    public void setHargaJualSaatItu(double value) {
        HargaJualSaatItu.set(value);
    }

    public DoubleProperty HargaJualSaatItuProperty() {
        return HargaJualSaatItu;
    }

    public RelasiBarangNotaPenjualan(Integer jumlah, Double HargaSaatItu,
            Barang kdBarang) {

        this.setJumlahBarangTerjual(jumlah);
        this.setHargaJualSaatItu(HargaSaatItu);
        this.setKodeBarang(kdBarang);

    }

    public RelasiBarangNotaPenjualan() {
    }

}
