package com.mntanproject.pos.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;

public class GenericApi<T> {


    private Class<T> type;
    Box<T> objBox;
    QueryBuilder<T> builder;
    Query<T> query;
    Gson gson;
    Property<T> property;
    Class<?> generatedClass;
    Util<T> util;

    public GenericApi(Class<?> generatedClass) {
        this.gson = new GsonBuilder().serializeNulls().create();
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.objBox = ObjectBoxDB.get().boxFor(type);
        this.generatedClass = generatedClass;
        this.util = new Util<T>(type);
        System.out.println("Type: " + type);
        //System.exit(1);
    }
    public Property getObjectBoxGeneratedClassProperty(String fieldName) {
        //Class<?> generatedClassInstance = null;
        Property<T> property = null;
        try {
            //generatedClassInstance = generatedClass.newInstance();
            Field idField = generatedClass.getField(fieldName);
            property = (Property<T>) idField.get(generatedClass);
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
            System.out.println("Error getObjectBoxGeneratedClassProperty: " + e.getCause());
            return null;
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
            System.out.println("Error getObjectBoxGeneratedClassProperty: " + e.getCause());
            return null;
        }
        return property;
    }


    public HttpResponse add(String params) {
        T obj = util.generateObject(params);
        String returnMsg = null;
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, gson.toJson("Failed to add user"));
        if(obj != null){
            objBox.put(obj);
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson("Succesfully added"));
        }
        return response;
    }

     public HttpResponse view(String params) {
        String returnMsg = null;
        if (params != null && params.length() != 0 && params.equalsIgnoreCase("all")) {
            List<T> objects = objBox.getAll();
            returnMsg = gson.toJson(objects);
        } else {

            long id = util.getIdGeneric(params);
            Property<T> property = getObjectBoxGeneratedClassProperty("id");
            T objQ = null;
            objQ = objBox.query().equal(property, id).build().findFirst();
            returnMsg = gson.toJson(objQ);
        }
        HttpResponse response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
        return response;
    }

    public HttpResponse delete(String params) {
        long id = 0;
        String returnMsg;

        id = util.getIdGeneric(params);
        boolean removed = false;
        removed = objBox.remove(id);
        if (removed) {
            returnMsg = "id: " + id + " is deleted";
        } else {
            returnMsg = "id: " + id + " delete failed";
        }

        HttpResponse response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson(returnMsg));
        return response;
    }

    public HttpResponse edit(String params) {
        long id = 0;
        String returnMsg = null;
        HttpResponse response = null;
        boolean edited = false;
        T obj = util.uriParamToObject(params);
        T objFromDB = null;

        id = util.getIdGeneric(params);

        if (id != 0) {
            id = util.getIdGeneric(params);
            Property<T> property = getObjectBoxGeneratedClassProperty("id");
            objFromDB = objBox.query().equal(property, id).build().findFirst();
            objFromDB = obj;
            objBox.put(objFromDB);
            returnMsg = gson.toJson(objFromDB);
        }


        response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson(returnMsg));

        return response;
    }


}
