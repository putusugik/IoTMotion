package com.androidhive.androidsqlite;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// Contacts table name
	private static final String TABLE_TIME = "times";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TIME1 = "waktu1";
	private static final String KEY_TIME2 = "waktu2";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TIME_TABLES = "CREATE TABLE " + TABLE_TIME + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME1 + " TIME,"
				+ KEY_TIME2 + " TIME" + ")";
		db.execSQL(CREATE_TIME_TABLES);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addTime(TimeSchedule timeSchedulect) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TIME1, timeSchedulect.get_waktu1()); // Contact Name
		values.put(KEY_TIME2, timeSchedulect.get_waktu2()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_TIME, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	TimeSchedule getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_TIME, new String[] { KEY_ID,
						KEY_TIME1, KEY_TIME2}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		TimeSchedule timeSchedule = new TimeSchedule(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return contact
		return timeSchedule;
	}
	
	// Getting All Contacts
	public List<TimeSchedule> getAllTimeSchedule() {
		List<TimeSchedule> timeList = new ArrayList<TimeSchedule>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TIME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				TimeSchedule timeSchedule = new TimeSchedule();
				timeSchedule.setID(Integer.parseInt(cursor.getString(0)));
				timeSchedule.set_waktu1(cursor.getString(1));
				timeSchedule.set_waktu2(cursor.getString(2));
				// Adding contact to list
				timeList.add(timeSchedule);
			} while (cursor.moveToNext());
		}

		// return contact list
		return timeList;

	}

	// Updating single contact
	public int updateTimeSchedule(TimeSchedule timeSchedule) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TIME1, timeSchedule.get_waktu1());
		values.put(KEY_TIME2, timeSchedule.get_waktu2());

		// updating row
		return db.update(TABLE_TIME, values, KEY_ID + " = ?",
				new String[] { String.valueOf(timeSchedule.getID()) });
	}

	// Deleting single contact
	public void deleteTimeSchedule(TimeSchedule timeSchedule) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TIME, KEY_ID + " = ?",
				new String[] { String.valueOf(timeSchedule.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_TIME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
