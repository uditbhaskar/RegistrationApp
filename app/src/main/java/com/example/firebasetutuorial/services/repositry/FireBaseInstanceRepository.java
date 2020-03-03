package com.example.firebasetutuorial.services.repositry;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.firebasetutuorial.services.model.Student;
import com.example.firebasetutuorial.services.model.StudentReg;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseInstanceRepository {
    FirebaseDatabase instance = FirebaseDatabase.getInstance();
    List<StudentReg> studentList = new ArrayList<>();

    public MutableLiveData<List<StudentReg>> fetchData() {
        final MutableLiveData<List<StudentReg>> studentData = new MutableLiveData<>();
        studentList.clear();

        instance.getReference("studentReg").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot studentRegSnapshot : dataSnapshot.getChildren()) {
                    String id = studentRegSnapshot.getKey();

                    for (DataSnapshot studentSnapshot : studentRegSnapshot.getChildren()) {
                        String timeStamp = studentSnapshot.getKey();
                        Student studentData = studentSnapshot.getValue(Student.class);
                        studentList.add(new StudentReg(timeStamp, id, studentData));
                    }
                }
                studentData.setValue(studentList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return studentData;
    }

    public MutableLiveData<Boolean> addRegistartion(String id, String timeStamp, Student student) {
        final MutableLiveData<Boolean> result = new MutableLiveData<>();
        instance.getReference("studentReg")
                .child(id)
                .child(timeStamp)
                .setValue(student)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("result", "Data successfully added.");
                        result.setValue(true);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("result", e.toString());
                result.setValue(false);
            }
        });
        return result;
    }

    public MutableLiveData<Boolean> deleteRegistartion(String id, String timeStamp) {
        final MutableLiveData<Boolean> result = new MutableLiveData<>();
        instance.getReference("studentReg")
                .child(id)
                .child(timeStamp)
                .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("result", "Successfully Deleted.");
                result.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("result", e.toString());
                result.setValue(false);
            }
        });
        return result;
    }

    public MutableLiveData<Boolean> editRegistration(String id, String timeStamp, Student student) {
        final MutableLiveData<Boolean> result = new MutableLiveData<>();
        instance.getReference("studentReg")
                .child(id)
                .child(timeStamp)
                .setValue(student)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        result.setValue(true);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                result.setValue(false);
                Log.d("result", e.toString());
            }
        });

        return result;
    }
}
