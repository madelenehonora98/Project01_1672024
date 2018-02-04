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
public class NotaPenjualan {

    String KodePenjualan;
    double Nominal;
    String TanggalPenjualan;
    User IdPengguna;

    public NotaPenjualan() {
    }

    public NotaPenjualan(String KodePenjualan, double Nominal,
            String TanggalPenjualan, User IdPengguna) {
        this.KodePenjualan = KodePenjualan;
        this.Nominal = Nominal;
        this.TanggalPenjualan = TanggalPenjualan;
        this.IdPengguna = IdPengguna;
    }

    public String getKodePenjualan() {
        return KodePenjualan;
    }

    public void setKodePenjualan(String KodePenjualan) {
        this.KodePenjualan = KodePenjualan;
    }

    public double getNominal() {
        return Nominal;
    }

    public void setNominal(double Nominal) {
        this.Nominal = Nominal;
    }

    public String getTanggalPenjualan() {
        return TanggalPenjualan;
    }

    public void setTanggalPenjualan(String TanggalPenjualan) {
        this.TanggalPenjualan = TanggalPenjualan;
    }

    public User getIdPengguna() {
        return IdPengguna;
    }

    public void setIdPengguna(User IdPengguna) {
        this.IdPengguna = IdPengguna;
    }

}
