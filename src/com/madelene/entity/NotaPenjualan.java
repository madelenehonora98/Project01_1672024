/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Madelene
 */
public class NotaPenjualan {

    private final StringProperty KodePenjualan = new SimpleStringProperty();

    public String getKodePenjualan() {
        return KodePenjualan.get();
    }

    public void setKodePenjualan(String value) {
        KodePenjualan.set(value);
    }

    public StringProperty KodePenjualanProperty() {
        return KodePenjualan;
    }
    private final StringProperty TanggalPenjualan = new SimpleStringProperty();

    public String getTanggalPenjualan() {
        return TanggalPenjualan.get();
    }

    public void setTanggalPenjualan(String value) {
        TanggalPenjualan.set(value);
    }

    public StringProperty TanggalPenjualanProperty() {
        return TanggalPenjualan;
    }
    private final ObjectProperty<User> IdPengguna = new SimpleObjectProperty<>();

    public User getIdPengguna() {
        return IdPengguna.get();
    }

    public void setIdPengguna(User value) {
        IdPengguna.set(value);
    }

    public ObjectProperty IdPenggunaProperty() {
        return IdPengguna;
    }

    private final DoubleProperty Nominal = new SimpleDoubleProperty();

    public double getNominal() {
        return Nominal.get();
    }

    public void setNominal(double value) {
        Nominal.set(value);
    }

    public DoubleProperty NominalProperty() {
        return Nominal;
    }
}
