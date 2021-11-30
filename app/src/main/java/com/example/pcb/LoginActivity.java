package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignup;
    private ImageButton btnPrev;
    private EditText editEmail;
    private EditText editPass;
    private Button btnSkip;
    private Button btnSignin;
    private ImageButton btnShow;
    private boolean show = false;
    public static boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);

        btnSignin = findViewById(R.id.signin);
        btnSignin.setOnClickListener(this);
        btnSignup = findViewById(R.id.signup);
        btnSignup.setOnClickListener(this);
        btnPrev = findViewById(R.id.prevbutton);
        btnPrev.setOnClickListener(this);
        btnSkip = findViewById(R.id.skipfornow);
        btnSkip.setOnClickListener(this);
        btnShow = findViewById(R.id.showBut);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signin){
            String dataEmail = editEmail.getText().toString().trim();
            String dataPass = editPass.getText().toString().trim();
            boolean isEmptyFields = false;
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
                Call<Verif> call = crudInterface.verifSignin(dataEmail, dataPass);
                call.enqueue(
                        new Callback<Verif>() {
                            @Override
                            public void onResponse(Call<Verif> call, Response<Verif> response) {
                                Verif verif = response.body();
                                if(verif.isSuccess()==true){
                                    login = true;
                                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent moveIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                                    moveIntent.putExtra(MainMenuActivity.EXTRA_EMAIL, dataEmail);
                                    startActivity(moveIntent);
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Username or Password incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Verif> call, Throwable t) {
                                Log.d("ERROR: ", t.getMessage());
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        }
        else if (v.getId() == R.id.signup){
            Intent moveIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(moveIntent);
        }
        else if (v.getId() == R.id.prevbutton){
            Intent moveIntent = new Intent(LoginActivity.this, InformationActivity2.class);
            startActivity(moveIntent);
        }
        else if (v.getId() == R.id.skipfornow){
            login = false;
            Intent moveIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(moveIntent);
        }
        else if (v.getId() == R.id.showBut){
            if(show == false){
                editPass.setTransformationMethod(null);
                show = true;
            }
            else{
                editPass.setTransformationMethod(new PasswordTransformationMethod());
                show = false;
            }
        }
    }

    public void onBackPressed(){
        moveTaskToBack(true);
    }
}