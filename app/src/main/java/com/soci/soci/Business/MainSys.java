package com.soci.soci.Business;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainSys {
    public static ArrayList<Person> people = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();

    public static void prepareData() {

        // people
        ArrayList<Integer> participated_Events = new ArrayList<>();
        ArrayList<Integer> created_Events = new ArrayList<>();
        Collections.addAll(created_Events, 1, 2, 12);
        Collections.addAll(participated_Events, 3, 4, 5);
        Person p = new Person(1, "Ahmet Oğuz", "Ergin", "ahmet@hotmail.com", "ahmet123", "05052082324", "Male", participated_Events, created_Events);
        people.add(p);

        participated_Events = new ArrayList<>();
        created_Events = new ArrayList<>();
        Collections.addAll(created_Events, 3, 4, 10, 11);
        Collections.addAll(participated_Events, 1, 2, 5, 12);
        p = new Person(2, "Emre", "Cura", "emre@hotmail.com", "emre123", "05051112233", "Male", participated_Events, created_Events);
        people.add(p);

        participated_Events = new ArrayList<>();
        created_Events = new ArrayList<>();
        Collections.addAll(created_Events, 5, 6);
        Collections.addAll(participated_Events, 1, 2, 3, 4, 10);
        p = new Person(3, "Bertan", "Özer", "bertan@hotmail.com", "bertan123", "05051112233", "Male", participated_Events, created_Events);
        people.add(p);

        participated_Events = new ArrayList<>();
        created_Events = new ArrayList<>();
        Collections.addAll(created_Events, 7, 8);
        Collections.addAll(participated_Events, 1, 2, 12);
        p = new Person(4, "Zeynep", "Ergin", "zeynep@hotmail.com", "zeynep123", "-1", "Female", participated_Events, created_Events);
        people.add(p);

        participated_Events = new ArrayList<>();
        created_Events = new ArrayList<>();
        Collections.addAll(created_Events, 9);
        Collections.addAll(participated_Events, 1, 2);
        p = new Person(5, "Sena", "Ergin", "sena@hotmail.com", "sena123", "05051112233", "Female", participated_Events, created_Events);
        people.add(p);
        // people end


        // events
        Event event = new Event(1, 22, "Football Match", "Sport", "We will play football to prepare next tournament.", "Ankara/Etlik/Antares halısaha", "15/06/2023-17.00", "15/06/2023-19.00");
        events.add(event);

        event = new Event(2, 10, "Taboo", "Table Game", "After last final exam courses end I am planning to play taboo.", "Ankara/Bilkent Ünversitesi/East Garden", "16/06/2023-13.00", "16/06/2023-14.00");
        events.add(event);

        event = new Event(3, 3, "Ayça Özefe Concert", "Concert", "Come Ayça Özefe concert with me.", "Ankara/IF Performance Hall", "09/06/2023-21.00", "09/06/2023-23.00");
        events.add(event);

        event = new Event(4, -1, "Meet with us", "Meet Up", "Lets meet after the finals.", "Ankara/Bilkent/Main Campus garden", "16/06/2023-14.00", "16/06/2023-19.00");
        events.add(event);

        event = new Event(5, 6, "Güzelcehisar Camping", "Camping", "For 5 days we will be camping in Güzelcehisar. We will leave ankara 21th of June.", "Bartın/Güzelcehisar", "21/06/2023-08.00", "26/06/2023-08.00");
        events.add(event);

        event = new Event(6, -1, "Fast and Furious 10", "Cinema - Theater", "Come with me to Fast and Furious 10 film.", "Ankara/Kentpark/Prestige", "18/06/2023-19.00", "18/06/2023-21.00");
        events.add(event);

        event = new Event(7, 20, "Başakşehir Football Match Spectator", "Spectator", "Lets suppot Başakşehir.", "Istanbul/Başakşehir Fatih Terim Stadium", "06/06/2023-20.00", "06/06/2023-22.00");
        events.add(event);

        event = new Event(8, -1, "Evening Meal", "Meal", "Lets go to evening meal together.", "Bilkent/Şençam", "20/06/2023-19.00", "20/06/2023-21.00");
        events.add(event);

        event = new Event(9, 20, "Volunteering Project Conference", "Conference", "Volunteering project meet up. Zoom link will be provided.", "Ankara/Bilkent/Conference Building", "27/06/2023-13.00", "27/06/2023-14.00");
        events.add(event);

        event = new Event(10, 12, "Towards A Life Cycle Sustainability Framework", "Conference", "We will talk about how we can build a better life cycle environment.", "FF Building, FFB06 ", "14/06/2023-16.00", "14/06/2023-17.00");
        events.add(event);

        event = new Event(11, 8, "Aikido Course", "Sport", "Learn how to defend yourself.", "Dormitories Sport Hall", "19/06/2023-18.00", "19/06/2023-19.00");
        events.add(event);

        event = new Event(12, 20, "Travel to Cappadocia", "Sightseeing", "Let's discover Cappadocia together!", "In front of the C building", "30/06/2023-06.00", "30/06/2023-22.00");
        events.add(event);
        // events end
    }

    public static void msg(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean containsNonDigit(String digits) {
        for (int i = 0; i < digits.length(); i++) {
            if (!Character.isDigit(digits.charAt(i))) {
                return true; // Non-digit character found
            }
        }
        return false; // Only digit characters found
    }

    public static Person getPersonById(int id) {
        for (Person p : people) {
            if (p.getId() == id)
                return p;
        }

        return null;
    }

    public static String getImgNameFromCategory(String category) {
        if (category.equalsIgnoreCase("Sport"))
            return "category_sport";
        else if (category.equalsIgnoreCase("Table Game"))
            return "category_tablegames";
        else if (category.equalsIgnoreCase("Concert"))
            return "category_concert";
        else if (category.equalsIgnoreCase("Meet Up"))
            return "category_meetup";
        else if (category.equalsIgnoreCase("Camping"))
            return "category_camping";
        else if (category.equalsIgnoreCase("Cinema - Theater"))
            return "category_cinematheater";
        else if (category.equalsIgnoreCase("Spectator"))
            return "category_spectator";
        else if (category.equalsIgnoreCase("Meal"))
            return "category_meal";
        else if (category.equalsIgnoreCase("Sightseeing"))
            return "category_sightseeing";
        else if (category.equalsIgnoreCase("Conference"))
            return "category_conference";
        return null;
    }

    public static int convertImageNameToId(Context ctx, String imgName) {
        return ctx.getResources().getIdentifier(imgName, "drawable",
                ctx.getPackageName());
    }

    public static ArrayList<Event> getEventsAsArrayListFromCategory(String category) {
        ArrayList<Event> returnEvents = new ArrayList<>();
        for (Event e : events) {
            if (category.equalsIgnoreCase("all") || e.getCategory().equalsIgnoreCase(category))
                returnEvents.add(e);
        }
        return returnEvents;
    }

    public static ArrayList<Event> getEventsAsArrayListFromParticipation(String participation, Person person) {
        ArrayList<Event> returnEvents = new ArrayList<>();

        // add participated events
        if (participation.equalsIgnoreCase("all") || participation.equalsIgnoreCase("Participated Events")) {
            for (Integer event_id : person.getParticipated_Events()) {
                returnEvents.add(getEventFromId(event_id));
            }
        }

        // add owner events
        if (participation.equalsIgnoreCase("all") || participation.equalsIgnoreCase("Owned Events")) {
            for (Integer event_id : person.getCreated_Events()) {
                returnEvents.add(getEventFromId(event_id));
            }
        }

        return returnEvents;
    }

    public static Event getEventFromId(int id) {
        for (Event event : events) {
            if (event.getId() == id)
                return event;
        }
        return null;
    }

    public static int fahrenheitToCelsius(int fahrenheit) {
        return (int) Math.round((fahrenheit - 32) / 1.8);
    }

    public static void makePhoneCall(Activity activity, String phoneNumber) {
        final int REQUEST_CALL_PERMISSION = 1;
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            Uri uri = Uri.parse("tel:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }

    public static void playSound(Context ctx, String type) {
        if (type.equalsIgnoreCase("positive")) {
            final MediaPlayer positiveSound = MediaPlayer.create(ctx, R.raw.sound_positive);
            positiveSound.start();
        }
    }
}
