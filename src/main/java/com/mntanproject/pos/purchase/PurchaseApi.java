package com.mntanproject.pos.purchase;

import com.google.gson.*;
import com.mntanproject.pos.database.GenericApi;
import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.database.Util;
import com.mntanproject.pos.item.Item;
import com.mntanproject.pos.item.ItemApi;
import com.mntanproject.pos.supplier.Supplier;
import com.mntanproject.pos.supplier.Supplier_;
import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Comparator.comparing;

public class PurchaseApi extends GenericApi<Purchase> {

    private static final Class<?> generatedClass = Purchase_.class;
    Box<PurchaseItem> purchaseItemBox = ObjectBoxDB.get().boxFor(PurchaseItem.class);
    Box<Item> itemBox = ObjectBoxDB.get().boxFor(Item.class);
    Box<Supplier> supplierBox = ObjectBoxDB.get().boxFor(Supplier.class);

    public PurchaseApi() {
        super(generatedClass);
    }

    @Override
    public HttpResponse view(String params) {
        String returnMsg = null;

        if (params != null && params.length() != 0 && params.contains("all")) {
            List<Purchase> objects = objBox.getAll();
            returnMsg = gson.toJson(objects);
        } else {
            long id = util.getIdGeneric(params);
            Property<Purchase> property = getObjectBoxGeneratedClassProperty("id");
            Purchase objQ = null;
            objQ = objBox.query().equal(property, id).build().findFirst();
            System.out.println("objQ" + objQ);
            JsonObject jsonObject = gson.fromJson(gson.toJson(objQ), JsonObject.class);
            Supplier supplier = supplierBox.get(jsonObject.get("supplierId").getAsLong());
            jsonObject.add("supplier", JsonParser.parseString(gson.toJson(supplier))
                    .getAsJsonObject());


            returnMsg = jsonObject.toString();
        }
        HttpResponse response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
        return response;
    }

    @Override
    public HttpResponse edit(String params) {
        long id = 0;
        String returnMsg = null;
        HttpResponse response = null;
        boolean edited = false;
        System.out.println("Edit params: " + params);
        Purchase obj = util.generateObject(params);
        System.out.println("Edit: " + obj);
        // T objFromDB = null;

        response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson(returnMsg));

