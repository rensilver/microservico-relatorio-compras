package com.rensilver.msrelatoriocompras.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rensilver.msrelatoriocompras.util.LocalDateDeserializerUtil;
import com.rensilver.msrelatoriocompras.util.LocalDateSerializerUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Compra {

    private String codigo;

    @JsonDeserialize(using = LocalDateDeserializerUtil.class)
    @JsonSerialize(using = LocalDateSerializerUtil.class)
    private LocalDate data;
    private String cliente;
    private List<Item> itens = new ArrayList<>();
    private Double valorTotal;

    public Compra() {
    }

    public Compra(String codigo, LocalDate data, String cliente, Double valorTotal) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
