package com.example.week5day2contentproviders;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class StudentProviderContract {
    public static  final String CONTENT_AUTHORITY = "com.example.week5day2contentproviders";
    public static  final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //public static final Uri CONTENT_PROVIDER_URI = Uri.parse("content://" + CONTENT_AUTHORITY + "/students");

    public static final String PATH_STUDENT = "student_gpa_table";
    public static  final String PATH_NAME = "name";
    public static  final String PATH_GPA = "gpa";
    public static  final String PATH_PHONE = "phone";

    public  static final class StudentEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NAME).build();

        public static  final String CONTENT_TYPE = "vnd.android.cursor.dir/"
                + CONTENT_URI + "/" + PATH_NAME;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/"
                        + CONTENT_URI + "/" + PATH_NAME;

        public  static final String TABLE_NAME = "student_gpa_table";
        public  static final String FIELD_NAME = "name";
        public  static final String FIELD_GPA = "gpa";
        public  static final String FIELD_PHONE = "phone";

        public static Uri buildStudentUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }




}
