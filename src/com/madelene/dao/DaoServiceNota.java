/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.dao;

import java.util.List;

/**
 *
 * @author Madelene
 */
public interface DaoServiceNota<NotaPenjualan> {

    int addData(NotaPenjualan object);

    int deleteData(NotaPenjualan object);

    int updateData(NotaPenjualan object);

    List<NotaPenjualan> showAllData();

}
