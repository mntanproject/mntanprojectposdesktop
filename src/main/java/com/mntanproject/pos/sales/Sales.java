package com.mntanproject.pos.sales;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mntanproject.pos.item.Item;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Sales {

    @Id
    public long id;
    public long customerId;
    public float total;
    public Date date;
    public Date lastModified;

    @Convert(converter = SalesItemsConverter.class,dbType = String.class)
    public ArrayList<Item> items;


    public static class SalesItemsConverter implements PropertyConverter<ArrayList<Item>, String> {

        @Override
        public ArrayList<Item> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }

            //JsonArray jsonArray = new JsonArray();
            return new Gson().fromJson(databaseValue, new TypeToken<ArrayList<Item>>() {
            }.getType());

        }

        @Override
        public String convertToDatabaseValue(ArrayList<Item> entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }


}
