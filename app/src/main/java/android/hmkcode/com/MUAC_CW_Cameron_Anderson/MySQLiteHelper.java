package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cammy on 03/12/16.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "usersDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create user table
        String CREATE_User_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, "+
                "password TEXT )";

        // create users table
        db.execSQL(CREATE_User_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older Users users if existed
        db.execSQL("DROP TABLE IF EXISTS users");

        // create fresh users table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) User + get all Users + delete all Users
     */

    // Users table name
    private static final String TABLE_UserS = "users";

    // Users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String[] COLUMNS = {KEY_ID,KEY_USERNAME,KEY_PASSWORD};

    public void addUser(User User){
        Log.d("addUser", User.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, User.getusername()); // get title
        values.put(KEY_PASSWORD, User.getpassword()); // get author

        // 3. insert
        db.insert(TABLE_UserS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public User getUser(String userName){
        Log.d("get user", userName);
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_UserS, // a. table
                        COLUMNS, // b. column names
                        " username = ?", // c. selections
                        new String[] { userName}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit


        User User = null;
        // 3. if we got results get the first one
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // 4. build User object
            User = new User();
            User.setId(Integer.parseInt(cursor.getString(0)));
            User.setusername(cursor.getString(1));
            User.setpassword(cursor.getString(2));
            Log.d("getUser(" + userName + ")", User.toString());
        }
        // 5. return User
        return User;
    }

    // Get All Users
    public List<User> getAllUsers() {
        List<User> Users = new LinkedList<User>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_UserS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build User and add it to list
        User User = null;
        if (cursor.moveToFirst()) {
            do {
                User = new User();
                User.setId(Integer.parseInt(cursor.getString(0)));
                User.setusername(cursor.getString(1));
                User.setpassword(cursor.getString(2));

                // Add User to Users
                Users.add(User);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", Users.toString());

        // return Users
        return Users;
    }

    // Updating single User
    public int updateUser(User User) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", User.getusername()); // get title
        values.put("author", User.getpassword()); // get author

        // 3. updating row
        int i = db.update(TABLE_UserS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(User.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single User
    public void deleteUser(User User) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_UserS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(User.getId()) });

        // 3. close
        db.close();

        Log.d("deleteUser", User.toString());

    }

    public Boolean getUsername(String userName, String passWord) throws SQLiteException
    {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // build query
        Cursor mCursor = db.rawQuery(  "SELECT username, password FROM users WHERE username='"+userName+"' AND password='"+passWord+"'",null);
        if (mCursor !=null)
        {
            // if user exists return true
            if (mCursor.getCount() > 0)
            {
                return true;
            }
            else // no user in db
                return false;
        }
        return false;
    }
}
