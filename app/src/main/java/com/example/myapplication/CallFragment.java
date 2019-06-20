package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.Calendar;

public class CallFragment extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialogFragment newFragment = new AlertDialogFragment();
        newFragment.show(getSupportFragmentManager(), "dialog");
    }
}
