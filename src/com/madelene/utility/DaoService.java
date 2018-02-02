/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madelene.utility;

import java.util.List;

/**
 *
 * @author Madelene
 */
public interface DaoService<E> {

    int addData(E object);

    int updateData(E object);

    int deleteData(E object);

    List<E> showAllData();

    E getData(Object id);
}
