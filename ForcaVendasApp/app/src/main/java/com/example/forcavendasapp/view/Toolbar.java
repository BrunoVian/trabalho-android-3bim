package com.example.forcavendasapp.view;

import android.os.Bundle;

import com.example.forcavendasapp.databinding.ActivityToolbarBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.forcavendasapp.view.databinding.ActivityToolbarBinding;

import com.example.forcavendasapp.R;

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