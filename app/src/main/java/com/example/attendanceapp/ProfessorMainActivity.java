package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProfessorMainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Professor professor;
    DocumentReference profDocumentRef;
    BottomNavigationView navMenu;
    String uuid;
    List<Classroom> classrooms = new ArrayList<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_main);

        uuid = getIntent().getStringExtra("UUID");
        professor = new Professor(uuid);
        profDocumentRef = db.collection("professors").document(uuid);
        loadProfessorInformation(uuid);
        loadClassroomsInformation(uuid);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        navMenu = findViewById(R.id.bottom_nav_menu);
        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = HomeFragment.newInstance(professor.getFirstName());

                if (item.getItemId() == R.id.nav_home)
                    fragment = HomeFragment.newInstance(professor.getFirstName());
                if( item.getItemId() == R.id.nav_profile)
                    fragment = ProfileFragment.newInstance(professor);
                if ( item.getItemId() == R.id.nav_generate )
                    fragment = GenerateFragment.newInstance(classrooms);
                if ( item.getItemId() == R.id.nav_consult )
                    fragment = new StatsFragment();
                if ( item.getItemId() == R.id.nav_classes )
                    fragment = ClassesFragment.newInstance(classrooms);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

                return true;
            }
        });
    }

    private void loadProfessorInformation(String uuid) {
        // load Professor information

        profDocumentRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if ( documentSnapshot.exists() ) {

                            professor.setUsername(documentSnapshot.getString(
                                    "username"));
                            professor.setJobTitle(documentSnapshot.getString(
                                    "jobTitle"));
                            professor.setFirstName(documentSnapshot.getString("firstName"));
                            professor.setLastName(documentSnapshot.getString(
                                    "lastName"));
                            professor.setUniversity(documentSnapshot.getString(
                                    "university"));
                            professor.setEmail(documentSnapshot.getString(
                                    "email"));
                            professor.setPassword(documentSnapshot.getString(
                                    "password"));
                            professor.setPhone(documentSnapshot.getString(
                                    "phone"));

                        } else {
                            Toast.makeText(getBaseContext(), "Professor Does " +
                                    "not exists", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getBaseContext(), "Failed to load " +
                                "Professor Informations", Toast.LENGTH_LONG).show();
                        Log.d("FIREBASE_DEBUG", e.toString());
                    }
                });

    }

    private void loadClassroomsInformation(String uuid) {
        db.collection("classrooms")
                .whereEqualTo("professorUUID", uuid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot:
                                queryDocumentSnapshots) {

                            Classroom classroom =
                                    documentSnapshot.toObject(Classroom.class);
                            classrooms.add(classroom);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getBaseContext(), "Failed to load " +
                                "classrooms", Toast.LENGTH_LONG).show();
                        Log.d("FIREBASE_DEBUG", "onFailure: " + e.toString());
                    }
                });

    }

    public DocumentReference getProfDocumentRef() {
        return profDocumentRef;
    }

    public String getProfessorUUID() {
        return uuid;
    }

    public FirebaseFirestore getDb() {
        return db;
    }
}