        return response;
    }


    @Override
    public HttpResponse delete(String params) {
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON,
                gson.toJson("Failed delete purchase"));
        long id = 0;
        String returnMsg;
        id = util.getIdGeneric(params);
        boolean removed = false;
        long purchaseItemRemoved;
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        try {
            removed = objBox.remove(id);
            purchaseItems = purchaseItemBox.query().equal(PurchaseItem_.purchaseId, id)
                    .build().find();
            purchaseItemBox.remove(purchaseItems);
            for (int i = 0; i < purchaseItems.size(); i++) {
                itemBox.remove(purchaseItems.get(i).itemId);
            }
            response = new HttpResponse(StatusCode.OK, ContentType.JSON,
                    gson.toJson("Successfully delete purchase"));

        } catch (Exception e) {


        }

        return response;
    }

    @Override
    public HttpResponse paginator(String params) {
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, gson.toJson("Failed paginator purchase"));
        JsonObject jsonObject = new JsonObject(), supplierJson = new JsonObject(), itemIdJsonObject = new JsonObject(),
                purchaseItemJsonObject = new JsonObject(), purchaseIdJsonObject = new JsonObject();

        JsonArray itemsJson = new JsonArray();
        List<Purchase> purchases = objBox.getAll();
        JsonObject returnJsonObject = new JsonObject();
        JsonArray returnJsonArray = new JsonArray();


        HashMap<String, String> mappedParam = new HashMap<String, String>();

        if (params != null && params.length() != 0) {
            String[] splits = params.split("&");
            for (String string : splits) {
                String[] keyValue = null;
                keyValue = string.split("=");
                System.out.println("key size: " + keyValue.length + "string:" + string);
                if (keyValue.length >= 2) {
                    mappedParam.put(keyValue[0], keyValue[1]);
                }
            }
            for (String str : mappedParam.keySet()) {
                System.out.println(str + " - " + mappedParam.get(str));
            }
        }
        if (mappedParam.containsKey("order")) {
            if (mappedParam.get("order").equalsIgnoreCase("total")) {
                if (mappedParam.get("descending").equalsIgnoreCase("1")) {
                    purchases.sort(comparing(Purchase::getTotal).reversed());
                } else {
                    purchases.sort(comparing(Purchase::getTotal));
                }

            }
            if (mappedParam.get("order").equalsIgnoreCase("createdOn")) {
                if (mappedParam.get("descending").equalsIgnoreCase("1")) {
                    purchases.sort(comparing(Purchase::getCreatedOn).reversed());
                } else {
                    purchases.sort(comparing(Purchase::getCreatedOn));
                }

            }
            if (mappedParam.get("order").equalsIgnoreCase("id")) {
                if (mappedParam.get("descending").equalsIgnoreCase("1")) {
                    purchases.sort(comparing(Purchase::getId).reversed());
                } else {
                    purchases.sort(comparing(Purchase::getId));
                }

            }
        }

        int pageBreak = Integer.valueOf(mappedParam.get("limit"))
                + Integer.valueOf(mappedParam.get("offset")) ;

        // System.out.println("" + mappedParam.get("offset"));
        //System.out.println("" + mappedParam.get("limit"));

        for (int i = Integer.valueOf(mappedParam.get("offset")); i < purchases.size(); i++) {
            System.out.println("no-break, " + i);
            if (i == pageBreak) {
                //System.out.println("break, " + i);
                break;
            }
            jsonObject = gson.fromJson(gson.toJson(purchases.get(i)), JsonObject.class);
            System.out.println("jsonObject: " + jsonObject.toString());
            Supplier supplier = supplierBox.get(purchases.get(i).supplierId);
            jsonObject.add("supplier", JsonParser.parseString(gson.toJson(supplier))
                    .getAsJsonObject());
//            QueryBuilder queryBuilder = purchaseItemBox.query();
//            queryBuilder.equal(PurchaseItem_.purchaseId, purchases.get(i).id);
//            List<PurchaseItem> itemsId = queryBuilder.build().find();
 //           System.out.println("itemsId: " + itemsId.size());
//            List<Item> items = new ArrayList<>();
//            itemsJson = new JsonArray();
//            for (int j = 0; j < itemsId.size(); j++) {
//                Item item = itemBox.get(itemsId.get(j).itemId);
//                if (item != null) {
//                    itemsJson.add(JsonParser.parseString(gson.toJson(item)).getAsJsonObject());
//                }
//            }
//            jsonObject.add("items", itemsJson);
            returnJsonArray.add(jsonObject);

        }


        response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnJsonArray.toString());
        return response;
    }

    @Override
    public HttpResponse add(String params) {

        boolean noError = true;
        JsonObject jsonObject = new JsonObject(), supplierJson = new JsonObject(),
                itemIdJsonObject = new JsonObject(),
                purchaseItemJsonObject = new JsonObject(), purchaseIdJsonObject = new JsonObject();

        JsonArray itemsJson = new JsonArray();

        try {

            jsonObject = gson.fromJson(params, JsonObject.class);
            supplierJson = gson.fromJson(jsonObject.get("supplier"), JsonObject.class);
            itemsJson = gson.fromJson(jsonObject.get("items"), JsonArray.class);

            jsonObject.remove("supplier");
            //jsonObject.remove("items");
            jsonObject.add("supplierId", supplierJson.get("id"));
            purchaseIdJsonObject = new JsonObject();
            purchaseIdJsonObject.addProperty("id",
                    objBox.put(gson.fromJson(jsonObject.toString(),Purchase.class)));

            Purchase dbPurchase  =  objBox.get(purchaseIdJsonObject.get("id").getAsLong());
            ArrayList<Item> purchaseDbItems =dbPurchase.items;
            for (int i = 0; i < itemsJson.size(); i++) {

                purchaseItemJsonObject.addProperty("purchaseId", purchaseIdJsonObject.get("id").getAsLong());
                itemIdJsonObject = new JsonObject();
                itemIdJsonObject.addProperty("id",
                        itemBox.put(gson.fromJson(itemsJson.get(i), Item.class)));
                purchaseDbItems.get(i).setId(itemIdJsonObject.get("id").getAsLong());
                purchaseItemJsonObject.add("itemId", itemIdJsonObject.get("id"));
                purchaseItemBox.put(gson.fromJson(purchaseItemJsonObject.toString(), PurchaseItem.class));
            }
            dbPurchase.setItems(purchaseDbItems);
            objBox.put(dbPurchase);

        } catch (Exception e) {
            noError = false;
        }

        System.out.println("gson: " + jsonObject.toString());
        System.out.println("supplier: " + supplierJson.toString());
        System.out.println("items: " + itemsJson.toString());

        //  Purchase obj = (Purchase) util.generateObject(params);
        //  String returnMsg = null;
        HttpResponse response;
        if (noError) {
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson("Successfully added"));
        } else {
            response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, gson.toJson("Failed to add user"));
        }
        return response;
    }
}
