package com.example.goo.calculator.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by Goo on 2019-07-31.
 */
@Database(entities = {CalculatorModel.class}, version = 1)
public abstract class CalculatorDatabase extends RoomDatabase {

    private static CalculatorDatabase instance;

    public abstract CalculatorDao calculatorDao();

    public static synchronized CalculatorDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CalculatorDatabase.class, "calculator_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CalculatorDao calculatorDao;

        private PopulateDbAsyncTask(CalculatorDatabase db) {
            calculatorDao = db.calculatorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
