package com.example.firebasetutuorial.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebasetutuorial.services.model.Student;
import com.example.firebasetutuorial.services.model.StudentReg;
import com.example.firebasetutuorial.services.repositry.FireBaseInstanceRepository;

import java.util.List;

public class FireBaseViewModel extends ViewModel {

    private FireBaseInstanceRepository fireBaseInstanceRepository;
    public LiveData<List<StudentReg>> studentRegLiveData;
    public LiveData<Boolean> addInfo;
    public LiveData<Boolean> editInfo;
    public LiveData<Boolean> deleteInfo;

    public FireBaseViewModel() {
        fireBaseInstanceRepository = new FireBaseInstanceRepository();
    }

    public void fetchList() {
        studentRegLiveData = fireBaseInstanceRepository.fetchData();
    }

    public void addList(String id, String timeStamp, Student student) {
        addInfo = fireBaseInstanceRepository.addRegistartion(id, timeStamp, student);
    }

    public void editList(String id, String timeStamp, Student student) {
        editInfo = fireBaseInstanceRepository.editRegistration(id, timeStamp, student);
    }

    public void deleteList(String id, String timeStamp) {
        deleteInfo = fireBaseInstanceRepository.deleteRegistartion(id, timeStamp);
    }


}
