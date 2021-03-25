package com.mntanproject.pos.purchase;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.mntanproject.pos.item.Item;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Purchase {

    @Id
    public long id;
    public float total;
    public Date createdOn;
    public Date lastEditedOn;
    public long supplierId;
    @Convert(converter = PurchaseCustomConverter.class, dbType = String.class)
    public ArrayList<Item> items;


    public Purchase() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastEditedOn() {
        return lastEditedOn;
    }

    public void setLastEditedOn(Date lastEditedOn) {
        this.lastEditedOn = lastEditedOn;
    }

    @Override
    public String toString() {

        return "Purchase{" +
                "id=" + id +
                ", total=" + total +
                ", createdOn=" + createdOn +
                ", lastEditedOn=" + lastEditedOn +
                ", supplierId=" + supplierId +
                ", items=" + items +
                '}';
    }

    public static class PurchaseCustomConverter implements PropertyConverter<ArrayList<Item>, String> {

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
