package com.mntanproject.pos.item;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Item {

    @Id
    public long id;
    public String name, barcode, notes;
    public float quantityOnHand, purchasePrice, sellingPrice;
    public boolean deleted, salesHappened;

    public Item() {
    }

    public Item(String name, String barcode, String notes, float quantityOnHand, float purchasePrice, float sellingPrice) {
        this.name = name;
        this.barcode = barcode;
        this.notes = notes;
        this.quantityOnHand = quantityOnHand;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(float quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isSalesHappened() {
        return salesHappened;
    }

    public void setSalesHappened(boolean salesHappened) {
        this.salesHappened = salesHappened;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", barcode='" + barcode + '\'' +
                ", notes='" + notes + '\'' +
                ", quantityOnHand=" + quantityOnHand +
                ", purchasePrice=" + purchasePrice +
                ", sellingPrice=" + sellingPrice +
                ", deleted=" + deleted +
                ", salesHappened=" + salesHappened +
                '}';
    }

}
