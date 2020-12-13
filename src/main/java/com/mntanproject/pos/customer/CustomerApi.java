package com.mntanproject.pos.customer;

import com.mntanproject.pos.database.GenericApi;

public class CustomerApi extends GenericApi<Customer> {


    private static final Class<?> generatedCustomer = Customer_.class;

    public CustomerApi() {
        super(generatedCustomer);
        System.out.println("CustomerApi called");

    }
}
