package com.example.kiwi.myapplication.notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.kiwi.myapplication.R;

/*
* MainActivity: The entry point into this app.
*/
public class NotesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        if(null == savedInstanceState)
            initFragment(NotesFragment.newInstance());
    }

    private void initFragment(Fragment notesFragment) {
        // Add NotesFragment to the layout
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.contentFrame, notesFragment);
        ft.commit();
    }

}
