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
public class Barang {

    int KodeBarang;
    String NamaBarang;
    double HargaBeli;
    double HargaJual;
    int Stok;

    public Barang() {
    }

    public Barang(int KodeBarang, String NamaBarang, double HargaBeli,
            double HargaJual, int Stok) {
        this.KodeBarang = KodeBarang;
        this.NamaBarang = NamaBarang;
        this.HargaBeli = HargaBeli;
        this.HargaJual = HargaJual;
        this.Stok = Stok;
    }

    public int getKodeBarang() {
        return KodeBarang;
    }

    public void setKodeBarang(int KodeBarang) {
        this.KodeBarang = KodeBarang;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String NamaBarang) {
        this.NamaBarang = NamaBarang;
    }

    public double getHargaBeli() {
        return HargaBeli;
    }

    public void setHargaBeli(double HargaBeli) {
        this.HargaBeli = HargaBeli;
    }

    public double getHargaJual() {
        return HargaJual;
    }

    public void setHargaJual(double HargaJual) {
        this.HargaJual = HargaJual;
    }

    public int getStok() {
        return Stok;
    }

    public void setStok(int Stok) {
        this.Stok = Stok;
    }

}
