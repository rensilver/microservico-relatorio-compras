package com.rensilver.msrelatoriocompras.service.validation;

import com.rensilver.msrelatoriocompras.entity.Cliente;
import com.rensilver.msrelatoriocompras.entity.Compra;
import com.rensilver.msrelatoriocompras.entity.Item;

import java.util.List;
import java.util.stream.Collectors;

public final class ClienteValidation {

    public static boolean verificarSeClienteJaComprouVinho(List<Cliente> clientes, Integer clientId, String tipo) {
        boolean validaVinho = false;
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(clientId)) {
                List<Compra> compraList = cliente.getCompras();
                List<Item> itensList = compraList.stream()
                                                .flatMap(e -> e.getItens()
                                                .stream())
                                                .collect(Collectors.toList());
                for (Item obterItem : itensList) {
                    validaVinho = obterItem.getVariedade().equals(tipo);
                }
            }
        }
        return validaVinho;
    }
}
