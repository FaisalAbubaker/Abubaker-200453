package alfaisal.firebase.abubaker_200453;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Uni.db";
    public static final String TABLE_NAME = "Students";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SURNAME = "Surname";
    public static final String COLUMN_NATID = "NatID";
    public static final String COLUMN_DOB = "DOB";
    public static final String COLUMN_GENDER = "Gender";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("+ COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT NOT NULL," + COLUMN_SURNAME +" TEXT NOT NULL,"+COLUMN_NATID+" TEXT NOT NULL,"+COLUMN_DOB+" TEXT NOT NULL,"+COLUMN_GENDER+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void AddStudent(String id, String name, String surname,String natID, String dob, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_NATID, natID);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_GENDER, gender);
        db.insert(TABLE_NAME, null, values);

    }
    public Cursor ViewStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return x;
    }
    public Integer DeleteStudents(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public void updateStudent(String ID, String name, String surname,String natID, String dob, String gender) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_NATID, natID);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_GENDER, gender);

        db.update(TABLE_NAME, values, "ID=?", new String[]{ID});
        db.close();
    }

}
