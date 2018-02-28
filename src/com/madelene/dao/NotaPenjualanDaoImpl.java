/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import com.madelene.entity.NotaPenjualan;
import com.madelene.entity.User;
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
public class NotaPenjualanDaoImpl implements DaoService<NotaPenjualan> {

    @Override
    public int addData(NotaPenjualan object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO NotaPenjualan( Nominal, TanggalPenjualan, User_IdPengguna) VALUES (?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);

                ps.setDouble(1, object.getNominal());
                ps.setString(2, object.getTanggalPenjualan());

                //Foreign Key IdPengguna dari User
                ps.setString(3, object.getIdPengguna().getIdPengguna());

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
    public int updateData(NotaPenjualan object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE NotaPenjualan SET Nominal=?, TanggalPenjualan=?, User_IdPengguna=? WHERE KodePenjualan=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(4, object.getKodePenjualan());
                ps.setDouble(1, object.getNominal());
                ps.setString(2, object.getTanggalPenjualan());

                //Foreign Key IdPengguna dari User
                ps.setString(3, object.getIdPengguna().getIdPengguna());

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
    public int deleteData(NotaPenjualan object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM nota WHERE KodePenjualan=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getKodePenjualan());

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
    public ObservableList<NotaPenjualan> showAllData() {
        ObservableList<NotaPenjualan> notas = FXCollections.
                observableArrayList();
        try {
            try (Connection connection = Utility.creatConnection()) {
                String query
                        = "SELECT np.KodePenjualan, np.Nominal, np.TanggalPenjualan, u.IdPengguna FROM NotaPenjualan np JOIN User u ON np.User_IdPengguna = u.IdPengguna  ORDER BY KodePenjualan";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setIdPengguna(rs.getString("IdPengguna"));

                    NotaPenjualan nota = new NotaPenjualan();
                    nota.setKodePenjualan(rs.getInt("KodePenjualan"));
                    nota.setNominal(rs.getDouble("Nominal"));
                    nota.setTanggalPenjualan(rs.getString("TanggalPenjualan"));

                    nota.setIdPengguna(user);

                    notas.add(nota);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return notas;
    }

    @Override
    public NotaPenjualan getData(NotaPenjualan id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NotaPenjualan> showData(String object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<NotaPenjualan> showTopData(String object, String object2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        ObservableList<NotaPenjualan> notas = FXCollections.
//                observableArrayList();
//        try {
//            try (Connection connection = Utility.creatConnection()) {
//                String query
//                        = "SELECT b.KodeBarang,b.NamaBarang,SUM(r.JumlahBarangTerjual) AS 'Total Terjual'from notapenjualan np JOIN barang_has_notapenjualan r ON r.NotaPenjualan_KodePenjualan = np.KodePenjualan JOIN Barang b ON b.KodeBarang = r.Barang_KodeBarangwhere np.TanggalPenjualan >'2018-02-23' AND np.TanggalPenjualan < '2018-02-25'GROUP BY b.KodeBarang, b.NamaBarangORDER BY SUM(r.JumlahBarangTerjual) DESC;";
//                PreparedStatement ps = connection.prepareStatement(query);
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    Barang barang = new Barang();
//                    RelasiBarangNotaPenjualan relasi = new RelasiBarangNotaPenjualan();
//                    NotaPenjualan nota = new NotaPenjualan();
//
//                    barang.setKodeBarang(rs.getString("KodeBarang"));
//                    barang.setNamaBarang(rs.getString("NamaBarang"));
//
//                    relasi.setJumlahBarangTerjual(rs.getInt("Total Terjual"));
//                    relasi.setKodeBarang(barang);
//                    relasi.setKodePenjualan(nota);
//
//                    nota.set
//
//                    notas.add(nota);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDaoImpl.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserDaoImpl.class.getName()).
//                    log(Level.SEVERE, null, ex);
//        }
//        return notas;
    }

}
