/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import com.madelene.entity.UserRole;
import com.madelene.utility.DaoService;
import com.madelene.utility.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Madelene
 */
public class UserRoleDaoImpl implements DaoService<UserRole> {

    @Override
    public int addData(UserRole object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "INSERT INTO UserRole(IdUserRole, Jabatan) VALUES (?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getIdUserRole());
                ps.setString(2, object.getJabatan());

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
    public int updateData(UserRole object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "UPDATE UserRole SET Jabatan=?, WHERE IdUserRole=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getIdUserRole());
                ps.setString(2, object.getJabatan());

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
    public int deleteData(UserRole object) {
        int result = 0;
        try {
            try (Connection connection = Utility.creatConnection()) {
                connection.setAutoCommit(false);
                String query
                        = "DELETE FROM UserRole WHERE IdUserRole=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, object.getIdUserRole());

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
    public List<UserRole> showAllData() {
        List<UserRole> roles = new ArrayList<>();
        String query = "SELECT U.* FROM UserRole u ORDER BY u.idUserRole";
        try {
            Connection connection = Utility.creatConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserRole role = new UserRole();
                role.setIdUserRole(rs.getString("idUserRole"));
                role.setJabatan(rs.getString("Jabatan"));
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRoleDaoImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return roles;
    }

    @Override
    public UserRole getData(UserRole id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
