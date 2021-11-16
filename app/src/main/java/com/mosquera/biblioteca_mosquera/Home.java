package com.mosquera.biblioteca_mosquera;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onBackPressed()
    {
            finish();
    }
}