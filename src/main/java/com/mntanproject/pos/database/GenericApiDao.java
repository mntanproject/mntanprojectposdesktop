package com.mntanproject.pos.database;

import com.google.gson.Gson;
import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
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

    public GenericApiDao(Class<?> generatedClass)  {
        //this.type = type;

        this.gson = new Gson();
        this.type =  (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.objBox = ObjectBoxDB.get().boxFor(type);
        this.generatedClass = generatedClass;

        System.out.println("Type: " + type);
        //System.exit(1);
    }

    public void printsomething(String params){
        System.out.println("Printsomething: " + params);
    }
    public T toObject(String param) {
        T returnedObject = null;
        String[] splits = param.split("&");
        HashMap<String, String> mappedParam = new HashMap<String, String>();

        for (String string : splits) {
            String[] keyValue = null;
            keyValue = string.split("=");
            if (keyValue.length == 2) {
                mappedParam.put(keyValue[0], keyValue[1]);
            }
        }
        String json = gson.toJson(mappedParam);
        returnedObject = gson.fromJson(json, type);


        return returnedObject;
    }

    public HttpResponse add(String params) {
        System.out.println("Generic add called");
        T obj = toObject(params);
        String paramJson = gson.toJson(obj);
        objBox.put(obj);
        HttpResponse response = new HttpResponse(StatusCode.OK, ContentType.JSON, paramJson);
        return response;
    }

    public long getIdGeneric(String params){
        T obj = toObject(params);
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

    public Property getObjectBoxGeneratedClassProperty(String fieldName){
        //Class<?> generatedClassInstance = null;
        Property<T> property = null;
        try {
            //generatedClassInstance = generatedClass.newInstance();
            Field idField = generatedClass.getField(fieldName);
            property = (Property<T>) idField.get(generatedClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return property;
    }

   public HttpResponse view(String params){
        String returnMsg = null;
        if (params != null && params.length() !=0 && params.equalsIgnoreCase("all")){
            List<T> objects = objBox.getAll();
            returnMsg = gson.toJson(objects);
        }else {
            long id = getIdGeneric(params);
            Property<T> property = getObjectBoxGeneratedClassProperty("id");
            T objQ = null;
            objQ = objBox.query().equal(property,id).build().findFirst();
            returnMsg = gson.toJson(objQ);
        }
        HttpResponse response  = new HttpResponse(StatusCode.OK, ContentType.JSON,returnMsg);
        return response;
    }

    public HttpResponse delete(String params){
        long id = 0;
        String returnMsg;

        id = getIdGeneric(params);
        boolean removed = false;
        removed = objBox.remove(id);
        if(removed){
            returnMsg = "id: " + id + " is deleted";
        } else {
            returnMsg = "id: " + id + " delete failed";
        }

        HttpResponse response  = new HttpResponse(StatusCode.OK, ContentType.JSON,gson.toJson(returnMsg));
        return response;
    }
    public HttpResponse edit(String params){
        long id = 0;
        String returnMsg = null;
        HttpResponse response  = null;
        boolean edited = false;
        T obj = toObject(params);
        T objFromDB = null;

        id = getIdGeneric(params);

        if (id != 0){
            id = getIdGeneric(params);
            Property<T> property = getObjectBoxGeneratedClassProperty("id");
            objFromDB = objBox.query().equal(property,id).build().findFirst();
            objFromDB = obj;
            objBox.put(objFromDB);
            returnMsg = gson.toJson(objFromDB);
        }


        response  = new HttpResponse(StatusCode.OK, ContentType.JSON,gson.toJson(returnMsg));

        return response;
    }


}
