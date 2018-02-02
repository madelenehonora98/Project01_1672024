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

/**
 *
 * @author Madelene
 */
public class UserDaoImpl implements DaoService<User> {

    @Override
    public int addData(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteData(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    user.setNoTelepon(rs.getString("NoTelepom"));
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
