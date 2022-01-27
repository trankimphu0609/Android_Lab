package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Initial
        TextView outputThird = (TextView) findViewById(R.id.outputThird);
        TextView outputNameThird = (TextView) findViewById(R.id.outputNameThird);
        outputThird.setText("");
        outputNameThird.setText("");

        //prevent a selection even during initialization.
        Spinner spinner = findViewById(R.id.spinner3);
        spinner.setSelection(2, false); //2 = Activity Third
        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        String activity = intent.getStringExtra("activity");
        outputNameThird.setText(userName);//display output as the name that the user entered from first activity
        outputThird.setText(activity); // Display output as 'from' text line passed
        Log.d("LIFECYCLE_A3","onCreate() - Received "+ userName + " "+ activity);
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
        Log.d("LIFECYCLE_A3", activitiesArray[position]);
        //Check selection from the user
        if(position == 0){  // 0 = activity 1
            Intent switchActivity1 = new Intent( MainActivity3.this, MainActivity.class);
            switchActivity1.putExtra("name", userName);
            switchActivity1.putExtra("activity", "From Third Activity");
            startActivity(switchActivity1);
        }else if(position == 1){ // 1 = activity 2
            Intent switchActivity3 = new Intent(MainActivity3.this, MainActivity2.class);
            switchActivity3.putExtra("name", userName);
            switchActivity3.putExtra("activity", "From Third Activity");
            startActivity(switchActivity3);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("LIFECYCLE_A3", "Nothing Selected");
    }

    // reached after onCreate() or onRestart()
    // called when the activity is about to become visible
    @Override
    protected void onStart() {
        super.onStart();
        //Initial selection for spinner when the user goes back to previous activity
        Spinner spinner = findViewById(R.id.spinner3);
        spinner.setSelection(2, false); //2 = Activity Third
        spinner.setOnItemSelectedListener(this);
        Log.d("LIFECYCLE_A3","onStart()");
    }
    // reached after onPause() or onStart()
    // called when the activity has become visible
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE_A3","onResume()");
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
        Log.d("LIFECYCLE_A3","onPause()");
    }
    // activity is no longer visible
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("LIFECYCLE_A3","onStop()");
    }
    // user navigates to the activity after the stop state
    // next state will be onStart()
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("LIFECYCLE_A3","onRestart()");
    }
    // activity is finishing or being destroyed by the system
    // reached after onStop()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("LIFECYCLE_A3", "onDestroy()");
    }
}
