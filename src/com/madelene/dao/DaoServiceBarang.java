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
public interface DaoServiceBarang<Barang> {

    int addData(Barang object);

    int deleteData(Barang object);

    int updateData(Barang object);

    List<Barang> showAllData();
}
