package com.mntanproject.pos.supplier;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Supplier {
    @Id
    public long id;
    public String company,name,contactno,email,street,city,state,country,bank,notes;

    public Supplier() {

    }




}
