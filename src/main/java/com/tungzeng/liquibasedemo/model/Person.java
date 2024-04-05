package com.tungzeng.liquibasedemo.model;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @Column
    private String height;

    @Column
    public String address;

    public Person() {
    }

    public Person(int id, String name, String height, String address) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.address = address;
    }

    public Person(String name, String height, String address) {
        this.name = name;
        this.height = height;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
