package com.mntanproject.pos.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.objectbox.Property;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.util.ArrayList;
import java.util.List;

public class GenericRelationApi<T> extends GenericApi<T> {

    Class<?> generatedClass;

    public GenericRelationApi(Class<?> generatedClass) {
        super(generatedClass);
        this.generatedClass = generatedClass;
    }

    public String toJson(Object obj) {
//
//        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON,
//                gson.toJson("Please implement this method"));
//
//        switch (option){
//            case "ToOne":
//                System.out.println("ToOne");
//                break;
//            case "ToMany":
//                System.out.println("ToMany");
//                break;
//            default:
//                System.out.println("DEfault");
//        }
        return null;
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

    //    public void add2(String params) {
//        A obj = util.generateObject(params);
//        String returnMsg = null;
//        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, gson.toJson("Failed to add user"));
//        if(obj != null){
//            //objBox.put(obj);
//            response = new HttpResponse(StatusCode.OK, ContentType.JSON, gson.toJson("Succesfully added"));
//        }
//        System.out.println(obj);
//    }


}
