package com.soci.soci.Database;

public class Event_Helper {

    private int id;
    private int max_Participant;
    private String name;
    private String category;
    private String description;
    private String location;
    private String start_Date;
    private String end_Date;

    public Event_Helper(int id, int max_Participant, String name, String category, String description, String location, String start_Date, String end_Date) {
        this.id = id;
        this.max_Participant = max_Participant;
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax_Participant() {
        return max_Participant;
    }

    public void setMax_Participant(int max_Participant) {
        this.max_Participant = max_Participant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(String start_Date) {
        this.start_Date = start_Date;
    }

    public String getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(String end_Date) {
        this.end_Date = end_Date;
    }
}
