package a3101020604.airline_projecta3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by justinfrasca on 2017-11-18.
 */

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private String password;
    public static final String DATABASE_User = "CustomerDatabase.db";
    public static final String TABLE_USER = "EmployeeDoc113";
    public static final String KEY_PASSWORD = "Password";
    private static final String COL1 = "ID";

    private SQLiteDatabase db;

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_User, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER + " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, FullName TEXT, Address TEXT, PhoneNum TEXT, Depart TEXT, Destionation TEXT, Cost INTEGER , Flights TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public boolean updateData(String Username, String Password, String FullName, String Address, String PhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);
        contentValues.put("FullName", FullName);
        contentValues.put("Address", Address);
        contentValues.put("PhoneNum", PhoneNum);
        db.update(TABLE_USER, contentValues, "Username = ?",new String[] { Username });
        return true;
    }


    public boolean updateData2(String Username, String Depart, String Destionation , String Cost, String Flights) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Destionation", Destionation);
        contentValues.put("Depart", Depart);
        contentValues.put("Cost", Cost);
        contentValues.put("Flights", Flights);


        db.update(TABLE_USER, contentValues, "Username = ?",new String[] { Username });
        return true;
    }


    public boolean insertData(String Username, String Password, String FullName, String Address, String PhoneNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Password ", Password);
        contentValues.put("FullName", FullName);
        contentValues.put("Address", Address);
        contentValues.put("PhoneNum", PhoneNum);


        long result = db.insert(TABLE_USER, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }







    String getregister(String Username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, "Username=?", new String[]{Username}, null, null, null, null);

        if (cursor.getCount() < 1) {
            cursor.close();
            return "Not Exist";
        } else if (cursor.getCount() >= 1 && cursor.moveToFirst()) {

            password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            cursor.close();

        }
        return password;


    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor PatientRes = db.rawQuery("select * from " + TABLE_USER+ " WHERE Username = Username LIMIT 1 ", null);
        return PatientRes;
    }



}
