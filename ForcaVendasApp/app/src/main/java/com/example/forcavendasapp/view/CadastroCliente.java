package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;


public class CadastroCliente extends AppCompatActivity {

    private EditText edNome, edCpf, edDataNascimento;

    Button btnSalvar, btnCancelar;

    private Spinner spEndereco;
    private ArrayList<Endereco> listaEnderecos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        spEndereco = findViewById(R.id.spEndereco);
        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edDataNascimento = findViewById(R.id.edDataNascimento);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);

        EnderecoController enderecoController = new EnderecoController(this);

        listaEnderecos = enderecoController.findAllEndereco();
        ArrayAdapter<Endereco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEndereco.setAdapter(adapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente();
            }
        });

    }

    private void salvarCliente() {

        Endereco enderecoSelecionado = (Endereco) spEndereco.getSelectedItem();
        String nome = edNome.getText().toString();
        String cpf = edCpf.getText().toString();
        String dataNasc = edDataNascimento.toString();
        int codEndereco = enderecoSelecionado.getCodigo();

        if (nome.isEmpty() || edNome.equals("")) {
            edNome.setError("Informe o nome");
        }
        if (cpf.isEmpty() || edCpf.equals("")) {
            edCpf.setError("Informe o CPF");
        }
        if (dataNasc.isEmpty() || edDataNascimento.equals("")) {
            edDataNascimento.setError("Informe a data de nascimento");
        }
        else {
            ClienteController clienteController = new ClienteController(this);

            String mensagem = clienteController.validaCliente("0", nome, cpf, dataNasc, codEndereco);

            if (!mensagem.equals("")) {
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            }

            Cliente novoCliente = new Cliente(nome, cpf, dataNasc, codEndereco);

            long resultado = clienteController.salvarCliente(novoCliente);

            if (resultado != -1) {
                Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                limpaCampos();
            } else {
                Toast.makeText(this, "Erro ao cadastrar cliente", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void limpaCampos() {
        edNome.setText("");
        edCpf.setText("");
        edDataNascimento.setText("");
    }
}