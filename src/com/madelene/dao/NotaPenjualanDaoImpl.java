/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import com.madelene.entity.NotaPenjualan;
import com.madelene.utility.DaoService;
import com.madelene.utility.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
                        = "INSERT INTO NotaPenjualan(KodePenjualan, Nominal, TanggalPenjualan, User_IdPengguna) VALUES (?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getKodePenjualan());
                ps.setDouble(2, object.getNominal());
                ps.setString(3, object.getTanggalPenjualan());

                //Foreign Key IdPengguna dari User
                ps.setString(4, object.getIdPengguna().getIdPengguna());

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
                ps.setString(1, object.getKodePenjualan());
                ps.setDouble(2, object.getNominal());
                ps.setString(3, object.getTanggalPenjualan());

                //Foreign Key IdPengguna dari User
                ps.setString(4, object.getIdPengguna().getIdPengguna());

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
                        = "DELETE FROM barang WHERE KodePenjualan=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getKodePenjualan());

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
    public List<NotaPenjualan> showAllData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NotaPenjualan getData(NotaPenjualan id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
