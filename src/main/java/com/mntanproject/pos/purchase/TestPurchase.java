package com.mntanproject.pos.purchase;

import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.item.Item;
import io.objectbox.Box;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestPurchase {

    public void add(){

        Purchase purchase = new Purchase();
        purchase.supplierId = 1;
        purchase.createdOn = new Date();
        purchase.total = 12;
        Item item = new Item("item12","item12barcode","12",12,12,12);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        purchase.items = items;

        Box<Purchase> purchaseBox = ObjectBoxDB.get().boxFor(Purchase.class);
        long put = purchaseBox.put(purchase);
        System.out.println("put: " + put);


    }
    public void viewAll(){

        Box<Purchase> purchaseBox = ObjectBoxDB.get().boxFor(Purchase.class);
        List<Purchase> all = purchaseBox.getAll();
        for (int i=0;i<all.size();i++){
            System.out.println(all.get(i));
        }

    }

    public static void main(String[] args) {
        ObjectBoxDB.init();
        //Box purchaseBox = ObjectBoxDB.get().boxFor(Purchase.class);
        TestPurchase testPurchase = new TestPurchase();
        testPurchase.add();
        testPurchase.viewAll();

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
