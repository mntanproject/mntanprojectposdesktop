package com.mntanproject.pos.customer;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Customer {

    @Id public long id;
    public String name,company;
}
