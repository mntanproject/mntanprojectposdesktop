package com.mntanproject.pos.sales;

import com.mntanproject.pos.customer.Customer;
import com.mntanproject.pos.database.GenericApi;
import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.item.Item;
import io.objectbox.Box;

public class SalesApi extends GenericApi<Sales> {

    private static final Class<?> generatedClass = Sales_.class;
    Box<Item> itemBox = ObjectBoxDB.get().boxFor(Item.class);
    Box<Customer> customerBox = ObjectBoxDB.get().boxFor(Customer.class);

    public SalesApi() {
        super(generatedClass);
    }

}
