package com.mntanproject.pos.customer;

import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.supplier.Supplier;

import com.mntanproject.pos.supplier.Supplier_;
import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;

import java.lang.reflect.Field;

public class TestCustomer {


    public static void main(String[] args) {
        ObjectBoxDB.init();
        Box<Supplier> supplierBox = ObjectBoxDB.get().boxFor(Supplier.class);
        //Property<Supplier> supplierProperty = new Property<Supplier>(;
        //supplierProperty.id = 1;


        //Supplier supplierQ = supplierBox.query().equal(UserProperties.Role, 2).build().findFirst();

    }
}