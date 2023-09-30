package com.example.forcavendasapp.model;

import java.util.List;

public class Pedido {

    private int codigo;
    private int codPessoa;
    private List<Item> itens;
    private double vlrDesconto;
    private double vlrTotal;

    public Pedido() {
    }

    public Pedido(int codigo, int codPessoa, List<Item> itens, double vlrDesconto, double vlrTotal) {
        this.codigo = codigo;
        this.codPessoa = codPessoa;
        this.itens = itens;
        this.vlrDesconto = vlrDesconto;
        this.vlrTotal = vlrTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public double getVlrDesconto() {
        return vlrDesconto;
    }

    public void setVlrDesconto(double vlrDesconto) {
        this.vlrDesconto = vlrDesconto;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }
}
