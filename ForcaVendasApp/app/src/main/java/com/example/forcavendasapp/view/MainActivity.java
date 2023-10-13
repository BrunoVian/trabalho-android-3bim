package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.controller.ItemController;

public class MainActivity extends AppCompatActivity {

    private Button btnCadItem, btnCadCliente, btnCadEndereco, btnPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCadItem = findViewById(R.id.btnCadItem);
        btnCadCliente = findViewById(R.id.btnCadCliente);
        btnCadEndereco = findViewById(R.id.btnCadEndereco);
        btnPedido = findViewById(R.id.btnNovoPedido);

        EnderecoController enderecoController = new EnderecoController(this);
        ClienteController clienteController = new ClienteController(this);
        ItemController itemController = new ItemController(this);

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

                if (enderecoController.findAllEndereco().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Atenção! Falta Cadastrar Endereços Para Continuar!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CadastroCliente.class);
                    startActivity(intent);
                }
            }
        });

        btnCadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroItem.class);
                startActivity(intent);
            }
        });

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (enderecoController.findAllEndereco().isEmpty() || clienteController.findAllCliente().isEmpty() || itemController.findAllItem().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Atenção! Faltam Cadastros Para Continuar", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Pedido.class);
                    startActivity(intent);
                }
            }
        });

    }
}