/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.entity;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Madelene
 */
public class User {

    private final StringProperty idPengguna = new SimpleStringProperty();

    public String getIdPengguna() {
        return idPengguna.get();
    }

    public void setIdPengguna(String value) {
        idPengguna.set(value);
    }

    public StringProperty idPenggunaProperty() {
        return idPengguna;
    }
    private final StringProperty NamaBelakang = new SimpleStringProperty();

    public String getNamaBelakang() {
        return NamaBelakang.get();
    }

    public void setNamaBelakang(String value) {
        NamaBelakang.set(value);
    }

    public StringProperty NamaBelakangProperty() {
        return NamaBelakang;
    }
    private final StringProperty Alamat = new SimpleStringProperty();

    public String getAlamat() {
        return Alamat.get();
    }

    public void setAlamat(String value) {
        Alamat.set(value);
    }

    public StringProperty AlamatProperty() {
        return Alamat;
    }
    private final StringProperty NoTelepon = new SimpleStringProperty();

    public String getNoTelepon() {
        return NoTelepon.get();
    }

    public void setNoTelepon(String value) {
        NoTelepon.set(value);
    }

    public StringProperty NoTeleponProperty() {
        return NoTelepon;
    }
    private final StringProperty Password = new SimpleStringProperty();

    public String getPassword() {
        return Password.get();
    }

    public void setPassword(String value) {
        Password.set(value);
    }

    public StringProperty PasswordProperty() {
        return Password;
    }
    private final StringProperty JenisKelamin = new SimpleStringProperty();

    public String getJenisKelamin() {
        return JenisKelamin.get();
    }

    public void setJenisKelamin(String value) {
        JenisKelamin.set(value);
    }

    public StringProperty JenisKelaminProperty() {
        return JenisKelamin;
    }
    private final ObjectProperty<UserRole> idUserRole
            = new SimpleObjectProperty<>();

    public UserRole getIdUserRole() {
        return idUserRole.get();
    }

    public void setIdUserRole(UserRole value) {
        idUserRole.set(value);
    }

    public ObjectProperty idUserRoleProperty() {
        return idUserRole;
    }

    private final StringProperty NamaDepan = new SimpleStringProperty();

    public String getNamaDepan() {
        return NamaDepan.get();
    }

    public void setNamaDepan(String value) {
        NamaDepan.set(value);
    }

    public StringProperty NamaDepanProperty() {
        return NamaDepan;
    }

    public String toString() {
        return getIdPengguna();
    }
}
