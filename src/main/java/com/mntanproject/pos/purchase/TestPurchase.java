package com.mntanproject.pos.purchase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mntanproject.pos.MyObjectBox;
import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.item.Item;
import com.mntanproject.pos.item.ToManySerializer;
import com.mntanproject.pos.item.ToOneSerializer;
import com.mntanproject.pos.supplier.Supplier;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestPurchase {


    public static void main(String[] args) {
        ObjectBoxDB.init();
        Box purchaseBox = ObjectBoxDB.get().boxFor(Purchase.class);
        try {

            PurchaseApi purchaseApi = new PurchaseApi();
            purchaseApi.toJson("ToMany");
            List<Purchase> purchases = purchaseBox.getAll();
            for (int i=0;i<purchases.size();i++){
                Purchase purchase = purchases.get(i);
                System.out.println("id: " + purchase.id + " Total: " + purchase.total);
            }


            /*
             TestPurchase testPurchase = new TestPurchase();
             testPurchase.deleteAll();
                        testPurchase.add();
                        testPurchase.viewAll();
                        BoxStore store = MyObjectBox.builder().name("objectbox-mntanprojectpos-db").build();
                        Gson gson = new Gson();

                        List<Item> items = new ArrayList<>();
                        for (int i=0;i<purchase.items.size();i++){
                            Item item = purchase.items.get(i);
                            items.add(item);
                        }
                        String json = gson.toJson(items);
                        System.out.println(json);
            long purchaseId = objBox.put(purchase);
                        System.out.println("purchaseID:" + purchaseId);
                        Purchase purchase = objBox.get(1);
                        System.out.println(purchase.id);
                        for (Item item : purchase.items) {
                            System.out.println(item.id + "-" + item.name);
                        }
             itemBox.put(new Item("direct","0000000","note3",344,4000,40000));
                        List<Item> items = itemBox.getAll();
                        System.out.println("direct size: " + items.size() + " - id=1 - " + items.get(2).name);
                   System.out.println("size:" + purchaseApi.size("all"));
            */


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewAll() {
        Box<Purchase> objBox = ObjectBoxDB.get().boxFor(Purchase.class);
        List<Purchase> purchases = objBox.getAll();
        System.out.println(purchases.size());
        Purchase purchase = null;

        for (int i = 0; i < purchases.size(); i++) {
            purchase = purchases.get(i);
            PurchaseParser purchaseParser = new PurchaseParser(purchase);
            purchaseParser.toJson(true);

        }
    }

    public void deleteAll() {
        Box<Purchase> objBox = ObjectBoxDB.get().boxFor(Purchase.class);
        objBox.removeAll();
    }

    public void add() {
        Box<Purchase> objBox = ObjectBoxDB.get().boxFor(Purchase.class);
        Item item1 = new Item("obeng", "1234567", "note",
                122, 1000, 15000);
        Item item2 = new Item("martil", "890123", "note2",
                333, 3000, 20000);
        Purchase purchase = new Purchase();
        purchase.total = 122000;
        purchase.items.add(item1);
        purchase.items.add(item2);
        objBox.put(purchase);

    }

    public void addAll() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ToOne.class, new ToOneSerializer<Purchase>());
        gsonBuilder.registerTypeAdapter(ToMany.class, new ToManySerializer<Purchase>());
        Box<Purchase> objBox = ObjectBoxDB.get().boxFor(Purchase.class);
        Gson gson = gsonBuilder.create();

        String jsonString = null;

        List<Purchase> purchases = objBox.getAll();
        Purchase purchase = null;
        PurchaseParser purchaseParser = null;


        for (int i = 0; i < purchases.size(); i++) {
            purchase = purchases.get(i);
            purchaseParser = new PurchaseParser(purchase);
            jsonString = purchaseParser.toJson(false);
            System.out.println("jsonString:" + jsonString);
        }


    }


}
