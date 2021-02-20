package com.mntanproject.pos.item;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mntanproject.pos.purchase.Purchase;

import java.util.HashMap;

public class ItemParser {

    Item item = null;
    public ItemParser(Item item){
        this.item  = item;
    }

    public String toJson(boolean parseToOne){

        String json = null;
        Gson gson = new Gson();
        HashMap jsonObject = new HashMap();
        jsonObject.put("id",item.id);
        jsonObject.put("name",item.name);
        jsonObject.put("barcode",item.barcode);
        jsonObject.put("notes",item.notes);
        jsonObject.put("quantityOnHand",item.quantityOnHand);
        jsonObject.put("purchasePrice",item.purchasePrice);
        jsonObject.put("sellingPrice",item.sellingPrice);

        if (parseToOne){
            Purchase itemPurchase = null;
            HashMap jsonObjectPurchase = new HashMap();
            itemPurchase = item.purchase.getTarget();

            jsonObjectPurchase.put("id",itemPurchase.id);
            jsonObjectPurchase.put("total",itemPurchase.total);
            jsonObjectPurchase.put("createdOn",itemPurchase.createdOn.toString());

            jsonObject.put("purchase",jsonObjectPurchase);
        }



        json = jsonObject.toString();

        return json;
    }













}
