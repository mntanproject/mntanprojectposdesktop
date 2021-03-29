package com.mntanproject.pos.item;

import com.mntanproject.pos.database.GenericApi;
import com.mntanproject.pos.database.GenericRelationApi;
import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

import java.util.HashMap;
import java.util.List;


public class ItemApi extends GenericApi<Item> {

    private static final Class<?> generatedClass = Item_.class;

    public ItemApi() {
        super(generatedClass);
    }

    @Override
    public HttpResponse paginator(String params) {
        String returnMsg = null;
        System.out.println("params: " + params);
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, returnMsg);;
        long limitLong = 0,offsetLong = 0;
        String orderBy , descending = null;
        Property<Item> propertyBarcode = getObjectBoxGeneratedClassProperty("barcode");
        Property<Item> propertyName = getObjectBoxGeneratedClassProperty("name");

        QueryBuilder<Item>  query = objBox.query();

        if (params != null && params.length() != 0 ) {
            String[] splits = params.split("&");
            HashMap<String, String> mappedParam = new HashMap<String, String>();

            for (String string : splits) {
                String[] keyValue = null;
                keyValue = string.split("=");
                System.out.println("key size: " + keyValue.length + "string:"+string);
                if (keyValue.length >= 2) {
                    mappedParam.put(keyValue[0], keyValue[1]);
                }
            }
            for (String str : mappedParam.keySet()){
                System.out.println(str);
            }
            if (mappedParam.containsKey("search")) {
                query.contains(propertyBarcode, mappedParam.get("search"));
                query.or().contains(propertyName, mappedParam.get("search"));
                            }
            if (mappedParam.containsKey("offset") && mappedParam.containsKey("limit")){
                Property<Item> property = getObjectBoxGeneratedClassProperty("id");
                offsetLong = Long.parseLong(mappedParam.get("offset"));
                limitLong = Long.parseLong(mappedParam.get("limit"));

                if(mappedParam.containsKey("order")&&mappedParam.containsKey("descending")) {
                    property = getObjectBoxGeneratedClassProperty(mappedParam.get("order"));
                    descending = mappedParam.get("descending");
                    if (descending != null && descending.equalsIgnoreCase("1")){
                        query.order(property,QueryBuilder.DESCENDING);
                    } else {
                        query.order(property);
                    }
                }else if (mappedParam.containsKey("order")){
                    property = getObjectBoxGeneratedClassProperty(mappedParam.get("order"));
                    query.order(property);
                }else{
                    query.order(property,QueryBuilder.DESCENDING);
                }
                List<Item> objects = query.build().find(offsetLong,limitLong);
                System.out.println("objects size: " + objects.size());
                returnMsg = gson.toJson(objects);
                response = new HttpResponse(StatusCode.OK, ContentType.JSON, returnMsg);
            }
        }
        System.out.println("response:"+response);

        return response;
    }
}
