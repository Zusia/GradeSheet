package com.example.a1405178.mycontentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycontentprovider.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onClickAddName(View view) {
// Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.txtName)).getText().toString());
        values.put(StudentsProvider.ADDRESS,

        ((EditText)findViewById(R.id.txtGrade)).getText().toString());
        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
// Retrieve student records
        String URL = "content://com.example.provider.College/students";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.NAME))
                                +
                                ", " + c.getString(c.getColumnIndex(
                                StudentsProvider.ADDRESS)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
