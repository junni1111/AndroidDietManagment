package com.dadada.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dadada.app.model.DietLog;
import com.dadada.app.model.DietLogRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    // repository
    private DietLogRepository repository;

    // live data
    private LiveData<List<DietLog>> allDietLogs;
    private LiveData<List<DietLog>> allDietLogsByDate;
    private LiveData<List<DietLog>> dietLogById;
    private LiveData<List<DietLog>> dietLogByDay;
    private int caloriesAfterDate;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new DietLogRepository(application);
    }

    public LiveData<List<DietLog>> getAllDietLogs() {
        allDietLogs = repository.getAllDietLogs();
        return allDietLogs;
    }

    public LiveData<List<DietLog>> getDietLogById(int id) {
        dietLogById = repository.getDietLogById(id);
        return dietLogById;
    }

    public LiveData<List<DietLog>> getDietLogByDay(String day) {
        dietLogByDay = repository.getDietLogByDay(day);
        return dietLogByDay;
    }

    public LiveData<List<DietLog>> getAllDietLogsByDate() {
        allDietLogsByDate = repository.getAllDietLogsByDate();
        return allDietLogsByDate;
    }

    public int getCaloriesAfterDate(int date) {
        caloriesAfterDate = repository.getCaloriesAfterDate(date);
        return caloriesAfterDate;
    }

    public void addNewDietLog(DietLog dietLog) {
        repository.insertDietLog(dietLog);
    }

    public void updateNewDietLog(DietLog dietLog) {
        repository.updateDietLog(dietLog);
    }

    public void deleteNewDietLog(DietLog dietLog) {
        repository.deleteDietLog(dietLog);
    }


}
