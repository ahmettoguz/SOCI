package com.soci.soci.Model;

import java.util.ArrayList;

public class Person {
    private int id;
    private String name, surname, email, password, phone;
    private ArrayList<Integer> participated_Events;
    private ArrayList<Integer> created_Events;

    // constructor
    public Person(int id, String name, String surname, String email, String password, String phone, ArrayList<Integer> participated_Events, ArrayList<Integer> created_Events) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.participated_Events = participated_Events;
        this.created_Events = created_Events;
    }

    public Person(String name, String surname, String email, String password, String phone, ArrayList<Integer> participated_Events, ArrayList<Integer> created_Events) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.participated_Events = participated_Events;
        this.created_Events = created_Events;
    }
    // constructor end

    // toString


    @Override
    public String toString() {
        return "Person:\n" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", participated_Events=" + participated_Events +
                ", created_Events=" + created_Events +
                "\n";
    }

    // getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Integer> getParticipated_Events() {
        return participated_Events;
    }

    public ArrayList<Integer> getCreated_Events() {
        return created_Events;
    }
    // getter end
}
