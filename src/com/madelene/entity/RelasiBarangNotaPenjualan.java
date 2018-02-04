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
public class RelasiBarangNotaPenjualan {

    Barang KodeBarang;
    int JumlahBarangTerjual;
    NotaPenjualan KodePenjualan;

    public RelasiBarangNotaPenjualan() {
    }

    public RelasiBarangNotaPenjualan(Barang KodeBarang, int JumlahBarangTerjual,
            NotaPenjualan KodePenjualan) {
        this.KodeBarang = KodeBarang;
        this.JumlahBarangTerjual = JumlahBarangTerjual;
        this.KodePenjualan = KodePenjualan;
    }

    public Barang getKodeBarang() {
        return KodeBarang;
    }

    public void setKodeBarang(Barang KodeBarang) {
        this.KodeBarang = KodeBarang;
    }

    public int getJumlahBarangTerjual() {
        return JumlahBarangTerjual;
    }

    public void setJumlahBarangTerjual(int JumlahBarangTerjual) {
        this.JumlahBarangTerjual = JumlahBarangTerjual;
    }

    public NotaPenjualan getKodePenjualan() {
        return KodePenjualan;
    }

    public void setKodePenjualan(NotaPenjualan KodePenjualan) {
        this.KodePenjualan = KodePenjualan;
    }

}
