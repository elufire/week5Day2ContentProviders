package com.example.week5day2contentproviders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student_db";
    public static final int DATABASE_VERSION = 98;
    public static final String TABLE_NAME = "student_gpa_table";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_GPA = "gpa";
    public static final String FIELD_PHONE = "phone";

    public StudentDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_NAME + " TEXT PRIMARY KEY, "
                + FIELD_GPA + " TEXT, "
                + FIELD_PHONE + " TEXT)";
        Log.d("TAG", "onCreate: ");
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<Student> getAllStudents(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            ArrayList<Student> arrayList = new ArrayList<>();
            do{
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA));
                String phone = cursor.getString(cursor.getColumnIndex(FIELD_PHONE));
                arrayList.add(new Student(name, gpa, phone));
            }while(cursor.moveToNext());
            return arrayList;
        }else{
            return null;
        }
    }

    public  Student getSingleSTudentByNAme(String name){
        Student returnStudent = null;
        if(name != null && !name.isEmpty()){
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                    FIELD_NAME + " = \"" +name + "\"";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if(cursor.moveToFirst()){
                String tempName = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA));
                String phone = cursor.getString(cursor.getColumnIndex(FIELD_PHONE));
                returnStudent = new Student(tempName, gpa, phone);
            }
            cursor.close();
        }

        return returnStudent;
    }

    public  void insertNewStudent(Student passedStudent){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        if(passedStudent != null){
            contentValues.put(FIELD_NAME, passedStudent.getStudentName());
            contentValues.put(FIELD_GPA, passedStudent.getStudentGPA());
            contentValues.put(FIELD_PHONE, passedStudent.getStudentPhone());
            database.insert(TABLE_NAME, null, contentValues);
        }
    }

    public int deleteStudent(Student passedStudent){
        String whereClause = FIELD_NAME + "=" + "\"" +  passedStudent.getStudentName() +"\"";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause , null);
    }

    public int updateStudent(Student passedStudent){
        if(passedStudent != null){
            String whereClause = FIELD_NAME + " = \"" + passedStudent.getStudentName() + "\"";
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FIELD_NAME, passedStudent.getStudentName());
            contentValues.put(FIELD_GPA, passedStudent.getStudentGPA());
            contentValues.put(FIELD_PHONE, passedStudent.getStudentPhone());
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause , null);
        }
        return 0;
    }

}
