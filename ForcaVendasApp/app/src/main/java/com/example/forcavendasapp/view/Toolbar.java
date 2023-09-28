package com.example.forcavendasapp.view;

import android.os.Bundle;

import com.example.forcavendasapp.databinding.ActivityToolbarBinding;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

public class Toolbar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityToolbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}