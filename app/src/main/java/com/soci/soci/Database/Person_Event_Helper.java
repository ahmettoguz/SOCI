package com.soci.soci.Database;

public class Person_Event_Helper {

    private int id;
    private int person_Id;
    private int event_Id;

    public Person_Event_Helper(int id, int person_Id, int event_Id) {
        this.id = id;
        this.person_Id = person_Id;
        this.event_Id = event_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_Id() {
        return person_Id;
    }

    public void setPerson_Id(int person_Id) {
        this.person_Id = person_Id;
    }

    public int getEvent_Id() {
        return event_Id;
    }

    public void setEvent_Id(int event_Id) {
        this.event_Id = event_Id;
    }
}
