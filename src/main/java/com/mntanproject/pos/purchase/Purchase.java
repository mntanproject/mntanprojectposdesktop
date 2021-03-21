package com.mntanproject.pos.purchase;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import java.util.Date;

@Entity
public class Purchase {

@Id
public long id;
public float total;
public Date createdOn;
public Date lastEditedOn;
public long supplierId;

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
}
