package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfessorMainActivity extends AppCompatActivity {

    BottomNavigationView navMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        navMenu = findViewById(R.id.bottom_nav_menu);
        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new HomeFragment();

                if (item.getItemId() == R.id.nav_home)
                    fragment = new HomeFragment();
                if( item.getItemId() == R.id.nav_profile)
                    fragment = new ProfileFragment();
                if ( item.getItemId() == R.id.nav_generate )
                    fragment = new GenerateFragment();
                if ( item.getItemId() == R.id.nav_consult )
                    fragment = new StatsFragment();
                if ( item.getItemId() == R.id.nav_classes )
                    fragment = new ClassesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

                return true;
            }
        });
    }
}
