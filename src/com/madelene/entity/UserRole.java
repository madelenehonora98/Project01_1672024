/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Madelene
 */
public class UserRole {

    String idUserRole;
    String Jabatan;

    public UserRole() {
    }

    public UserRole(String idUserRole, String Jabatan) {
        this.idUserRole = idUserRole;
        this.Jabatan = Jabatan;
    }

    public String getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(String idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getJabatan() {
        return Jabatan;
    }

    public void setJabatan(String Jabatan) {
        this.Jabatan = Jabatan;
    }

//    private String jabatanProperty;
//
//    /**
//     * Get the value of jabatanProperty
//     *
//     * @return the value of jabatanProperty
//     */
//    public String getJabatanProperty() {
//        return jabatanProperty;
//    }
//
//    /**
//     * Set the value of jabatanProperty
//     *
//     * @param jabatanProperty new value of jabatanProperty
//     */
//    public void setJabatanProperty(String jabatanProperty) {
//        this.jabatanProperty = jabatanProperty;
//    }
    private final StringProperty jabatanProperty = new SimpleStringProperty();

    public String getJabatanProperty() {
        return jabatanProperty.get();
    }

    public void setJabatanProperty(String value) {
        jabatanProperty.set(value);
    }

    public StringProperty jabatanPropertyProperty() {
        return jabatanProperty;
    }

}
