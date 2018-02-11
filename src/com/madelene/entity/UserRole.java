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

    private final StringProperty idUserRole = new SimpleStringProperty();

    public String getIdUserRole() {
        return idUserRole.get();
    }

    public void setIdUserRole(String value) {
        idUserRole.set(value);
    }

    public StringProperty idUserRoleProperty() {
        return idUserRole;
    }

    private final StringProperty Jabatan = new SimpleStringProperty();

    public String getJabatan() {
        return Jabatan.get();
    }

    public void setJabatan(String value) {
        Jabatan.set(value);
    }

    public StringProperty JabatanProperty() {
        return Jabatan;
    }
}
