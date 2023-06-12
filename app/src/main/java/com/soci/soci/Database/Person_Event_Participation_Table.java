package com.soci.soci.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class Person_Event_Participation_Table {
    public static String TABLE_NAME = "Person_Event_Participation";
    public static String FIELD_ID = "Id";
    public static String FIELD_ID_PERSON = "Person_Id";
    public static String FIELD_ID_EVENT = "Event_Id";


    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_ID + " integer, " + FIELD_ID_PERSON + " integer, " + FIELD_ID_EVENT + " integer)";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static int insert(DatabaseHelper dbHelper, String person_Id, String event_Id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID_PERSON, person_Id);
        contentValues.put(FIELD_ID_EVENT, event_Id);

        long insertedIdLong = dbHelper.insert(TABLE_NAME, contentValues);
        int insertedIdInt = (int) insertedIdLong;
        return insertedIdInt;
    }

    // Read all
    public static ArrayList<Person_Event_Helper> getAll(DatabaseHelper dbHelper) {
        Person_Event_Helper anItem;
        ArrayList<Person_Event_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int person_Id = cursor.getInt(1);
            int event_Id = cursor.getInt(2);

            anItem = new Person_Event_Helper(id, person_Id, event_Id);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    // Delete
    public static int delete(DatabaseHelper dbHelper, int id) {
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = " + id;

        // returning affected row count. 0 returns if no record deleted.
        int deletedRowCount = dbHelper.delete(TABLE_NAME, where);
        return deletedRowCount;
    }
}

