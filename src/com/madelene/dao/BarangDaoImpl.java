/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import com.madelene.entity.Barang;
import com.madelene.utility.DaoService;
import com.madelene.utility.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Madelene
 */
public class BarangDaoImpl implements DaoService<Barang> {

    @Override
    public int addData(Barang object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);

                String query
                        = "INSERT INTO barang(KodeBarang, NamaBarang, HargaBeli, HargaJual,Stok) VALUES (?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getKodeBarang());
                ps.setString(2, object.getNamaBarang());
                ps.setDouble(3, object.getHargaBeli());
                ps.setDouble(4, object.getHargaJual());
                ps.setInt(5, object.getStok());

                if (ps.executeUpdate() != 0) {

                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public int updateData(Barang object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE barang SET  NamaBarang=?, HargaBeli=?, HargaJual=?,Stok = ? WHERE KodeBarang=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getKodeBarang());
                ps.setString(2, object.getNamaBarang());
                ps.setDouble(3, object.getHargaBeli());
                ps.setDouble(4, object.getHargaJual());
                ps.setInt(5, object.getStok());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Utility.showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
        return result;
    }

    @Override
    public int deleteData(Barang object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM barang WHERE KodeBarang=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getKodeBarang());

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public List<Barang> showAllData() {
        ObservableList<Barang> barangs = FXCollections.observableArrayList();
        try {
            try (Connection connection = Utility.creatConnection()) {
                String query = "SELECT * FROM Barang ORDER BY KodeBarang";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Barang barang = new Barang();
                    barang.setKodeBarang(rs.getString("KodeBarang"));
                    barang.setNamaBarang(rs.getString("NamaBarang"));
                    barang.setHargaBeli(rs.getDouble("HargaBeli"));
                    barang.setHargaJual(rs.getDouble("HargaJual"));
                    barang.setStok(rs.getInt("Stok"));

                    barangs.add(barang);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return barangs;
    }

    @Override
    public Barang getData(Barang id) {
        Barang b = null;
//        try {
//            try (Connection connection = Utility.creatConnection()) {
//                connection.setAutoCommit(false);
//                String query
//                        = "SELECT NamaBarang, HargaBeli, HargaJual, Stok FROM Barang WHERE KodeBarang=?";
//                PreparedStatement ps = connection.prepareStatement(query);
//                ps.setString(1, id.getIdPengguna());
//                ps.setString(2, id.getPassword());
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    User user = new User();
//                    user.setIdPengguna(rs.getString("u.IdPengguna"));
//
//                    user.setPassword(rs.getString("u.Password"));
//
//                    return user;
//                }
////masukin semua yang di select
//
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex);
//        }
        return b;
    }
}
