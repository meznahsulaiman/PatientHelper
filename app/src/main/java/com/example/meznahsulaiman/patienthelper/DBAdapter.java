package com.example.meznahsulaiman.patienthelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {
    public static final String KEY_ROWID = "id";
    public static final String KEY_DRUG = "DrugName";
    public static final String KEY_DOSENUMBER = "DoseNumber";
    public static final String KEY_STARTDRUG = "StartDrug";
    public static final String KEY_DRUGPERIOD = "DrugPeriodInDays";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "AppointmentDB";
    private static final String DATABASE_TABLE = "appointments";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table if not exists appointments (id integer primary key autoincrement,"
                    +"DrugName VARCHAR not null, DoseNumber integer, " +
                    "StartDrug integer, DrugPeriodInDays integer);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a record into the database---
    public long insertRecord(String DrugName, String DoseNumber, String StartDrug, String DrugPeriodInDays)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DRUG,DrugName );
        initialValues.put(KEY_DOSENUMBER, DoseNumber);
        initialValues.put(KEY_STARTDRUG, StartDrug);
        initialValues.put(KEY_DRUGPERIOD, DrugPeriodInDays);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_DRUG,
                KEY_DOSENUMBER, KEY_STARTDRUG, KEY_DRUGPERIOD}, null, null, null, null,null);
    }

    //---retrieves a particular record---
    public Cursor getRecord(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_DRUG, KEY_DOSENUMBER, KEY_STARTDRUG, KEY_DRUGPERIOD},
                        KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long rowId, String title, String duedate, String course, String notes)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_DRUG, title);
        args.put(KEY_DOSENUMBER, duedate);
        args.put(KEY_STARTDRUG, course);
        args.put(KEY_DRUGPERIOD, notes);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}

