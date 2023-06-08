package com.soci.soci.Model;

import java.util.ArrayList;

public class Event {
    private int id;
    private String name, category, description, location, start_Date, end_Date;
    private ArrayList<Integer> participants;

    // constructor
    public Event(int id, String name, String category, String description, String location, String start_Date, String end_Date, ArrayList<Integer> participants) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.participants = participants;
    }

    public Event(String name, String category, String description, String location, String start_Date, String end_Date, ArrayList<Integer> participants) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.participants = participants;
    }
    // constructor end

    // toString

    @Override
    public String toString() {
        return "Event: \n" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", start_Date='" + start_Date + '\'' +
                ", end_Date='" + end_Date + '\'' +
                ", participants=" + participants +
                "\n";
    }

    // getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStart_Date() {
        return start_Date;
    }

    public String getEnd_Date() {
        return end_Date;
    }

    public ArrayList<Integer> getParticipants() {
        return participants;
    }
    // getter end
}
