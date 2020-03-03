package com.example.firebasetutuorial.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.firebasetutuorial.R;
import com.example.firebasetutuorial.services.model.Student;

import com.example.firebasetutuorial.viewmodel.FireBaseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {
    EditText etName;
    EditText etRoll;
    EditText etId;
    FloatingActionButton btn_showRecord;
    FireBaseViewModel fireBaseViewModel;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        // setUpRecyclerView();
        setUpFirebaseViewModel();
        setListeners();

    }

    private void init() {
        etName = findViewById(R.id.et_name);
        etId = findViewById(R.id.et_id);
        etRoll = findViewById(R.id.et_roll);
        btnRegister = findViewById(R.id.btn_register);
        btn_showRecord = findViewById(R.id.btn_show_record);
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etId.getText().toString().trim().isEmpty() || etName.getText().toString().trim().isEmpty() || etRoll.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "All fields are mandatory.", Toast.LENGTH_SHORT).show();
                } else {
                    addPost();
                    etName.getText().clear();
                    etId.getText().clear();
                    etRoll.getText().clear();
                }

            }
        });

        btn_showRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addPost() {

        fireBaseViewModel.addList(etId.getText().toString().trim(), String.valueOf(System.currentTimeMillis()), new Student(etName.getText().toString().trim(), etRoll.getText().toString().trim()));
        fireBaseViewModel.addInfo.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(RegisterActivity.this, "Record Added.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Record Not Added.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setUpFirebaseViewModel() {
        fireBaseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()))
                .get(FireBaseViewModel.class);
    }


}
