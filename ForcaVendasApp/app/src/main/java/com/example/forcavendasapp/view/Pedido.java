package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ItemListAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;
import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends AppCompatActivity {

    private Spinner spEndereco, spCliente, spItem;
    private ArrayList<Endereco> listaEnderecos;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Item> listaItems;

    private ArrayList<Item> listaItemsSelecionados = new ArrayList<>();

    private LinearLayout llAPrazo;
    private TextView tvQntItem, tvTotalItensLista, tvTotal, tvTitleParcelas, tvParcelas, tvTotalFrete;

    private RecyclerView rvListaItems;
    private Button btGerarParcelas, btSalvar, btCancelar;
    private EditText edQuantParcelas;

    private RadioGroup rgFormaPgt;

    private RadioButton rbAVista, rbAPrazo;

    private double valorTotal = 0;
    private double valorTotalCondicao = 0;
    private double valorFrete = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        spEndereco = findViewById(R.id.spEnderecoEntrega);
        spCliente = findViewById(R.id.spCliente);
        spItem = findViewById(R.id.spItem);
        rvListaItems = findViewById(R.id.rvItems);
        tvQntItem = findViewById(R.id.tvQntItem);
        tvTotalItensLista = findViewById(R.id.tvTotalLista);
        rbAPrazo = findViewById(R.id.rbAPrazo);
        rbAVista = findViewById(R.id.rbAVista);
        tvTotal = findViewById(R.id.tvTotal);
        llAPrazo = findViewById(R.id.llAPrazo);
        btGerarParcelas = findViewById(R.id.btGerarParcelas);
        edQuantParcelas = findViewById(R.id.edQuantParcelas);
        tvTitleParcelas = findViewById(R.id.tvTitleParcelas);
        tvParcelas = findViewById(R.id.tvParcelas);
        rgFormaPgt = findViewById(R.id.rgFormaPgt);
        tvTotalFrete = findViewById(R.id.tvTotalFrete);
        btSalvar = findViewById(R.id.btnSalvar);
        btCancelar = findViewById(R.id.btnCancelar);

        rgFormaPgt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                edQuantParcelas.setText(null);

                if (rbAVista.isChecked()) {
                    llAPrazo.setVisibility(View.GONE);
                    tvTitleParcelas.setVisibility(View.GONE);
                    tvParcelas.setVisibility(View.GONE);
                    calculaTotalPedidoCondicoes();
                    tvParcelas.setText("");
                } else if (rbAPrazo.isChecked()) {
                    llAPrazo.setVisibility(View.VISIBLE);
                    tvTitleParcelas.setVisibility(View.VISIBLE);
                    tvParcelas.setVisibility(View.VISIBLE);
                    calculaTotalPedidoCondicoes();
                }
            }
        });

        btGerarParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qntParcelas = Integer.parseInt(edQuantParcelas.getText().toString());

                if (qntParcelas == 0) {
                    edQuantParcelas.setError("Informe uma quantidade de parcelas válidas.");
                } else {
                    Double valorParcela = valorTotalCondicao / qntParcelas;

                    String parcelas = "";

                    for (int i = 0; i < qntParcelas; i++) {
                        parcelas += "Parcela " + (i + 1) + " - R$ - " + valorParcela + "\n";
                    }

                    tvParcelas.setText(parcelas);
                    parcelas = "";
                }

            }
        });

        Item selecioneItem = new Item();
        selecioneItem.setDescricao("Selecione um Item");

        Endereco selecioneEndereco = new Endereco();
        selecioneEndereco.setCidade("Selecione um Endereço");

        Cliente selecioneCliente = new Cliente();
        selecioneCliente.setNome("Selecione um Cliente");

        EnderecoController enderecoController = new EnderecoController(this);
        ClienteController clienteController = new ClienteController(this);
        ItemController itemController = new ItemController(this);

        listaEnderecos = enderecoController.findAllEndereco();
        listaClientes = clienteController.findAllCliente();
        listaItems = itemController.findAllItem();

        listaItems.add(0, selecioneItem);
        listaEnderecos.add(0, selecioneEndereco);
        listaClientes.add(0, selecioneCliente);


        ArrayAdapter<Endereco> adapterEndereco = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaEnderecos);
        adapterEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Cliente> adapterCliente = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaClientes);
        adapterCliente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Item> adapterItem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaItems);
        adapterItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEndereco.setAdapter(adapterEndereco);
        spCliente.setAdapter(adapterCliente);
        spItem.setAdapter(adapterItem);

        Endereco enderecoSelecionado = (Endereco) spEndereco.getSelectedItem();

        spEndereco.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Endereco enderecoSelecionado = (Endereco) spEndereco.getItemAtPosition(position);
                System.out.println("Endereco: " + enderecoSelecionado.getCidade());
                if(position > 1){
                    if ("Toledo".equals(enderecoSelecionado.getCidade())) {
                        valorFrete = 0;
                        tvTotalFrete.setText("Frete Grátis");
                    } else {
                        valorFrete = 20;
                        tvTotalFrete.setText("Frete: R$: 20,00");
                    }
                    calculaTotalPedido();
                    calculaTotalPedidoCondicoes();
                } else {
                    valorFrete = 0;
                    calculaTotalPedido();
                    calculaTotalPedidoCondicoes();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Item itemSelecionado = (Item) spItem.getItemAtPosition(position);
                    listaItemsSelecionados.add(itemSelecionado);
                    atualizarListaItems();
                    calculaTotalPedido();
                    calculaTotalPedidoCondicoes();
                    spItem.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPedido();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pedido.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void atualizarListaItems() {
        ItemListAdapter adapter = new ItemListAdapter(listaItemsSelecionados, this);
        rvListaItems.setLayoutManager(new LinearLayoutManager(this));

        rvListaItems.setAdapter(adapter);
        if (adapter.getItemCount() > 1) {
            tvQntItem.setText(adapter.getItemCount() + " itens selecionados");
        } else {
            tvQntItem.setText(adapter.getItemCount() + " item selecionado");
        }
    }

    private void calculaTotalPedido() {

        valorTotal = 0;

        for (Item listaItemsSelecionado : listaItemsSelecionados) {
            valorTotal += listaItemsSelecionado.getVlrUnit();
        }

        tvTotalItensLista.setText("Total dos Itens R$ " + valorTotal);


    }

    private void calculaTotalPedidoCondicoes() {

        valorTotalCondicao = 0;

        if (rbAVista.isChecked()) {
            valorTotalCondicao = valorTotal - (valorTotal * 0.05) + valorFrete;
        } else if (rbAPrazo.isChecked()) {
            valorTotalCondicao = valorTotal + (valorTotal * 0.05) + valorFrete;
        }

        if (valorTotalCondicao == 0) {
            tvTotal.setText("Selecione uma condição.");
        } else {
            tvTotal.setText("R$ " + valorTotalCondicao);
        }

    }

    private void salvarPedido() {



        if(listaItemsSelecionados.isEmpty()){
            Toast.makeText(this, "Informe os itens", Toast.LENGTH_SHORT).show();
        }
        if(spCliente.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Informe o cliente", Toast.LENGTH_SHORT).show();
        }
        if(spEndereco.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Informe o Endereço de Entrega", Toast.LENGTH_SHORT).show();
        }
        if(valorTotalCondicao == 0){
            Toast.makeText(this, "Selecione a Condição de Pagamento", Toast.LENGTH_SHORT).show();
        }
        else {
            com.example.forcavendasapp.model.Pedido nvPedido = new com.example.forcavendasapp.model.Pedido();

            Cliente cliente = (Cliente) spCliente.getSelectedItem();

            nvPedido.setCodPessoa(Integer.valueOf(String.valueOf(cliente.getCodigo())));
            nvPedido.setItens(listaItemsSelecionados);
            nvPedido.setVlrTotal(valorTotalCondicao);
        }

    }

}