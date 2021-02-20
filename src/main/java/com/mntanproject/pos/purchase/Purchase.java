package com.mntanproject.pos.purchase;

import com.mntanproject.pos.item.Item;
import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import java.util.Date;

@Entity
public class Purchase {

@Id
public long id;
public float total;
public Date createdOn;

@Backlink
public ToMany<Item> items;


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

    public ToMany<Item> getItems() {
        return items;
    }

    public void setItems(ToMany<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", total=" + total +
                ", createdOn=" + createdOn +
                ", items=" + items +
                '}';
    }
}
