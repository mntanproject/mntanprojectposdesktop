package com.mntanproject.pos.customer;

import com.mntanproject.pos.database.GenericApiDao;
import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

public class CustomerApi extends GenericApiDao<Customer> {


    public CustomerApi() {
        super();
        System.out.println("CustomerApi called");

    }
}
