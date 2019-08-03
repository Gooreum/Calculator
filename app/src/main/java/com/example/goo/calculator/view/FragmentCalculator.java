
package com.example.goo.calculator.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.goo.calculator.calculatorTask.Expression;
import com.example.goo.calculator.calculatorTask.ExpressionBuilder;
import com.example.goo.calculator.model.CalculatorModel;
import com.example.goo.calculator.viewModel.CalculatorViewModel;
import com.example.goo.calculator.R;


import java.math.BigDecimal;

import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;


/**
 * Created by Goo on 2019-07-30.
 */


public class FragmentCalculator extends Fragment implements View.OnClickListener {

    private CalculatorViewModel calculatorViewModel;
    private CalculatorModel calculatorModel;

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnAdd;
    private Button btnMin;
    private Button btnMul;
    private Button btnDiv;
    private Button btnDel;
    private Button btnAllClear;
    private Button btn100;
    private Button btnEqual;
    private Button btnDot;


    private boolean isNumber;
    private boolean lastDot;

    private StringBuffer values;
    private StringBuffer resultValue;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_button, container, false);
        ButterKnife.bind(this, v);

        inisializeButtons(v);
        setOnclick();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calculatorViewModel = ViewModelProviders.of(getActivity()).get(CalculatorViewModel.class);
        calculatorViewModel.getAllValues().observe(getActivity(), new Observer<CalculatorModel>() {
            @Override
            public void onChanged(@Nullable CalculatorModel calculModel) {
                calculatorModel = calculModel;
                if (calculatorModel != null) {

                    values = new StringBuffer(calculatorModel.getValue().toString());
                    resultValue = new StringBuffer(calculatorModel.getResult().toString());

                    isNumber = calculatorModel.isNumber();
                    lastDot = calculatorModel.isLastDot();

                    Log.d(TAG, "isNumber " + isNumber);
                    Log.d(TAG, "lastDot " + lastDot);

                    Log.d(TAG, "Value객체 값은 ? " + values.getClass().hashCode());
                    Log.d(TAG, "resultValue객체 값은 ? " + resultValue.getClass().hashCode());
                } else {

                    calculatorModel = new CalculatorModel("", "", false, false);
                    calculatorViewModel.insert(calculatorModel);
                }
            }
        });

    }

    private void inisializeButtons(View v) {

        btn0 = v.findViewById(R.id.btn0);
        btn1 = v.findViewById(R.id.btn1);
        btn2 = v.findViewById(R.id.btn2);
        btn3 = v.findViewById(R.id.btn3);
        btn4 = v.findViewById(R.id.btn4);
        btn5 = v.findViewById(R.id.btn5);
        btn6 = v.findViewById(R.id.btn6);
        btn7 = v.findViewById(R.id.btn7);
        btn8 = v.findViewById(R.id.btn8);
        btn9 = v.findViewById(R.id.btn9);
        btn100 = v.findViewById(R.id.btn100);
        btnAdd = v.findViewById(R.id.btnAdd);
        btnMin = v.findViewById(R.id.btnMin);
        btnMul = v.findViewById(R.id.btnMul);
        btnDiv = v.findViewById(R.id.btnDiv);
        btnDel = v.findViewById(R.id.btnDel);
        btnAllClear = v.findViewById(R.id.btnAllClear);
        btnDel = v.findViewById(R.id.btnDel);
        btnEqual = v.findViewById(R.id.btnEqual);
        btnDot = v.findViewById(R.id.btnDot);

    }

    private void setOnclick() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn100.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMin.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnAllClear.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnDot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int Id = v.getId();
        switch (Id) {
            case R.id.btn0:
                append("0");

                updateCalculatorModelIsNumber(true);

                break;

            case R.id.btn1:

                append("1");

                updateCalculatorModelIsNumber(true);


                break;

            case R.id.btn2:
                append("2");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn3:
                append("3");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn4:
                append("4");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn5:
                append("5");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn6:
                append("6");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn7:
                append("7");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn8:
                append("8");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn9:
                append("9");

                updateCalculatorModelIsNumber(true);
                break;

            case R.id.btn100:
                if (!isEmpty()) {
                    if (endsWithOperatore()) {
                        replace("%");
                    } else {
                        append("%");
                    }
                }
                updateCalculatorModelIsNumber(false);
                updateCalculatorModelIsDot(false);

                break;

            case R.id.btnAdd:
                if (!isEmpty()) {
                    if (endsWithOperatore()) {
                        replace("+");
                    } else {
                        append("+");
                    }
                }
                updateCalculatorModelIsNumber(false);
                updateCalculatorModelIsDot(false);

                break;

            case R.id.btnMin:
                if (!isEmpty()) {
                    if (endsWithOperatore()) {
                        replace("-");
                    } else {
                        append("-");
                    }
                }
                updateCalculatorModelIsNumber(false);
                updateCalculatorModelIsDot(false);

                break;

            case R.id.btnMul:

                if (!isEmpty()) {
                    if (endsWithOperatore()) {
                        replace("x");
                    } else {
                        append("x");
                    }
                }
                updateCalculatorModelIsNumber(false);
                updateCalculatorModelIsDot(false);

                break;

            case R.id.btnDiv:

                if (!isEmpty()) {
                    if (endsWithOperatore()) {
                        replace("\u00F7");
                    } else {
                        append("\u00F7");
                    }
                }
                updateCalculatorModelIsNumber(false);
                updateCalculatorModelIsDot(false);

                break;

            case R.id.btnDot:


                if (isNumber && !lastDot) {
                    append(".");
                    updateCalculatorModelIsNumber(false);
                    updateCalculatorModelIsDot(true);

                } else if (isEmpty() || endsWithOperatore()) {
                    append("0.");
                    updateCalculatorModelIsNumber(false);
                    updateCalculatorModelIsDot(true);

                }

                break;

            case R.id.btnDel:

                delete();

                break;

            case R.id.btnAllClear:

                clear();
                break;

            case R.id.btnEqual:

                calcule(true);

                break;

            default:

                break;
        }

    }


    //연산자로 끝나느지 확인하기
    private boolean endsWithOperatore() {
        return getinput().endsWith("+") || getinput().endsWith("-") || getinput().endsWith("\u00F7") || getinput().endsWith("x") || getinput().endsWith("%");
    }

    //
    private void replace(String str) {

        values.replace(getinput().length() - 1, getinput().length(), str);
        updateCalculatorModelValues(values.toString(), resultValue.toString());

    }

    //연산 클리어
    private void clear() {

        updateCalculatorModelIsNumber(false);
        updateCalculatorModelIsDot(false);

        values.delete(0, values.length());
        updateCalculatorModelValues("", "");

    }

    //입력하기
    private void append(String str) {
        values.append(str);

        if (!endsWithOperatore()) {
            calcule(false);
        }
        updateCalculatorModelValues(values.toString(), resultValue.toString());


        Log.e(TAG, "CalculatorModel: " + calculatorViewModel.getAllValues().getValue().getValue().toString());

    }

    //연산자 혹은 피연산자 값 지우기
    private void delete() {

        if (!isEmpty()) {
            if (getinput().endsWith(".")) {

                updateCalculatorModelIsDot(false);
                updateCalculatorModelIsNumber(true);
            }

            values.delete(getinput().length() - 1, getinput().length());
            updateCalculatorModelValues(values.toString(), resultValue.toString());
            calcule(false);

        } else clear();

    }


    private String getinput() {

        return values.toString();
    }

    private boolean isEmpty() {
        return getinput().isEmpty();
    }

    //계산하기
    protected void calcule(boolean isequlclick) {

        String input = getinput();
        try {
            if (!isEmpty() && !endsWithOperatore()) {

                if (input.contains("x")) {

                    input = input.replaceAll("x", "*");

                }

                if (input.contains("\u00F7")) {

                    input = input.replaceAll("\u00F7", "/");

                }

                Expression expression = new ExpressionBuilder(input).build();
                BigDecimal result = expression.evaluate().stripTrailingZeros();

                if (result.compareTo(BigDecimal.ZERO) == 0) {

                    result = BigDecimal.valueOf(0);

                }

                if (isequlclick) {

                    values.delete(0, values.length());
                    values.append(result.toPlainString());

                    resultValue.delete(0, resultValue.length());
                    resultValue.append("");

                    updateCalculatorModelValues(values.toString(), resultValue.toString());

                    if (!result.toPlainString().contains(".")) {
                        updateCalculatorModelIsNumber(true);
                        updateCalculatorModelIsDot(false);

                    } else if (result.toPlainString().contains(".")) {
                        updateCalculatorModelIsNumber(true);
                        updateCalculatorModelIsDot(true);

                    } else {
                        updateCalculatorModelIsNumber(isNumber);
                        updateCalculatorModelIsDot(lastDot);
                    }


                } else {

                    resultValue.delete(0, resultValue.length());
                    resultValue.append(result.toPlainString());
                    updateCalculatorModelValues(values.toString(), resultValue.toString());
                }


            } else {

                resultValue.delete(0, resultValue.length());
                resultValue.append("");
                updateCalculatorModelValues(values.toString(), resultValue.toString());
            }

        } catch (Exception e) {

            updateCalculatorModelIsNumber(false);


        }
    }


    public void updateCalculatorModelValues(String value, String result) {

        calculatorModel.setValue(value);
        calculatorModel.setResult(result);
        calculatorModel.setId(1);
        calculatorViewModel.update(calculatorModel);

    }

    public void updateCalculatorModelIsNumber(boolean isNumber) {
        calculatorModel.setNumber(isNumber);
        calculatorModel.setId(1);
        calculatorViewModel.update(calculatorModel);
    }


    public void updateCalculatorModelIsDot(boolean lastDot) {
        calculatorModel.setLastDot(lastDot);
        calculatorModel.setId(1);
        calculatorViewModel.update(calculatorModel);
    }
}

