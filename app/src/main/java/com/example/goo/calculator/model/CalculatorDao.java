package com.example.goo.calculator.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by Goo on 2019-07-30.
 */
@Dao
public interface CalculatorDao {

    @Insert
    void insert(CalculatorModel note);

    @Update
    void update(CalculatorModel note);

    @Query("SELECT * FROM calculator_table ")
    LiveData<CalculatorModel> getAllValues();
}
