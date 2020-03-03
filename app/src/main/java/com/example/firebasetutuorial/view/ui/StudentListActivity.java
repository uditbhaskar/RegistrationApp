package com.example.firebasetutuorial.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.firebasetutuorial.R;
import com.example.firebasetutuorial.services.model.StudentReg;
import com.example.firebasetutuorial.view.adapter.StudentRegAdapter;
import com.example.firebasetutuorial.viewmodel.FireBaseViewModel;

import java.util.List;


public class StudentListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FireBaseViewModel fireBaseViewModel;
    StudentRegAdapter studentRegAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        init();
        setUpRecyclerView();
        setUpFirebaseViewModel();
    }

    private void setUpRecyclerView() {
        studentRegAdapter = new StudentRegAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentRegAdapter);
    }

    private void setUpFirebaseViewModel() {
        fireBaseViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()))
                .get(FireBaseViewModel.class);   //first time fetch
        fetchPost();
    }

    private void fetchPost() {
        progressBar.setVisibility(View.VISIBLE);
        fireBaseViewModel.fetchList();
        fireBaseViewModel.studentRegLiveData.observe(this, new Observer<List<StudentReg>>() {
            @Override
            public void onChanged(List<StudentReg> studentRegs) {
                studentRegAdapter.setItems(studentRegs);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void deletePost(String id,String timeStamp) {
        progressBar.setVisibility(View.VISIBLE);
        fireBaseViewModel.deleteList(id,timeStamp);
        fireBaseViewModel.deleteInfo.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(StudentListActivity.this, "Data deleted successfully!", Toast.LENGTH_SHORT).show();
                    recyclerView.setVisibility(View.VISIBLE);
                    fetchPost();

                } else {
                    Toast.makeText(StudentListActivity.this, "Sorry, unable to delete the post...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
    }
}
