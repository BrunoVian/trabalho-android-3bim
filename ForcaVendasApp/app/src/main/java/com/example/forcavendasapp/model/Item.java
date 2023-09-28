package com.example.forcavendasapp.model;

public class Item {

    private int codigo;
    private String descricao;
    private int vlrUnit;
    private String unMedida;

    public Item(int codigo, String descricao, int vlrUnit, String unMedida) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.vlrUnit = vlrUnit;
        this.unMedida = unMedida;
    }

    public Item() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getVlrUnit() {
        return vlrUnit;
    }

    public void setVlrUnit(int vlrUnit) {
        this.vlrUnit = vlrUnit;
    }

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }
}
