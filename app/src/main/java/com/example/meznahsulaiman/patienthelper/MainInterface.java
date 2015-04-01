package com.example.meznahsulaiman.patienthelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MainInterface extends Activity {
    /** Called when the activity is first created. */

    //DBAdapter db = new DBAdapter(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_interface);

        Button addappoint = (Button)findViewById(R.id.button);
        Button viewappoint = (Button)findViewById(R.id.button2);
        addappoint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainInterface.this,
                        addappointmentActiv.class);
                startActivity(i);
            }
        });

        viewappoint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainInterface.this,
                        Dispaly.class);
                startActivity(i);



            }
        });
        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/databases/AssignmentDB";
            File f = new File(destPath);
            if (!f.exists()) {
                CopyDB( getBaseContext().getAssets().open("mydb"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //DBAdapter db = new DBAdapter(this);


        //---add an assignment---
        /*
65 db.open();
66 long id = db.insertRecord("Hello World", "2/18/2012", "DPR 224", "First
… Android Project");
67 id = db.insertRecord("Workbook Exercises", "3/1/2012", "MAT 100", "Do odd
… numbers");
68 db.close();
69 */

        //---get all Records---
         /*
73 db.open();
74 Cursor c = db.getAllRecords();
75 if (c.moveToFirst())
76 {
77 do {
78 DisplayRecord(c);
79 } while (c.moveToNext());
80 }
81 db.close();
82 */
        /*
85 //---get a Record---
86 db.open();
87 Cursor c = db.getRecord(2);
88 if (c.moveToFirst())
89 DisplayRecord(c);
90 else
91 Toast.makeText(this, "No Assignments found",
… Toast.LENGTH_LONG).show();
92 db.close();
93 */
        //---update Record---
       /*
98 db.open();
99 if (db.updateRecord(1, "Hello Android", "2/19/2012", "DPR 224", "First
… Android Project"))
100 Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
101 else
102 Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
  db.close();
  */

  /*
107 //---delete a Record---
108 db.open();
109 if (db.deleteRecord(1))
110 Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
111 else
112 Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
113 db.close();
114 */
    }


    private class DBAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        //private ArrayList<>

        @Override
        public int getCount() {

            return 0;
        }

        @Override
        public Object getItem(int arg0) {

            return null;
        }

        @Override
        public long getItemId(int arg0) {

            return 0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            return null;
        }
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void DisplayRecord(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Title: " + c.getString(1) + "\n" +
                        "Due Date: " + c.getString(2),
                Toast.LENGTH_SHORT).show();
    }

    public void addAssignment(View view)
    {

        Intent i = new Intent("edu.dccc.addassignment");
        startActivity(i);
        Log.d("TAG", "Clicked");
    }
}
