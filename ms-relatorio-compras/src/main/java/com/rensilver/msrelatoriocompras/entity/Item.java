package com.rensilver.msrelatoriocompras.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Item implements Comparable<Item> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String codigo;
    private String produto;
    private String variedade;
    private String pais;
    private String categoria;
    private String safra;
    private Double preco;

    public Item() {
    }

    public Item(String codigo, String produto, String variedade, String pais, String categoria, String safra, Double preco) {
        this.codigo = codigo;
        this.produto = produto;
        this.variedade = variedade;
        this.pais = pais;
        this.categoria = categoria;
        this.safra = safra;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getVariedade() {
        return variedade;
    }

    public void setVariedade(String variedade) {
        this.variedade = variedade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public int compareTo(Item o) {
        return this.codigo.compareTo(o.codigo);
    }
}
