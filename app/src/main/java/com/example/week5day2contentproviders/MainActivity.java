package com.example.week5day2contentproviders;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGpa;
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etGpa = findViewById(R.id.etGPA);
        etPhone = findViewById(R.id.etPhone);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put("name",
                etName.getText().toString());

        values.put("gpa",
                etGpa.getText().toString());

        values.put("phone",
                etPhone.getText().toString());

        Uri uri = getContentResolver().insert(
                StudentContentProvider.CONTENT_URI, values);

        //Uri queryUri = Uri.parse(StudentContentProvider.CONTENT_URI + "/100");

        String[] mSelectionArgs = {""};
        String mSortOrder = "ASC";
        Cursor mCursor;
        mCursor = getContentResolver().query(
                StudentContentProvider.CONTENT_URI,  // The content URI of the words table
                null,                       // The columns to return for each row
                null,                  // Either null, or the word the user entered
                mSelectionArgs,                    // Either empty, or the string the user entered
                mSortOrder);                       // The sort order for the returned rows


// Some providers return null if an error occurs, others throw an exception
        if (null == mCursor) {
            /*
             * Insert code here to handle the error. Be sure not to use the cursor! You may want to
             * call android.util.Log.e() to log this error.
             *
             */
// If the Cursor is empty, the provider found no matches
        } else if (mCursor.getCount() < 1) {

            /*
             * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily
             * an error. You may want to offer the user the option to insert a new row, or re-type the
             * search term.
             */

        } else {

            Log.e("TAG", "Value of Cursor: " + mCursor.toString());

//            do{
//                System.out.println(mCursor.);
//
//            }while (mCursor.moveToNext());
            // Insert code here to do something with the results

        }

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
//    public void onClickRetrieveStudents(View view) {
//        // Retrieve student records
//        String URL = "content://com.example.MyApplication.StudentProviderContract";
//
//        Uri students = Uri.parse(URL);
//        Cursor c = managedQuery(students, null, null, null, "name");
//
//        if (c.moveToFirst()) {
//            do{
//                Toast.makeText(this,
//                        c.getString(c.getColumnIndex(StudentProviderContract._ID)) +
//                                ", " +  c.getString(c.getColumnIndex( StudentProviderContract.NAME)) +
//                                ", " + c.getString(c.getColumnIndex( StudentProviderContract.GRADE)),
//                        Toast.LENGTH_SHORT).show();
//            } while (c.moveToNext());
//        }
//    }
}
