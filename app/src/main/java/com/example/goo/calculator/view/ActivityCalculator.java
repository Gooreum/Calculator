package com.example.goo.calculator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.goo.calculator.R;

/**
 * Created by Goo on 2019-07-29.
 */

public class ActivityCalculator extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new FragmentDisplay())
                .add(R.id.container_b, new FragmentCalculator())
                .commit();

    }

}
