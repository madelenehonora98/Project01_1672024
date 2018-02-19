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
                        = "INSERT INTO RelasiBarangNotaPenjualan(KodePenjualan, KodeBarang, JumlahBarangTerjual) VALUES (?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getKodePenjualan().getKodePenjualan());
                ps.setString(2, object.getKodeBarang().getKodeBarang());
                ps.setInt(3, object.getJumlahBarangTerjual());

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
                    user.setIdPengguna(rs.getString("IdPengguna"));

                    NotaPenjualan nota = new NotaPenjualan();
                    nota.setKodePenjualan(rs.getInt("KodePenjualan"));
                    nota.setNominal(rs.getDouble("Nominal"));
                    nota.setTanggalPenjualan(rs.getString("TanggalPenjualan"));
                    nota.setIdPengguna(user);

                    Barang barang = new Barang();
                    barang.setKodeBarang(rs.getString("KodeBarang"));

                    relasiNotaBarang.setKodeBarang(barang);

                    relasiNotaBarang.getKodePenjualan().setKodePenjualan(rs.
                            getInt("KodePenjualan"));
                    relasiNotaBarang.getKodePenjualan().setNominal(rs.getDouble(
                            "Nominal"));
                    relasiNotaBarang.getKodePenjualan().setTanggalPenjualan(rs.
                            getString("TanggalPenjualan"));

                    relasiNotaBarang.setHargaJualSaatItu(rs.getDouble(
                            "HargaSaatItu"));
                    relasiNotaBarang.setJumlahBarangTerjual(rs.getInt(
                            "JumlahBarang"));

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

}
