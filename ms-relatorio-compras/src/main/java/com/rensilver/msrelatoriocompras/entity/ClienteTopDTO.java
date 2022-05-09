package com.rensilver.msrelatoriocompras.entity;

public class ClienteTopDTO implements Comparable<ClienteTopDTO> {

    private String nome;
    private String cpf;
    private Integer compraFrequencia;
    private Double valorTotalGasto;

    public ClienteTopDTO() {
    }

    public ClienteTopDTO(String nome, String cpf, Integer compraFrequencia, Double valorTotalGasto) {
        this.nome = nome;
        this.cpf = cpf;
        this.compraFrequencia = compraFrequencia;
        this.valorTotalGasto = valorTotalGasto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCompraFrequencia() {
        return compraFrequencia;
    }

    public void setCompraFrequencia(Integer compraFrequencia) {
        this.compraFrequencia = compraFrequencia;
    }

    public Double getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(Double valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }

    @Override
    public int compareTo(ClienteTopDTO o) {
        return this.compraFrequencia - o.getCompraFrequencia();
    }
}
