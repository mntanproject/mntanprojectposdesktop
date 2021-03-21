package com.mntanproject.pos.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.sun.net.httpserver.HttpPrincipal;
import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenericRelationApi<T> extends GenericApi<T> {

    Class<?> generatedClass;

    public GenericRelationApi(Class<?> generatedClass) {
        super(generatedClass);
        this.generatedClass = generatedClass;
    }

    public T fromJson(String json){
        return null;
    }
    public String toJson(Object obj) {
        return null;
    }

    @Override
    public  HttpResponse add(String params){
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, null);
        System.out.println("params: " + params);
        T obj = fromJson(params);
        if(obj != null){
            objBox.put(obj);
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson("Succesfully added"));
        }
        return response;
    }
    @Override
    public HttpResponse view(String params) {
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, null);
        String returnMsg = null;
        ArrayList<String> jsonArray = new ArrayList<>();
        String jsonObj = null;
        String obj;
        if (params != null && params.length() != 0 && params.contains("all")) {
            List<T> objects = objBox.getAll();

            for (int i = 0; i < objects.size(); i++) {
                obj = toJson(objects.get(i));
                jsonArray.add(obj);
            }
            returnMsg = jsonArray.toString();
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);

        } else {
            long id = util.getIdGeneric(params);
            Property<T> property = getObjectBoxGeneratedClassProperty("id");
            T objQ = null;
            objQ = objBox.query().equal(property, id).build().findFirst();
            if (objQ != null) {
                returnMsg = toJson(objQ);
                response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
            }
        }

        return response;
    }




}
