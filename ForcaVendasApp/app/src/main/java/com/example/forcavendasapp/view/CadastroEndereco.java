package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Endereco;


public class CadastroEndereco extends AppCompatActivity {

    Button btnSalvar, btnCancelar;
    EditText edLogradouro, edNumero, edBairro, edCidade, edUf, edCodigo;

    EnderecoController enderecoController = new EnderecoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);


        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);
        edLogradouro = findViewById(R.id.edLogradouro);
        edNumero = findViewById(R.id.edNumero);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edUf = findViewById(R.id.edUf);
        edCodigo = findViewById(R.id.edCodigo);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
                Intent intent = new Intent(CadastroEndereco.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEndereco();
            }
        });
    }

    private void salvarEndereco() {
        String logradouro = edLogradouro.getText().toString();
        String numero = edNumero.getText().toString();
        String bairro = edBairro.getText().toString();
        String cidade = edCidade.getText().toString();
        String uf = edUf.getText().toString();

        Endereco novoEndereco = new Endereco(logradouro, Integer.parseInt(numero), bairro, cidade, uf);

        long resultado = enderecoController.salvarEndereco(novoEndereco);

        if (resultado != -1) {
            Toast.makeText(this, "Endereço cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            limpaCampos();
        } else {
            Toast.makeText(this, "Erro ao cadastrar endereço", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpaCampos() {
        edLogradouro.setText("");
        edNumero.setText("");
        edBairro.setText("");
        edCidade.setText("");
        edUf.setText("");
    }

    public void buscaEndereco(){
        if(edCodigo != null || !edCodigo.equals("")){
            Endereco endereco = enderecoController.findByIdEndereco(Integer.parseInt(edCodigo.toString()));
            edLogradouro.setText(endereco.getLogradouro());
            edBairro.setText(endereco.getBairro());
            edNumero.setText(endereco.getNumero());
            edCidade.setText(endereco.getCidade());
            edUf.setText(endereco.getUf());
        } else {
            Toast.makeText(this, "Informe um código para buscar", Toast.LENGTH_SHORT).show();
        }
    }
}