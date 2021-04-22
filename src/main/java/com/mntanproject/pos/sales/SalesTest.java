package com.mntanproject.pos.sales;


import com.mntanproject.pos.database.ObjectBoxDB;
import io.objectbox.Box;
import io.objectbox.BoxStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SalesTest {

    public static void main(String[] args) {
        SalesTest salesTest = new SalesTest();
        salesTest.view();
    }

    public void view(){

        Box<Sales> salesBox = ObjectBoxDB.get().boxFor(Sales.class);
        List<Sales> all = salesBox.getAll();
        for (int i=0;i<all.size();i++){
            System.out.println(all.get(i));
        }
    }



}
