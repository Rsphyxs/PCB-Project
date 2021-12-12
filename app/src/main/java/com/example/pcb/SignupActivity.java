package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPass;
    private Button butSignup;
    private CheckBox checkBoxPolicy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        butSignup = findViewById(R.id.signup);
        checkBoxPolicy = findViewById(R.id.policyCheckbox);
        butSignup.setEnabled(false);
        checkBoxPolicy.setOnClickListener(this);
        butSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signup) {
            String dataUsername = editUsername.getText().toString().trim();
            String dataEmail = editEmail.getText().toString().trim();
            String dataPass = editPass.getText().toString().trim();
            boolean isEmptyFields = false;
            if (TextUtils.isEmpty(dataUsername)) {
                isEmptyFields = true;
                editUsername.setError("Field can't be empty");
            }
            if (TextUtils.isEmpty(dataEmail)) {
                isEmptyFields = true;
                editEmail.setError("Field can't be empty");
            }
            if (TextUtils.isEmpty(dataPass)) {
                isEmptyFields = true;
                editPass.setError("Field can't be empty");
            }
            if (!isEmptyFields) {
                Log.d("Ilhum", "Masuk enqueue");
                CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
                Call<Verif> call = crudInterface.verifSignup(dataUsername, dataEmail);
                call.enqueue(
                        new Callback<Verif>() {
                            @Override
                            public void onResponse(Call<Verif> call, Response<Verif> response) {
                                Verif verif = response.body();
                                if (verif.isSuccess() == true) {
                                    Toast.makeText(SignupActivity.this, "Email or Username already registered!", Toast.LENGTH_SHORT).show();
                                } else {
                                    sendData(dataUsername, dataEmail, dataPass);
                                }
                            }

                            @Override
                            public void onFailure(Call<Verif> call, Throwable t) {
                                Log.d("ERROR: ", t.getMessage());
                                Toast.makeText(SignupActivity.this, "Akun belum ada", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        }
        else if (checkBoxPolicy.isChecked()) {
            final Dialog dialog = new Dialog(SignupActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.term_dialog);

            final ImageButton cancelButton = dialog.findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBoxPolicy.setChecked(false);
                    dialog.dismiss();
                }
            });
            final CheckBox agreeCheckbox = dialog.findViewById(R.id.agreeCheckbox);
            agreeCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    butSignup.setEnabled(true);
                    checkBoxPolicy.setChecked(true);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 300);
                }
            });

            dialog.show();
        }
        else if (!checkBoxPolicy.isChecked()) {
            butSignup.setEnabled(false);
        }
    }

    public void sendData(String username, String email, String password) {
        CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
        Call<User> call = crudInterface.createUser(username, email, password);
        call.enqueue(
                new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(SignupActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                        Intent moveIntent = new Intent(SignupActivity.this, InformationActivity1.class);
                        moveIntent.putExtra(InformationActivity1.EXTRA_EMAIL, email);
                        startActivity(moveIntent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("ERROR: ", t.getMessage());
                        Toast.makeText(SignupActivity.this, "Failed to create Account!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}