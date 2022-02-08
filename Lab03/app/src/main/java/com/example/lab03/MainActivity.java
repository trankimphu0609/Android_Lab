package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public final String SW_STATE = "SW_STATE";
    public final String SP_FILE = "shared";
    public final String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TopHalf topHalf= new TopHalf();
        BottomHalf bottomHalf = new BottomHalf();
        fragmentTransaction.replace(R.id.topFrameLayout,topHalf);
        fragmentTransaction.replace(R.id.bottomFrameLayout, bottomHalf);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = this.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        String getCount = sharedPreferences.getString(SW_STATE, "0");
        TextView currentCount = (TextView) findViewById(R.id.countView);
        currentCount.setText(getCount);
        Log.d(tag,"onResume() - fetching state found" + getCount);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences = this.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
        TextView currentCount = (TextView) findViewById(R.id.countView);
        String getCount = currentCount.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SW_STATE, getCount);
        editor.apply();
        Log.d(tag, "onPause() - saving state found" + getCount);
    }

    public void onSubtractCount(View view) {
        TextView currentCount = (TextView) findViewById(R.id.countView);
        int number = Integer.parseInt(currentCount.getText().toString());
        number -= 1;
        currentCount.setText(String.valueOf(number));
    }

    public void onAddCount(View view) {
        TextView currentCount = (TextView) findViewById(R.id.countView);
        int number = Integer.parseInt(currentCount.getText().toString());
        number += 1;
        currentCount.setText(String.valueOf(number));
    }
}