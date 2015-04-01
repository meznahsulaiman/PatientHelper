package com.example.meznahsulaiman.patienthelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by meznahSulaiman on 4/1/15.
 */
public class addappointmentActiv extends Activity {

    public DBAdapter db = new DBAdapter(this);

    EditText drugName;
    EditText doseNumber;
    EditText startDrug;
    EditText drugPeriod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addappointment);


    }
    public void addAppointment(View v)
    {
        Log.d("test", "adding");
        //get data from form
        drugName = (EditText)findViewById(R.id.editText);
        doseNumber = (EditText)findViewById(R.id.editText2);
        startDrug = (EditText)findViewById(R.id.editText3);
        drugPeriod = (EditText)findViewById(R.id.editText4);

        db.open();
        long id = db.insertRecord(drugName.getText().toString(),
                doseNumber.getText().toString(),startDrug.getText().toString(),
                drugPeriod.getText().toString());
        db.close();

        drugName.setText("");
        doseNumber.setText("");
        startDrug.setText("");
        drugPeriod.setText("");
        Toast.makeText(addappointmentActiv.this, "تم إضافة موعدك",
                Toast.LENGTH_LONG).show();

    }

    public Cursor viewAppointment(View v) {


        Intent i1 = new Intent(this, Dispaly.class);

        startActivity(i1);

        db.open();
        Cursor c = db.getAllRecords();
        if (c.moveToFirst())
        {
            do {
                DisplayRecord(c);
            } while (c.moveToNext());
        }
        db.close();

        return c;











    }

    public void DisplayRecord(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Title: " + c.getString(1) + "\n" +
                        "Due Date: " + c.getString(2),
                Toast.LENGTH_SHORT).show();
    }

}
