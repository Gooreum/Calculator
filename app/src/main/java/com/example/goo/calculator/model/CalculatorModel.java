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

    private String operatingProcess;
    private String operatingResult;

    private boolean isNumber;
    private boolean isDot;

    public CalculatorModel(String operatingProcess, String operatingResult, boolean isNumber, boolean isDot) {
        this.operatingProcess = operatingProcess;
        this.operatingResult = operatingResult;
        this.isNumber = isNumber;
        this.isDot = isDot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOperatingProcess() {
        return operatingProcess;
    }
    public String getOperatingResult() {
        return operatingResult;
    }

    public boolean isNumber() {
        return isNumber;
    }


    public boolean isDot() {
        return isDot;
    }


}
