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
public class User {

    String IdPengguna;
    String NamaDepan;
    String NamaBelakang;
    String Alamat;
    String NoTelepon;
    UserRole idUserRole;
    String Password;
    String JenisKelamin;

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String JenisKelamin) {
        this.JenisKelamin = JenisKelamin;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getIdPengguna() {
        return IdPengguna;
    }

    public void setIdPengguna(String IdPengguna) {
        this.IdPengguna = IdPengguna;
    }

    public String getNamaDepan() {
        return NamaDepan;
    }

    public void setNamaDepan(String NamaDepan) {
        this.NamaDepan = NamaDepan;
    }

    public String getNamaBelakang() {
        return NamaBelakang;
    }

    public void setNamaBelakang(String NamaBelakang) {
        this.NamaBelakang = NamaBelakang;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getNoTelepon() {
        return NoTelepon;
    }

    public void setNoTelepon(String NoTelepon) {
        this.NoTelepon = NoTelepon;
    }

    public UserRole getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(UserRole idUserRole) {
        this.idUserRole = idUserRole;
    }

    public User(String IdPengguna, String NamaDepan, String NamaBelakang,
            String Alamat, String NoTelepon, UserRole idUserRole,
            String Password, String JenisKelamin) {
        this.IdPengguna = IdPengguna;
        this.NamaDepan = NamaDepan;
        this.NamaBelakang = NamaBelakang;
        this.Alamat = Alamat;
        this.NoTelepon = NoTelepon;
        this.idUserRole = idUserRole;
        this.Password = Password;
        this.JenisKelamin = JenisKelamin;
    }

    public User() {
    }

}
