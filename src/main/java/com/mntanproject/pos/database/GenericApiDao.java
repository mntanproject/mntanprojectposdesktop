package com.mntanproject.pos.database;

import com.google.gson.Gson;
import com.mntanproject.pos.supplier.Supplier;
import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;

public abstract class GenericApiDao<T> {

    T obj;
    private Class<T> type;
    Box<Supplier> supplierBox = ObjectBoxDB.get().boxFor(Supplier.class);
    QueryBuilder<Supplier> builder = supplierBox.query();
    Query<Supplier> query = builder.build();
    Gson gson = new Gson();

    protected GenericApiDao(Class<T> type) {
        this.type = type;
    }



        public T toObject(String param) {
        T returnedObject = null;
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
        returnedObject = gson.fromJson(json, type);



        return  returnedObject;
    }


}
