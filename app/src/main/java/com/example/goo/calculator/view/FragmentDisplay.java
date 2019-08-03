package com.example.goo.calculator.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.goo.calculator.model.CalculatorModel;
import com.example.goo.calculator.viewModel.CalculatorViewModel;
import com.example.goo.calculator.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Goo on 2019-07-30.
 */

public class FragmentDisplay extends Fragment {

    private CalculatorViewModel calculatorViewModel;
    EditText inputText;
    TextView resultText;
    CalculatorModel calculatorModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_display, container, false);

        inputText = v.findViewById(R.id.input);
        resultText = v.findViewById(R.id.result);

        // Hiding and disable keyboard
        inputText.setRawInputType(InputType.TYPE_NULL);

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

                    Log.e(TAG, "Display  CalculatorModel: " + calculatorViewModel.getAllValues().getValue().getOperatingProcess().toString());
                    Log.e(TAG, "Display  ResultValue : " + calculatorViewModel.getAllValues().getValue().getOperatingResult().toString());


                    inputText.setText(calculatorViewModel.getAllValues().getValue().getOperatingProcess().toString());
                    resultText.setText(calculatorViewModel.getAllValues().getValue().getOperatingResult().toString());
                }

            }
        });
    }
}
