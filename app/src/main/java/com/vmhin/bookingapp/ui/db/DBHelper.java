package com.vmhin.bookingapp.ui.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vmhin.bookingapp.ui.models.BookingDetails;
import com.vmhin.bookingapp.ui.models.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TripLogger.db";

    public static final String USERS_TABLE_NAME = "users";
    public static final String TRIP_TABLE_NAME = "trip";

    //Name, ID, email, gender, comment
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_GENDER = "gender";
    public static final String USERS_COLUMN_COMMENT = "comment";
    public static final String USERS_COLUMN_USERID = "userid";


    //Title, Date, Trip Type, Destination, Duration, Comment, Photo
    public static final String TRIPS_COLUMN_TRIP_ID = "id";
    public static final String TRIPS_COLUMN_TITLE= "title";
    public static final String TRIPS_COLUMN_DATE = "date";
    public static final String TRIPS_COLUMN_TRIPTYPE = "triptype";
    public static final String TRIPS_COLUMN_DESTINATION = "destination";
    public static final String TRIPS_COLUMN_DURATION = "duration";
    public static final String TRIPS_COLUMN_COMMENTTRIP = "commenttrip";
    public static final String TRIPS_COLUMN_PHOTO = "photo";
    public static final String TRIPS_COLUMN_LATITUDE = "latitude";
    public static final String TRIPS_COLUMN_LONGITUDE = "longitude";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " +USERS_TABLE_NAME+
                        "(id integer primary key, name text,email text, gender text,comment text,userid text)"
        );
        db.execSQL(
                "create table " +TRIP_TABLE_NAME+
                        "(id integer primary key, title text,date text, triptype text,destination text" +
                        ",duration text,commenttrip text,photo text,latitude text,longitude text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TRIP_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser (String name,String email, String gender,String comment, String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_EMAIL, email);
        contentValues.put(USERS_COLUMN_GENDER, gender);
        contentValues.put(USERS_COLUMN_COMMENT, comment);
        contentValues.put(USERS_COLUMN_USERID, userid);
        db.insert(USERS_TABLE_NAME, null, contentValues);
        return true;
    }


    public boolean insertTrip (String title,String date, String triptype,String destination,String duration,String commenttrip,String photo, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRIPS_COLUMN_TITLE, title);
        contentValues.put(TRIPS_COLUMN_DATE, date);
        contentValues.put(TRIPS_COLUMN_TRIPTYPE, triptype);
        contentValues.put(TRIPS_COLUMN_DESTINATION, destination);
        contentValues.put(TRIPS_COLUMN_DURATION, duration);
        contentValues.put(TRIPS_COLUMN_COMMENTTRIP, commenttrip);
        contentValues.put(TRIPS_COLUMN_PHOTO, photo);
        contentValues.put(TRIPS_COLUMN_LATITUDE, latitude);
        contentValues.put(TRIPS_COLUMN_LONGITUDE, longitude);
        db.insert(TRIP_TABLE_NAME, null, contentValues);
        return true;
    }



    public Cursor getDataUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from"+ USERS_TABLE_NAME +"where id="+id+"", null );
        return res;
    }
    public Cursor getDataTrip(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from"+ TRIP_TABLE_NAME +"where id="+id+"", null ); return res;
    }

    public int numberOfRowsUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }
    public int numberOfRowsTrip(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TRIP_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser (Integer id,String name,String email, String gender,String comment,String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_EMAIL, email);
        contentValues.put(USERS_COLUMN_GENDER, gender);
        contentValues.put(USERS_COLUMN_COMMENT, comment);
        contentValues.put(USERS_COLUMN_USERID, userid);
        db.update(USERS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public boolean updateTrip (Integer id,String title,String date, String triptype,String destination,String duration,String commenttrip,String photo, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRIPS_COLUMN_TITLE, title);
        contentValues.put(TRIPS_COLUMN_DATE, date);
        contentValues.put(TRIPS_COLUMN_TRIPTYPE, triptype);
        contentValues.put(TRIPS_COLUMN_DESTINATION, destination);
        contentValues.put(TRIPS_COLUMN_DURATION, duration);
        contentValues.put(TRIPS_COLUMN_COMMENTTRIP, commenttrip);
        contentValues.put(TRIPS_COLUMN_PHOTO, photo);
        contentValues.put(TRIPS_COLUMN_LATITUDE, latitude);
        contentValues.put(TRIPS_COLUMN_LONGITUDE, longitude);
        db.update(TRIP_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public Integer deleteUser (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USERS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteTrip (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TRIP_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<UserDetails> getAllUsers() {
        ArrayList<UserDetails> array_list = new ArrayList<UserDetails>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from "+USERS_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            UserDetails userModel = new UserDetails();
            userModel.setName(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
            userModel.setEmail(res.getString(res.getColumnIndex(USERS_COLUMN_EMAIL)));
            userModel.setGender(res.getString(res.getColumnIndex(USERS_COLUMN_GENDER)));
            userModel.setComment(res.getString(res.getColumnIndex(USERS_COLUMN_COMMENT)));
            userModel.setUserID(res.getString(res.getColumnIndex(USERS_COLUMN_USERID)));
            userModel.setId(res.getString(res.getColumnIndex(USERS_COLUMN_ID)));
            array_list.add(userModel);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<BookingDetails> getAllTrips() {
        ArrayList<BookingDetails> array_list = new ArrayList<BookingDetails>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from "+TRIP_TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Log.e("getAllTrips","getAllTrips");
            BookingDetails tripModel = new BookingDetails();
            tripModel.setId(res.getString(res.getColumnIndex(TRIPS_COLUMN_TRIP_ID)));
            Log.e("TRIPS_COLUMN_TRIP_ID",""+res.getString(res.getColumnIndex(TRIPS_COLUMN_TRIP_ID)));
            tripModel.setTitle(res.getString(res.getColumnIndex(TRIPS_COLUMN_TITLE)));
            tripModel.setDate(res.getString(res.getColumnIndex(TRIPS_COLUMN_DATE)));
            tripModel.setTripType(res.getString(res.getColumnIndex(TRIPS_COLUMN_TRIPTYPE)));
            tripModel.setDestination(res.getString(res.getColumnIndex(TRIPS_COLUMN_DESTINATION)));
            tripModel.setDuration(res.getString(res.getColumnIndex(TRIPS_COLUMN_DURATION)));
            tripModel.setComment(res.getString(res.getColumnIndex(TRIPS_COLUMN_COMMENTTRIP)));
            tripModel.setPhoto(res.getString(res.getColumnIndex(TRIPS_COLUMN_PHOTO)));
            tripModel.setLatitude(res.getString(res.getColumnIndex(TRIPS_COLUMN_LATITUDE)));
            tripModel.setLongitude(res.getString(res.getColumnIndex(TRIPS_COLUMN_LONGITUDE)));
            array_list.add(tripModel);
            res.moveToNext();
        }
        return array_list;
    }

}
