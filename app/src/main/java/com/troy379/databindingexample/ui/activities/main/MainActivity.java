package com.troy379.databindingexample.ui.activities.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.troy379.databindingexample.R;
import com.troy379.databindingexample.Demo;
import com.troy379.databindingexample.data.models.User;
import com.troy379.databindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT_ACTIVITY = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, LAYOUT_ACTIVITY);

        User user = Demo.getUser();
        binding.setViewModel(new ProfileViewModel(this, user));
    }
}
