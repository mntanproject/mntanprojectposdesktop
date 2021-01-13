package com.mntanproject.pos.database;

import com.google.gson.Gson;
import io.objectbox.Property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Util<T> {

    private Class<T> type;
    private Gson gson = new Gson();

    public Util(Class<T> type) {
        System.out.println("Database util created" + type);
        this.type = type;
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

    public T uriParamToObject(String param) {

        System.out.println("param: " + param);
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
        System.out.println("uriParamToObject:" + json);



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
    public boolean isValidJson(String params) {
        boolean valid = false;
        System.out.println("checking is valid json");
        try {
            T obj = gson.fromJson(params, type);
            System.out.println("object: " + obj);
            System.out.println("json: " + objectToJson(obj));
            valid = true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return valid;
    }
    public long getIdGeneric(String params) {
        T obj = generateObject(params);
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

    public T generateObject(String params){
        T obj = null;
        if(isValidJson(params)){
            System.out.println("generating object from json: " +params);
            obj = jsonToObject(params);
        } else {
            obj = uriParamToObject(params);
        }
        return obj;
    }










}
