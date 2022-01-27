package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Initial
        TextView outputSecond = (TextView) findViewById(R.id.outputSecond);
        TextView outputNameSecond = (TextView) findViewById(R.id.outputNameSecond);
        outputSecond.setText("");
        outputNameSecond.setText("");

        //prevent a selection even during initialization.
        Spinner spinner = findViewById(R.id.spinner2);
        spinner.setSelection(1, false); //1 = Activity Two
        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        String activity = intent.getStringExtra("activity");
        outputNameSecond.setText(userName);  //display output as the name that the user entered from first activity
        outputSecond.setText(activity);  // Display output as 'from' text line passed
        Log.d("LIFECYCLE_A2","onCreate() - Received "+ userName + " "+ activity);

    }

    /**
     * When the user select one of the selection in the spinner,
     * it will switch to the user's selection activity
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String[] activitiesArray = getResources().getStringArray(R.array.activities);
        Log.d("LIFECYCLE_A2", activitiesArray[position]);
        //Check selection from the user
        if(position == 0){  // 0 = activity 1
            Intent switchActivity1 = new Intent( MainActivity2.this, MainActivity.class);
            switchActivity1.putExtra("name", userName);
            switchActivity1.putExtra("activity", "From Second Activity");
            startActivity(switchActivity1);
        }else if(position == 2){ // 2 = activity 3
            Intent switchActivity3 = new Intent(MainActivity2.this, MainActivity3.class);
            switchActivity3.putExtra("name", userName);
            switchActivity3.putExtra("activity", "From Second Activity");
            startActivity(switchActivity3);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("LIFECYCLE_A2", "Nothing Selected");
    }

    // reached after onCreate() or onRestart()
    // called when the activity is about to become visible
    @Override
    protected void onStart() {
        super.onStart();
        //Initial selection for spinner when the user goes back to previous activity
        Spinner spinner = findViewById(R.id.spinner2);
        spinner.setSelection(1, false); //1 = Activity Two
        spinner.setOnItemSelectedListener(this);
        Log.d("LIFECYCLE_A2","onStart()");
    }
    // reached after onPause() or onStart()
    // called when the activity has become visible
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE_A2","onResume()");
    }
    /**
     * ----------------------------------------------
     *  Activity is running after call to onResume()
     * ----------------------------------------------
     */
    // another activity comes into the foreground
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("LIFECYCLE_A2","onPause()");
    }
    // activity is no longer visible
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("LIFECYCLE_A2","onStop()");
    }
    // user navigates to the activity after the stop state
    // next state will be onStart()
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("LIFECYCLE_A2","onRestart()");
    }
    // activity is finishing or being destroyed by the system
    // reached after onStop()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("LIFECYCLE_A2", "onDestroy()");
    }
}
