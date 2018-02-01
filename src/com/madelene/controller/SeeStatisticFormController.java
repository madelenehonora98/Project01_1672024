/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Madelene
 */
public class SeeStatisticFormController implements Initializable {

    @FXML
    private Button btnBackOwner;
    @FXML
    private NumberAxis StatisticSales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnBackOwnerAct(ActionEvent event) {
    }

}
