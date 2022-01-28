package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prevent a selection even during initialization.
        Spinner spinner = findViewById(R.id.spinner1);
        spinner.setSelection(0, false); //0 = Activity One
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * When the user select one of the selection in the spinner,
     * it will switch to the user's selection activity
     *
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        EditText txtEnterName = (EditText) findViewById(R.id.inputName);
        String userName = txtEnterName.getText().toString();
        String[] activitiesArray = getResources().getStringArray(R.array.activities);
        Log.d("LIFECYCLE_A1", activitiesArray[position]);
        //Check selection from the user
        if(position == 1){  // 1 = activity 2
            Intent switchActivity2 = new Intent(MainActivity.this, MainActivity2.class);
            switchActivity2.putExtra("name", userName);
            switchActivity2.putExtra("activity", "From First Activity");
            startActivity(switchActivity2);
        }
        else if(position == 2){ // position 2 in the array = activity 3
           Intent switchActivity3 = new Intent(MainActivity.this, MainActivity3.class);
           switchActivity3.putExtra("name", userName);
           switchActivity3.putExtra("activity", "From First Activity");
           startActivity(switchActivity3); //start to switch
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("LIFECYCLE_A1", "Nothing Selected");
    }

    // reached after onCreate() or onRestart()
    // called when the activity is about to become visible
    @Override
    protected void onStart() {
        super.onStart();
        //Initial selection for spinner when the user goes back to previous activity
        Spinner spinner = findViewById(R.id.spinner1);
        spinner.setSelection(0, false); //0 = Activity One
        spinner.setOnItemSelectedListener(this);
        Log.d("LIFECYCLE_A1","onStart()");
    }
    // reached after onPause() or onStart()
    // called when the activity has become visible
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE_A1","onResume()");
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
        Log.d("LIFECYCLE_A1","onPause()");
    }
    // activity is no longer visible
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("LIFECYCLE_A1","onStop()");
    }
    // user navigates to the activity after the stop state
    // next state will be onStart()
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("LIFECYCLE_A1","onRestart()");
    }
    // activity is finishing or being destroyed by the system
    // reached after onStop()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("LIFECYCLE_A1", "onDestroy()");
    }
}