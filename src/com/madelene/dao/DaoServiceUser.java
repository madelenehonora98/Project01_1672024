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
public interface DaoServiceUser<User> {

    int addData(User object);

    int deleteData(User object);

    int updateData(User object);

    List<User> showAllData();
}
