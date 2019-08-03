package com.example.goo.calculator.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.goo.calculator.model.CalculatorModel;
import com.example.goo.calculator.model.CalculatorDao;
import com.example.goo.calculator.model.CalculatorDatabase;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-07-29.
 */

public class CalculatorRepository {
    private CalculatorDao calculatorDao;
    private LiveData<CalculatorModel> allValues;

    public CalculatorRepository(Application application) {
        CalculatorDatabase database = CalculatorDatabase.getInstance(application);
        calculatorDao = database.calculatorDao();
        allValues = calculatorDao.getAllValues();
    }

    public void insert(CalculatorModel calculatorModel) {
        new InsertNoteAsyncTask(calculatorDao).execute(calculatorModel);
    }

    public void update(CalculatorModel calculatorModel) {
        new UpdateNoteAsyncTask(calculatorDao).execute(calculatorModel);

    }

    public LiveData<CalculatorModel> getAllValues() {
        return allValues;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<CalculatorModel, Void, Void> {
        private CalculatorDao calculatorDao;

        private InsertNoteAsyncTask(CalculatorDao calculatorDao) {
            this.calculatorDao = calculatorDao;
        }

        @Override
        protected Void doInBackground(CalculatorModel... values) {
            calculatorDao.insert(values[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<CalculatorModel, Void, Void> {
        private CalculatorDao calculatorDao;

        private UpdateNoteAsyncTask(CalculatorDao calculatorDao) {
            this.calculatorDao = calculatorDao;
        }

        @Override
        protected Void doInBackground(CalculatorModel... values) {
            calculatorDao.update(values[0]);
            return null;
        }
    }

}
