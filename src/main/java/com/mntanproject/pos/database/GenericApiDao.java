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

public class GenericApiDao<T> {


    private Class<T> type;
    Box<T> objBox;
    QueryBuilder<T> builder;
    Query<T> query;
    Gson gson;
    Property<T> property;
    Class<?> generatedClass;

    public GenericApiDao(Class<?> generatedClass) {
        //this.type = type;

        this.gson = new GsonBuilder().serializeNulls().create();

        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.objBox = ObjectBoxDB.get().boxFor(type);
        this.generatedClass = generatedClass;

        System.out.println("Type: " + type);
        //System.exit(1);
    }

    public T uriParamToObject(String param) {

        T returnedObject = null;
        String[] splits = param.split("&");
        HashMap<String, String> mappedParam = new HashMap<String, String>();

        for (String string : splits) {
            String[] keyValue = null;
            keyValue = string.split("=");
            System.out.println("key size: " + keyValue.length);
            if (keyValue.length == 2) {
                mappedParam.put(keyValue[0], keyValue[1]);
            }
        }
        String json = gson.toJson(mappedParam);
        if (!isEmptyObject(json)){
            returnedObject = gson.fromJson(json, type);
        }
        System.out.println(json);



        return returnedObject;
    }
    
    public boolean isEmptyObject(String json){
        boolean empty = true;
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (json.contains(field.getName()))
            {
                empty = false;
            }
        }
        return empty;
    }

    public String objectToJson(T object) {

        String json = gson.toJson(object, type);
        return json;
    }
    public T jsonToObject(String json){
        T obj = null;
        obj = gson.fromJson(json, type);
        return obj;
    }

    public boolean isValidJson(String params) {
        boolean valid = false;
        try {

            T obj = gson.fromJson(params, type);
            System.out.println("object: " + obj);
            System.out.println("json: " + objectToJson(obj));
            valid = true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return valid;
    }

    public HttpResponse add(String params) {
        T obj = null;
        String returnMsg = null;
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, gson.toJson("Failed to add user"));

        if(isValidJson(params)){
            obj = jsonToObject(params);
        } else {
             obj = uriParamToObject(params);
        }

        if(obj != null){
            objBox.put(obj);
            response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson("Succesfully added"));
        }
        return response;
    }

    public long getIdGeneric(String params) {
        T obj = uriParamToObject(params);
        long id = 0;

        Method invokeMethod = null;
        try {
            invokeMethod = obj.getClass().getMethod("getId");
            id = (long) invokeMethod.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return id;
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

    public HttpResponse view(String params) {
        String returnMsg = null;
        if(isValidJson(params)){

        }
        if (params != null && params.length() != 0 && params.equalsIgnoreCase("all")) {
            List<T> objects = objBox.getAll();
            returnMsg = gson.toJson(objects);
        } else {
            long id = getIdGeneric(params);
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

        id = getIdGeneric(params);
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
        T obj = uriParamToObject(params);
        T objFromDB = null;

        id = getIdGeneric(params);

        if (id != 0) {
            id = getIdGeneric(params);
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
