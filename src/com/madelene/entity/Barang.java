package com.madelene.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Madelene
 */
public class Barang {

    private final StringProperty KodeBarang = new SimpleStringProperty();

    public Barang() {
    }

    public Barang(String kdBarang, String nmBarang) {
        this.setKodeBarang(kdBarang);
        this.setNamaBarang(nmBarang);
    }

    public String getKodeBarang() {
        return KodeBarang.get();
    }

    public void setKodeBarang(String value) {
        KodeBarang.set(value);
    }

    public StringProperty KodeBarangProperty() {
        return KodeBarang;
    }
    private final DoubleProperty HargaBeli = new SimpleDoubleProperty();

    public double getHargaBeli() {
        return HargaBeli.get();
    }

    public void setHargaBeli(double value) {
        HargaBeli.set(value);
    }

    public DoubleProperty HargaBeliProperty() {
        return HargaBeli;
    }
    private final DoubleProperty HargaJual = new SimpleDoubleProperty();

    public double getHargaJual() {
        return HargaJual.get();
    }

    public void setHargaJual(double value) {
        HargaJual.set(value);
    }

    public DoubleProperty HargaJualProperty() {
        return HargaJual;
    }
    private final IntegerProperty Stok = new SimpleIntegerProperty();

    public int getStok() {
        return Stok.get();
    }

    public void setStok(int value) {
        Stok.set(value);
    }

    public IntegerProperty StokProperty() {
        return Stok;
    }

    private final StringProperty NamaBarang = new SimpleStringProperty();

    public String getNamaBarang() {
        return NamaBarang.get();
    }

    public void setNamaBarang(String value) {
        NamaBarang.set(value);
    }

    public StringProperty NamaBarangProperty() {
        return NamaBarang;
    }

    public String toString() {
        return getKodeBarang() + " - " + getNamaBarang();
    }
}
