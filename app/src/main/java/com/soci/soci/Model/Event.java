package com.soci.soci.Model;

import java.util.ArrayList;

public class Event {
    private int id, max_Participant;
    private String name, category, description, location, start_Date, end_Date;

    // constructor
    public Event(int id, int max_Participant, String name, String category, String description, String location, String start_Date, String end_Date) {
        this.id = id;
        this.max_Participant = max_Participant;
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }

    public Event(int max_Participant, String name, String category, String description, String location, String start_Date, String end_Date) {
        this.max_Participant = max_Participant;
        this.name = name;
        this.category = category;
        this.description = description;
        this.location = location;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
    }
    // constructor end

    // toString


    @Override
    public String toString() {
        return "Event:\n" +
                "id=" + id +
                ", max_Participant=" + max_Participant +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", start_Date='" + start_Date + '\'' +
                ", end_Date='" + end_Date + '\'' +
                "\n";
    }

    // getter
    public int getId() {
        return id;
    }

    public int getMax_Participant() {
        return max_Participant;
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
    // getter end
}
