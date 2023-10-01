package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ItemListAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;
import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;

public class Pedido extends AppCompatActivity {

    private Spinner spEndereco, spCliente, spItem;
    private ArrayList<Endereco> listaEnderecos;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Item> listaItems;

    private TextView tvQntItem;

    private RecyclerView rvListaItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        spEndereco = findViewById(R.id.spEnderecoEntrega);
        spCliente = findViewById(R.id.spCliente);
        spItem = findViewById(R.id.spItem);
        rvListaItems = findViewById(R.id.rvItems);
        tvQntItem = findViewById(R.id.tvQntItem);

        EnderecoController enderecoController = new EnderecoController(this);
        ClienteController clienteController = new ClienteController(this);
        ItemController itemController = new ItemController(this);

        listaEnderecos = enderecoController.findAllEndereco();
        listaClientes = clienteController.findAllCliente();
        listaItems = itemController.findAllItem();

        ArrayAdapter<Endereco> adapterEndereco = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEnderecos);
        adapterEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Cliente> adapterCliente = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaClientes);
        adapterCliente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Item> adapterItem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaItems);
        adapterItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEndereco.setAdapter(adapterEndereco);
        spCliente.setAdapter(adapterCliente);
        spItem.setAdapter(adapterItem);

        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item itemSelecionado = (Item) spItem.getSelectedItem();
                listaItems.add(itemSelecionado);
                atualizarListaItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void atualizarListaItems() {
        ItemListAdapter adapter = new ItemListAdapter(listaItems, this);
        rvListaItems.setLayoutManager(new LinearLayoutManager(this));

        rvListaItems.setAdapter(adapter);
        if(adapter.getItemCount()>1){
            tvQntItem.setText(adapter.getItemCount() + " itens selecionados");
        } else {
            tvQntItem.setText(adapter.getItemCount() + " item selecionado");
        }
    }




}