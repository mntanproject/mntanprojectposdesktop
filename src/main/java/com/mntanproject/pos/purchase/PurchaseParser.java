package com.mntanproject.pos.purchase;

import com.google.gson.*;
import com.mntanproject.pos.item.Item;
import com.mntanproject.pos.item.ItemParser;
import com.mntanproject.pos.supplier.Supplier;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PurchaseParser {

    Purchase purchase = null;

    public PurchaseParser(Purchase purchase) {
        this.purchase = purchase;
    }

    public String toJson(boolean parseToMany,boolean parseToOne) {

        String json = null;
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();


        HashMap jsonObjectPurchase  = new HashMap();
        jsonObjectPurchase.put("id", (purchase.id));
        jsonObjectPurchase.put("total", purchase.total);
        jsonObjectPurchase.put("createdOn",purchase.createdOn);

        if (parseToMany) {
            ItemParser itemParser;
            ArrayList items = new ArrayList();

          /*  for (int i = 0; i < purchase.itemsId.size(); i++) {

                //Item item = purchase.itemsId.get(i);
//                itemParser = new ItemParser(item);
//                String stringItem = (gson.toJson(item));
//                items.add(item);
            }*/
            jsonObjectPurchase.put("items",(items));

        }

        if(parseToOne){
            long supplierId = purchase.supplierId;
            //Supplier supplier = purchase.supplier.getTarget();

            //System.out.println("parseToOne - supplier: " + supplier);
            //jsonObjectPurchase.put("supplier",supplier);
        }

        json =  gson.toJson(jsonObjectPurchase);
        return json;
    }


    public Purchase fromJson(String json){
        Purchase purchase = new Purchase();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        purchase.id = jsonObject.get("id").getAsLong();
        purchase.total = jsonObject.get("total").getAsFloat();

        //purchase.createdOn = jsonObject.get("createdOn").


        Supplier supplier = new Supplier();
        JsonObject supplierJson = jsonObject.getAsJsonObject("supplier");
        supplier.id = supplierJson.get("id").getAsLong();
        supplier.name = supplierJson.get("name").getAsString();
        supplier.email = supplierJson.get("email").getAsString();
        supplier.company = supplierJson.get("company").getAsString();
        supplier.bank = supplierJson.get("bank").getAsString();
        supplier.city = supplierJson.get("city").getAsString();
        supplier.country = supplierJson.get("country").getAsString();
        supplier.state = supplierJson.get("state").getAsString();
        supplier.contactno = supplierJson.get("contactno").getAsString();
        supplier.notes = supplierJson.get("notes").getAsString();

       // purchase.supplier.setTarget(supplier);

        JsonArray itemsJson = jsonObject.get("items").getAsJsonArray();

        for (int i=0;i<itemsJson.size();i++){
            JsonObject itemJson = itemsJson.get(i).getAsJsonObject();
            Item item = new Item();
            item.id = itemJson.get("id").getAsLong();
            item.name = itemJson.get("name").getAsString();
            item.purchasePrice = itemJson.get("purchasePrice").getAsFloat();
            item.barcode = itemJson.get("barcode").getAsString();
            item.notes = itemJson.get("notes").getAsString();
            item.quantityOnHand = itemJson.get("quantityOnHand").getAsFloat();
            item.sellingPrice = itemJson.get("sellingPrice").getAsFloat();
            try {
                //purchase.items.add(item);
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("-----------------");
            System.out.println(item);
            System.out.println("*****************");
        }

        return purchase;
    }

}
