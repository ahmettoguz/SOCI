package com.soci.soci.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.soci.soci.Business.MainSys;

import java.util.ArrayList;

public class Event_Table {
    public static String TABLE_NAME = "Event";
    public static String FIELD_ID = "Id";
    public static String FIELD_MAX_PARTICIPANT = "Max_Participant";
    public static String FIELD_NAME = "Name";
    public static String FIELD_CATEGORY = "Category";
    public static String FIELD_DESCRIPTION = "Description";
    public static String FIELD_LOCATION = "Location";
    public static String FIELD_START_DATE = "Start_Date";
    public static String FIELD_END_DATE = "End_Date";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_ID + " integer, " + FIELD_MAX_PARTICIPANT + " integer, " + FIELD_NAME + " text, " + FIELD_CATEGORY + " text, " + FIELD_DESCRIPTION + " text, " + FIELD_LOCATION + " text, " + FIELD_START_DATE + " text, " + FIELD_END_DATE + " text)";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static int insert(DatabaseHelper dbHelper, int max_Participant, String name, String category, String description, String location, String start_Date, String end_Date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_MAX_PARTICIPANT, max_Participant);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_CATEGORY, category);
        contentValues.put(FIELD_DESCRIPTION, description);
        contentValues.put(FIELD_LOCATION, location);
        contentValues.put(FIELD_START_DATE, start_Date);
        contentValues.put(FIELD_END_DATE, end_Date);


        long insertedIdLong = dbHelper.insert(TABLE_NAME, contentValues);
        int insertedIdInt = (int) insertedIdLong;
        return insertedIdInt;
    }

    // Read all
    public static ArrayList<Event_Helper> getAll(DatabaseHelper dbHelper) {
        Event_Helper anItem;
        ArrayList<Event_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int max_Participant = cursor.getInt(1);
            String name = cursor.getString(2);
            String category = cursor.getString(3);
            String description = cursor.getString(4);
            String location = cursor.getString(5);
            String start_Date = cursor.getString(6);
            String end_Date = cursor.getString(7);

            anItem = new Event_Helper(id, max_Participant, name, category, description, location, start_Date, end_Date);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    // Delete
    public static int delete(DatabaseHelper dbHelper, int id) {
        String where = FIELD_ID + " = " + id;

        // returning affected row count. 0 returns if no record deleted
        int deletedRowCount = dbHelper.delete(TABLE_NAME, where);
        return deletedRowCount;
    }
}

