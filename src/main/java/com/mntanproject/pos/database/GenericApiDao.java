package com.mntanproject.pos.database;

import com.google.gson.Gson;
import com.mntanproject.pos.customer.Customer;
import com.mntanproject.pos.supplier.Supplier;
import com.mntanproject.pos.supplier.Supplier_;
import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class GenericApiDao<T> {


    private Class<T> type;
    Box<T> objBox;
    QueryBuilder<T> builder;
    Query<T> query;
    Gson gson;
    Property<T> property;

    public GenericApiDao() {
        //this.type = type;

        this.gson = new Gson();
        this.type =  (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.objBox = ObjectBoxDB.get().boxFor(type);

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
//    public Property getObjectBoxGeneratedClass(){
//        String objectBoxGeneratedClass = this.type.getName();
//        Class<?> classRef = null;
//        Property<T> tt = null;
//        try {
//            classRef = Class.forName(objectBoxGeneratedClass + "_");
//            tt = classRef.newInstance().getClass().getDeclaredField("id").;
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
 /*   public HttpResponse view(String params){
        String returnMsg = null;
        if (params != null && params.length() !=0 && params.equalsIgnoreCase("all")){
            List<T> objects = objBox.getAll();
            returnMsg = gson.toJson(objects);
        }else {


            try {
                long id = getIdGeneric(params);
                //Object o = getObjectBoxGeneratedClass().getField("id");
                T objQ = null;
                //objQ = objBox.query().equal(o,id).build().findFirst();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            returnMsg = gson.toJson(objQ);
        }
        HttpResponse response  = new HttpResponse(StatusCode.OK, ContentType.JSON,returnMsg);
        return response;
    }
*/
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
        String returnMsg;
        HttpResponse response  = null;
        boolean edited = false;
        T obj = toObject(params);
        T objFromDB = null;

        id = getIdGeneric(params);

        if (id != 0){

        }



        return response;
    }


}
