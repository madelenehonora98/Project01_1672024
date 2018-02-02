/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.entity;

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

}
