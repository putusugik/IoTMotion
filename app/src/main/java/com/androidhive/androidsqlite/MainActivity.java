package com.androidhive.androidsqlite;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends Activity {
    EditText eWaktu1, eWaktu2;
    Button butSave, dis1, dis2;
    TimePicker time;
    TextView test;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        eWaktu1 = (EditText)findViewById(R.id.waktu_awal);
        eWaktu2 = (EditText)findViewById(R.id.waktu_akhir);
        butSave = (Button)findViewById(R.id.butsave);
        time = (TimePicker)findViewById(R.id.timePicker2);

        dis1 = (Button)findViewById(R.id.button2);
        dis2 = (Button)findViewById(R.id.button);
        DatabaseHandler db = new DatabaseHandler(this);



        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        /*Log.d("Insert: ", "Inserting ..");
        db.addTime(new TimeSchedule("10:00", "15:00"));
        db.addTime(new TimeSchedule("18:00", "06:00"));
        */
 
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<TimeSchedule> timeSchedule = db.getAllTimeSchedule();
 
        for (TimeSchedule ts : timeSchedule) {
            String log = "Id: "+ts.getID()+" ,Name: " + ts.get_waktu1() + " ,Phone: " + ts.get_waktu2();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
        }
    }
    public void save (View v){
        DatabaseHandler db = new DatabaseHandler(this);
        db.addTime(new TimeSchedule(eWaktu1.getText().toString(),eWaktu2.getText().toString()));
        Toast.makeText(MainActivity.this,
                "Berhasil", Toast.LENGTH_LONG).show();
    }
    public void get1(View v){
        eWaktu1.setText(new StringBuilder().append(time.getCurrentHour().toString()).append(":".toString()).append(time.getCurrentMinute().toString()));
    }
    public void get2(View v){
        eWaktu2.setText(new StringBuilder().append(time.getCurrentHour().toString()).append(":".toString()).append(time.getCurrentMinute().toString()));
    }
}