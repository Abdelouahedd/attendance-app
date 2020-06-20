package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    MaterialButton loginButton;
    private FirebaseAuth mAuth;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.login_button);
        usernameInput = findViewById(R.id.login);
        passwordInput = findViewById(R.id.password);

        // TODO: implemtnt button listener and remove this
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(usernameInput.getText());
                String password = String.valueOf(passwordInput.getText());

                if ( ! validateUserInput(username, password) ) {

                    Toast.makeText(getBaseContext(), "Empty Inputs", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(LoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if ( task.isSuccessful() ) {

                                Intent intent =
                                        new Intent(getBaseContext(),
                                                ProfessorMainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getBaseContext(), "Invalide " +
                                        "Credentials", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
            }
        });
    }

    private Boolean validateUserInput(String username, String password) {

        if ( username.length() == 0 )
            return false;

        if ( password.length() == 0 )
            return false;

        return true;
    }

}
