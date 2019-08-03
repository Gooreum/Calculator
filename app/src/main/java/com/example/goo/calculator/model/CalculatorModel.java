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
    private boolean lastDot;

    public CalculatorModel(String operatingProcess, String operatingResult, boolean isNumber, boolean lastDot) {
        this.operatingProcess = operatingProcess;
        this.operatingResult = operatingResult;
        this.isNumber = isNumber;
        this.lastDot = lastDot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOperatingProcess(String operatingProcess) {
        this.operatingProcess = operatingProcess;
    }

    public String getOperatingProcess() {
        return operatingProcess;
    }

    public void setOperatingResult(String operatingResult) {
        this.operatingResult = operatingResult;
    }


    public String getOperatingResult() {
        return operatingResult;
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
