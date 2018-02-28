/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import com.madelene.entity.Barang;
import com.madelene.entity.NotaPenjualan;
import com.madelene.entity.RelasiBarangNotaPenjualan;
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

/**
 *
 * @author Madelene
 */
public class RelasiBarangNotaPenjualanDaoImpl implements
        DaoService<RelasiBarangNotaPenjualan> {

    @Override
    public int addData(RelasiBarangNotaPenjualan object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO barang_has_notapenjualan(NotaPenjualan_KodePenjualan, Barang_KodeBarang, JumlahBarangTerjual,HargaJualSaatIni) VALUES (?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getKodePenjualan().getKodePenjualan());
                ps.setString(2, object.getKodeBarang().getKodeBarang());
                ps.setInt(3, object.getJumlahBarangTerjual());
                ps.setDouble(4, object.getHargaJualSaatItu());

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
    public int updateData(RelasiBarangNotaPenjualan object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteData(RelasiBarangNotaPenjualan object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<RelasiBarangNotaPenjualan> showAllData() {
        ObservableList<RelasiBarangNotaPenjualan> relasiPenjualans
                = FXCollections.
                observableArrayList();
        try {
            try (Connection connection = Utility.creatConnection()) {
                String query
                        = "SELECT np.KodePenjualan, rbn.JumlahBarangTerjual, b.KodeBarang, rbn.HargaJualSaatIni, np.TanggalPenjualan, u.IdPengguna FROM barang_has_notapenjualan rbn JOIN NotaPenjualan np ON rbn.NotaPenjualan_KodePenjualan = np.KodePenjualan JOIN User u ON np.User_IdPengguna = u.IdPengguna JOIN Barang b ON b.KodeBarang = rbn.Barang_KodeBarang ORDER BY KodePenjualan";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    RelasiBarangNotaPenjualan relasiNotaBarang
                            = new RelasiBarangNotaPenjualan();
                    User user = new User();
                    NotaPenjualan nota = new NotaPenjualan();
                    Barang barang = new Barang();
                    user.setIdPengguna(rs.getString("u.IdPengguna"));
                    nota.setKodePenjualan(rs.getInt("np.KodePenjualan"));
                    nota.
                            setTanggalPenjualan(rs.getString(
                                    "np.TanggalPenjualan"));
                    barang.setKodeBarang(rs.getString("b.KodeBarang"));

                    relasiNotaBarang.setJumlahBarangTerjual(rs.getInt(
                            "rbn.JumlahBarangTerjual"));
                    relasiNotaBarang.setHargaJualSaatItu(rs.getDouble(
                            "rbn.HargaJualSaatIni"));
                    relasiNotaBarang.setKodeBarang(barang);
                    nota.setIdPengguna(user);
                    relasiNotaBarang.setKodePenjualan(nota);
                    relasiPenjualans.add(relasiNotaBarang);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return relasiPenjualans;
    }

    @Override
    public RelasiBarangNotaPenjualan getData(RelasiBarangNotaPenjualan id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RelasiBarangNotaPenjualan> showData(
            String object) {
        ObservableList<RelasiBarangNotaPenjualan> relasiPenjualans
                = FXCollections.
                observableArrayList();
        try {
            try (Connection connection = Utility.creatConnection()) {
                String query
                        = "SELECT np.KodePenjualan, rbn.JumlahBarangTerjual, b.KodeBarang, rbn.HargaJualSaatIni, np.TanggalPenjualan, u.IdPengguna FROM barang_has_notapenjualan rbn JOIN NotaPenjualan np ON rbn.NotaPenjualan_KodePenjualan = np.KodePenjualan JOIN User u ON np.User_IdPengguna = u.IdPengguna JOIN Barang b ON b.KodeBarang = rbn.Barang_KodeBarang WHERE LEFT(np.TanggalPenjualan,4) = LEFT(?,4) ORDER BY KodePenjualan";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    RelasiBarangNotaPenjualan relasiNotaBarang
                            = new RelasiBarangNotaPenjualan();
                    User user = new User();
                    NotaPenjualan nota = new NotaPenjualan();
                    Barang barang = new Barang();
                    user.setIdPengguna(rs.getString("u.IdPengguna"));
                    nota.setKodePenjualan(rs.getInt("np.KodePenjualan"));
                    nota.
                            setTanggalPenjualan(rs.getString(
                                    "np.TanggalPenjualan"));
                    barang.setKodeBarang(rs.getString("b.KodeBarang"));
                    relasiNotaBarang.setJumlahBarangTerjual(rs.getInt(
                            "rbn.JumlahBarangTerjual"));
                    relasiNotaBarang.setHargaJualSaatItu(rs.getDouble(
                            "rbn.HargaJualSaatIni"));
                    relasiNotaBarang.setKodeBarang(barang);
                    nota.setIdPengguna(user);
                    relasiNotaBarang.setKodePenjualan(nota);
                    relasiPenjualans.add(relasiNotaBarang);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return relasiPenjualans;
    }

    @Override
    public List<RelasiBarangNotaPenjualan> showTopData(String object,
            String object2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        ObservableList<NotaPenjualan> notas = FXCollections.
//                observableArrayList();
//        try {
//            try (Connection connection = Utility.creatConnection()) {
//                String query
//                        = "SELECT b.KodeBarang,b.NamaBarang,SUM(r.JumlahBarangTerjual) AS 'Total Terjual'from notapenjualan np JOIN barang_has_notapenjualan r ON r.NotaPenjualan_KodePenjualan = np.KodePenjualan JOIN Barang b ON b.KodeBarang = r.Barang_KodeBarangwhere np.TanggalPenjualan >'?' AND np.TanggalPenjualan < '?' GROUP BY b.KodeBarang, b.NamaBarangORDER BY SUM(r.JumlahBarangTerjual) DESC;";
//                PreparedStatement ps = connection.prepareStatement(query);
//                ps.setString(0, object);
//                ps.setString(1, object2);
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
//                    nota.se
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
