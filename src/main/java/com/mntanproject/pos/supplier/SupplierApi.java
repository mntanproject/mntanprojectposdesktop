package com.mntanproject.pos.supplier;

import com.google.gson.Gson;
import com.mntanproject.pos.database.ObjectBoxDB;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.util.HashMap;
import java.util.List;

public class SupplierApi {

    Box<Supplier> supplierBox = ObjectBoxDB.get().boxFor(Supplier.class);
    Gson gson = new Gson();

    public Supplier toObject(String param) {

        System.out.println("param: " + param);
        String[] splits = param.split("&");
        HashMap<String, String> mappedParam = new HashMap<String, String>();

        for (String string : splits) {
            String[] keyValue = null;
            keyValue = string.split("=");
            if(keyValue.length == 2) {
                mappedParam.put(keyValue[0], keyValue[1]);
            }
        }
        String json = gson.toJson(mappedParam);
        Supplier supplier = gson.fromJson(json, Supplier.class);
        return supplier;
    }

    public HttpResponse add(String params){

        Supplier supplier = toObject(params);

        String paramJson = gson.toJson(supplier);
        supplierBox.put(supplier);
        HttpResponse response  = new HttpResponse(StatusCode.OK, ContentType.JSON,paramJson);
        return response;
    }
    public HttpResponse view(String params){
        List<Supplier>  suppliers = supplierBox.getAll();
        String returnMsg = gson.toJson(suppliers);

        HttpResponse response  = new HttpResponse(StatusCode.OK, ContentType.JSON,returnMsg);
        return response;
    }


}
