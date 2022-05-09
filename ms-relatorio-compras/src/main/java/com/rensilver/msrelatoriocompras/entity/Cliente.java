package com.rensilver.msrelatoriocompras.entity;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Comparable<Cliente> {

    private Integer id;
    private String nome;
    private String cpf;

    private List<Compra> compras = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Compra> getCompras() {
        return compras;
    }

    @Override
    public int compareTo(Cliente o) {
        return this.id - o.getId();
    }
}
