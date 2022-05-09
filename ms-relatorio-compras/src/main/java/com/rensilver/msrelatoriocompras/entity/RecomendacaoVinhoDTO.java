package com.rensilver.msrelatoriocompras.entity;

public class RecomendacaoVinhoDTO {

    private String tipo;
    private String vinho;
    private String pais;
    private String categoria;
    private String safra;
    private Double preco;

    public RecomendacaoVinhoDTO() {
    }

    public RecomendacaoVinhoDTO(String tipo, String vinho, String pais, String categoria, String safra, Double preco) {
        this.tipo = tipo;
        this.vinho = vinho;
        this.pais = pais;
        this.categoria = categoria;
        this.safra = safra;
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVinho() {
        return vinho;
    }

    public void setVinho(String vinho) {
        this.vinho = vinho;
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
}
