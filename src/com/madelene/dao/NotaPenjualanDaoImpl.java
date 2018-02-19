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
                        = "INSERT INTO NotaPenjualan(KodePenjualan, Nominal, TanggalPenjualan, User_IdPengguna) VALUES (?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, object.getKodePenjualan());
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
                ps.setInt(1, object.getKodePenjualan());
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
                        = "SELECT np.KodePenjualan, np.Nominal, np.TanggalPenjualan, u.IdPengguna FROM NotaPenjualan np JOIN User u ON np.IdPengguna = u.IdPengguna  ORDER BY KodePenjualan";
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

}
