package com.mntanproject.pos.item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.purchase.Purchase;
import io.objectbox.relation.ToOne;

public class TestItem {

    public static void main(String[] args) {
        ObjectBoxDB.init();

        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(ToOne.class, new ToOneSerializer<Purchase>());
        Gson gson = gsonBuilder.create();

        Item item1 = new Item("obeng","1234567","note",122,1000,15000);
        String jsonString = gson.toJson(item1); // Convert car object to JSON string
        System.out.println(jsonString);
    }
}
