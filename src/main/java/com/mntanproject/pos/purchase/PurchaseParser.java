package com.mntanproject.pos.purchase;

import com.google.gson.*;
import com.mntanproject.pos.item.Item;
import com.mntanproject.pos.item.ItemParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchaseParser {

    Purchase purchase = null;

    public PurchaseParser(Purchase purchase) {
        this.purchase = purchase;
    }

    public String toJson(boolean parseToMany) {

        String json = null;
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();


        HashMap jsonObjectPurchase  = new HashMap();
        jsonObjectPurchase.put("id", (purchase.id));
        jsonObjectPurchase.put("total", purchase.total);

        if (parseToMany) {
            ItemParser itemParser;
            ArrayList items = new ArrayList();

            for (int i = 0; i < purchase.items.size(); i++) {
                Item item = purchase.items.get(i);
                itemParser = new ItemParser(item);
                String stringItem = (itemParser.toJson(false));
                items.add(stringItem);
            }
            jsonObjectPurchase.put("items",(items));

        }

        json =  gson.toJson(jsonObjectPurchase);
        return json;
    }


}
