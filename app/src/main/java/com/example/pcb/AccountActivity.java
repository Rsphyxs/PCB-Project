package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {
    private de.hdodenhof.circleimageview.CircleImageView imageUser;
    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private ImageButton butShow;
    private Button butUpdate;
    private ImageButton butPrev;
    private boolean show = false;
    private String dataEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        imageUser = findViewById(R.id.accountImage);
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPass);
        butShow = findViewById(R.id.showBut);
        butShow.setOnClickListener(this);
        butUpdate = findViewById(R.id.update);
        butUpdate.setOnClickListener(this);
        butPrev = findViewById(R.id.prevbutton);
        butPrev.setOnClickListener(this);

        if(MainMenuActivity.list.get(0).getUsername().equals("zufar")){
            imageUser.setImageResource(R.drawable.zufar);
        }
        else if(MainMenuActivity.list.get(0).getUsername().equals("ilhum")){
            imageUser.setImageResource(R.drawable.ilhum);
        }
        else if(MainMenuActivity.list.get(0).getUsername().equals("fred")){
            imageUser.setImageResource(R.drawable.fred);
        }
        else{
            imageUser.setImageResource(R.drawable.logopcb);
        }
        editUsername.setText(MainMenuActivity.list.get(0).getUsername());
        editEmail.setText(MainMenuActivity.list.get(0).getEmail());
        editPassword.setText(MainMenuActivity.list.get(0).getPassword());

        dataEmail = MainMenuActivity.list.get(0).getEmail();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.showBut){
            if(show == false){
                editPassword.setTransformationMethod(null);
                show = true;
            }
            else{
                editPassword.setTransformationMethod(new PasswordTransformationMethod());
                show = false;
            }
        }
        else if(v.getId() == R.id.update){
            String dataUsername = editUsername.getText().toString().trim();
            dataEmail = editEmail.getText().toString().trim();
            String dataPass = editPassword.getText().toString().trim();
            CRUDapi crudInterface = RetrofitClient.getClient().create(CRUDapi.class);
            Call<User> call = crudInterface.updateUser(MainMenuActivity.list.get(0).getId(), dataUsername, dataEmail, dataPass);
            call.enqueue(
                    new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.d("Update: ", "SUCCESS");
                            MainMenuActivity.list.get(0).setUsername(dataUsername);
                            MainMenuActivity.list.get(0).setEmail(dataEmail);
                            MainMenuActivity.list.get(0).setPassword(dataPass);
                            Toast.makeText(AccountActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d("Update: ", t.getMessage());
                            Toast.makeText(AccountActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
        else if(v.getId() == R.id.prevbutton){
            Intent moveIntent = new Intent(AccountActivity.this, MainMenuActivity.class);
            moveIntent.putExtra(MainMenuActivity.EXTRA_EMAIL, dataEmail);
            startActivity(moveIntent);
        }
    }
}