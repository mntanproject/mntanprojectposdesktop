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

    public Supplier( String company, String name, String contactno, String email, String street, String city, String state, String country, String bank, String notes) {
        this.company = company;
        this.name = name;
        this.contactno = contactno;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.bank = bank;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", contactno='" + contactno + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", bank='" + bank + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
