package com.mntanproject.pos.item;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ItemSerializer implements JsonSerializer<Item> {
    @Override
    public JsonElement serialize(Item src, Type typeOfSrc, JsonSerializationContext context) {

//        JsonObject jsonItem = new JsonObject();
//
//        jsonItem.addProperty("Id",src.id);
//        jsonItem.addProperty("name", src.name);
//        jsonItem.addProperty("barcode",src.barcode);
//        jsonItem.addProperty("notes",src.notes);
//        jsonItem.addProperty("quantityOnHand",src.quantityOnHand);
//        jsonItem.addProperty("purchasePrice",src.purchasePrice);
//        jsonItem.addProperty("sellingPrice",src.sellingPrice);
        //jsonItem.addProperty("purchase",src.purchase);
        System.out.println(src);
return null;

    }
}
