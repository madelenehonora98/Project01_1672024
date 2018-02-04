/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import com.madelene.entity.User;
import com.madelene.entity.UserRole;
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
public class UserDaoImpl implements DaoService<User> {

    @Override
    public int addData(User object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO user(IdPengguna,NamaDepan, NamaBelakang,Alamat,NoTelepon,UserRole_idUserRole) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getIdPengguna());
                ps.setString(2, object.getNamaDepan());
                ps.setString(3, object.getNamaBelakang());
                ps.setString(4, object.getAlamat());
                ps.setString(5, object.getNoTelepon());
                ps.setString(6, object.getIdUserRole().getIdUserRole());
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
    public int updateData(User object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE user SET NamaDepan = ?, NamaBelakang = ?, Alamat=?, NoTelepon = ?, UserRole_idUserRole=? WHERE IdPengguna=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getIdPengguna());
                ps.setString(2, object.getNamaDepan());
                ps.setString(3, object.getNamaBelakang());
                ps.setString(4, object.getAlamat());
                ps.setString(5, object.getNoTelepon());
                ps.setString(6, object.getIdUserRole().getIdUserRole());
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
    public int deleteData(User object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM user WHERE IdPengguna=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getIdPengguna());

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
    public ObservableList<User> showAllData() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            try (Connection connection = Utility.creatConnection()) {
                String query = "SELECT * FROM User ORDER BY IdPengguna";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setIdPengguna(rs.getString("IdPengguna"));
                    user.setNamaDepan(rs.getString("NamaDepan"));
                    user.setNamaBelakang(rs.getString("NamaBelakang"));
                    user.setAlamat(rs.getString("Alamat"));
                    user.setNoTelepon(rs.getString("NoTelepon"));
                    UserRole userRole = new UserRole();
                    userRole.setIdUserRole(rs.getString("UserRole_idUserRole"));

                    user.setIdUserRole(userRole);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public User getData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
