package com.example.goo.calculator.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Goo on 2019-07-29.
 */

@Entity(tableName = "calculator_table")
public class CalculatorModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String value;
    private String result;

    private boolean isNumber;
    private boolean lastDot;

    public CalculatorModel(String value, String result, boolean isNumber, boolean lastDot) {
        this.value = value;
        this.result = result;

        this.isNumber = isNumber;
        this.lastDot = lastDot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getValue() {
        return value;
    }

    public String getResult() {
        return result;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(boolean isNumber) {
        this.isNumber = isNumber;
    }


    public boolean isLastDot() {
        return lastDot;
    }
    public void setLastDot(boolean lastDot) {
        this.lastDot = lastDot;
    }

}
