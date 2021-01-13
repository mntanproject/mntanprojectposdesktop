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

    public HttpResponse size(String params) {
        String returnMsg = null;
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, returnMsg);;
        if (params != null && params.length() != 0 && params.equalsIgnoreCase("all")) {
            int size = objBox.getAll().size();
            HashMap<String, Integer> sizeObj = new HashMap<String, Integer>();
            sizeObj.put("totalsize",size);
            returnMsg = gson.toJson(sizeObj);
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
        }
        if (params != null && params.length() != 0 && params.contains("search=")) {
            int size = searchSize(params);
            HashMap<String, Integer> sizeObj = new HashMap<String, Integer>();
            sizeObj.put("totalsize",size);
            returnMsg = gson.toJson(sizeObj);
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
        }
        return response;
    }

     public HttpResponse view(String params) {
        String returnMsg = null;
        if (params != null && params.length() != 0 && params.contains("all")) {
            System.out.println("calllllllllllllllllllllll");
            List<T> objects = objBox.getAll();
            returnMsg = gson.toJson(objects);
        } else {
            System.out.println("rrrrrrrrrrrrrrrrrr");
            long id = util.getIdGeneric(params);
            Property<T> property = getObjectBoxGeneratedClassProperty("id");
            T objQ = null;
            objQ = objBox.query().equal(property, id).build().findFirst();
            returnMsg = gson.toJson(objQ);
        }
        HttpResponse response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
        return response;
    }
    public HttpResponse paginator(String params) {
        String returnMsg = null;
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, returnMsg);;
        long limitLong = 0,offsetLong = 0;
        String orderBy , descending = null;
        Property<T> propertyCompany = getObjectBoxGeneratedClassProperty("company");
        Property<T> propertyName = getObjectBoxGeneratedClassProperty("name");
        Property<T> propertyCountry = getObjectBoxGeneratedClassProperty("country");
        Property<T> propertyCity = getObjectBoxGeneratedClassProperty("city");
        Property<T> propertyState = getObjectBoxGeneratedClassProperty("state");

        QueryBuilder<T> query = objBox.query();

        if (params != null && params.length() != 0 ) {
            String[] splits = params.split("&");
            HashMap<String, String> mappedParam = new HashMap<String, String>();

            for (String string : splits) {
                String[] keyValue = null;
                keyValue = string.split("=");
                System.out.println("key size: " + keyValue.length);
                if (keyValue.length >= 2) {
                    mappedParam.put(keyValue[0], keyValue[1]);
                }
            }
            if (mappedParam.containsKey("search")) {
                query.contains(propertyCompany, mappedParam.get("search"));
                query.or().contains(propertyName, mappedParam.get("search"));
                query.or().contains(propertyCountry, mappedParam.get("search"));
                query.or().contains(propertyCity, mappedParam.get("search"));
                query.or().contains(propertyState, mappedParam.get("search"));
            }
            if (mappedParam.containsKey("offset") && mappedParam.containsKey("limit")){
                Property<T> property = getObjectBoxGeneratedClassProperty("id");
                offsetLong = Long.parseLong(mappedParam.get("offset"));
                limitLong = Long.parseLong(mappedParam.get("limit"));


                if(mappedParam.containsKey("order")&&mappedParam.containsKey("descending")) {
                    property = getObjectBoxGeneratedClassProperty(mappedParam.get("order"));
                    descending = mappedParam.get("descending");
                    if (descending != null && descending.equalsIgnoreCase("1")){
                        query.order(property,QueryBuilder.DESCENDING);
                    }
                }else if (mappedParam.containsKey("order")){
                    property = getObjectBoxGeneratedClassProperty(mappedParam.get("order"));
                    query.order(property);
                }else{
                    query.order(property,QueryBuilder.DESCENDING);
                }
                System.out.println("query: +");
                System.out.println("query: " + query.toString());
                List<T> objects = query.build().find(offsetLong,limitLong);
                returnMsg = gson.toJson(objects);
                response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
            }
        }

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
        System.out.println("Edit params: " + params);
        T obj = util.generateObject(params);
        System.out.println("Edit: " + obj);
        T objFromDB = null;

        id = util.getIdGeneric(params);
        System.out.println("Edit id: " + id);
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

    public int searchSize(String params) {
        long id = 0;
        int size = 0;

        String searchTerm = null;
        if (params != null && params.contains("search=")){
            searchTerm = params.substring(params.lastIndexOf("=")+1);
        }
        Property<T> propertyCompany = getObjectBoxGeneratedClassProperty("company");
        Property<T> propertyName = getObjectBoxGeneratedClassProperty("name");
        QueryBuilder<T> query = objBox.query();
        query.contains(propertyCompany,searchTerm);
        query.or().contains(propertyName,searchTerm);
        List<T> objects = query.build().find();
        size = objects.size();
        return size;
    }





}
