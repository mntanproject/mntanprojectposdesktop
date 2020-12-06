package com.mntanproject.pos.customer;

import com.mntanproject.pos.database.GenericApiDao;

public class CustomerApi extends GenericApiDao<Customer> {


    private static final Class<?> generatedCustomer = Customer_.class;

    public CustomerApi() {
        super(generatedCustomer);
        System.out.println("CustomerApi called");

    }
}
