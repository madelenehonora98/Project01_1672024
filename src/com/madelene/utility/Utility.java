/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Madelene
 */
public class Utility {

    private static Alert alert;

    public static Connection creatConnection() throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbPenjualan", "root", "");
        return connection;
    }

    public static void showAlert(String title, String content,
            Alert.AlertType type) {
        alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(type);
        alert.setContentText(content);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
