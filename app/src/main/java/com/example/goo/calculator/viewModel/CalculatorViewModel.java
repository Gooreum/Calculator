package com.example.goo.calculator.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.goo.calculator.model.CalculatorModel;
import com.example.goo.calculator.repository.CalculatorRepository;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-07-29.
 */

public class CalculatorViewModel extends AndroidViewModel {
    private CalculatorRepository repository;
    private LiveData<CalculatorModel> allValues;


    public CalculatorViewModel(@NonNull Application application) {
        super(application);
        repository = new CalculatorRepository(application);
        allValues = repository.getAllValues();
    }

    public void insert(CalculatorModel values) {
        repository.insert(values);
    }

    public void update(CalculatorModel values) {
        Log.d(TAG, "update:" + values.getOperatingProcess());
        repository.update(values);
    }




    public LiveData<CalculatorModel> getAllValues() {
        return allValues;
    }

}
