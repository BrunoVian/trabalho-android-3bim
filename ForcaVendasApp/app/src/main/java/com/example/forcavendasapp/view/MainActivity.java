package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forcavendasapp.R;

public class MainActivity extends AppCompatActivity {

    private Button btnCadItem, btnCadCliente, btnCadEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCadItem = findViewById(R.id.btnCadItem);
        btnCadCliente = findViewById(R.id.btnCadCliente);
        btnCadEndereco = findViewById(R.id.btnCadEndereco);

        btnCadEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroEndereco.class);
                startActivity(intent);
            }
        });

        btnCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroCliente.class);
                startActivity(intent);
            }
        });

        btnCadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroItem.class);
                startActivity(intent);
            }
        });

    }
}