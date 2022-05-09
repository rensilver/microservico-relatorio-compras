package com.rensilver.msrelatoriocompras.service;

import com.rensilver.msrelatoriocompras.entity.*;
import com.rensilver.msrelatoriocompras.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final APIConsumerService apiConsumerService;

    public ClienteService(APIConsumerService apiConsumerService) {
        this.apiConsumerService = apiConsumerService;
    }

    public List<ClienteTopDTO> obterClientesFieis() {
        List<ClienteTopDTO> clienteTopDTOList = new ArrayList<>();
        List<Cliente> clientesObtidos = obterClientesComCompras();
        for (Cliente cliente : clientesObtidos) {
            List<Compra> compraList = cliente.getCompras();
            clienteTopDTOList.add(new ClienteTopDTO(
                    cliente.getNome(), cliente.getCpf(), cliente.getCompras().size(), somarCompras(compraList)
            ));
        }
        return clienteTopDTOList.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
    }

    public RecomendacaoVinhoDTO obterRecomendacaoParaCliente(Integer clientId, String tipo) {
        List<Cliente> clientesObtidos = obterClientesComCompras();
        if (verificarSeClienteJaComprouVinho(clientesObtidos, clientId, tipo)) {
            RecomendacaoVinhoDTO recomendacaoVinhoDTO = new RecomendacaoVinhoDTO();
            for (Cliente cliente : clientesObtidos) {
                if (cliente.getId().equals(clientId)) {
                    List<Compra> compraList = cliente.getCompras();
                    List<Item> itensList = obterListaItens(compraList);
                    recomendacaoVinhoDTO = setarDadosRecomendacaoVinho(itensList, tipo);
                }
            }
            return recomendacaoVinhoDTO;
        } else {
            throw new ResourceNotFoundException(String.format("Tipo de vinho não encontrado para o cliente %s.", clientId));
        }
    }

    private List<Cliente> obterClientesComCompras() {
        return obterComprasCliente(apiConsumerService.consumirAPIEndpointClientes(),
                apiConsumerService.consumirAPIEndpointCompras());
    }

    private List<Cliente> obterComprasCliente(List<Cliente> clientes, List<Compra> compras) {
        Set<Cliente> clientesComCompras = new TreeSet<>();
        for (Compra compraParaCliente : compras) {
            for (Cliente cliente : clientes) {
                if (compraParaCliente.getCliente().endsWith(cliente.getId().toString())) {
                    cliente.getCompras().add(compraParaCliente);
                    clientesComCompras.add(cliente);
                }
            }
        }
        return new ArrayList<>(clientesComCompras);
    }

    private double somarCompras(List<Compra> compras) {
        return compras.stream()
                    .map(Compra::getValorTotal)
                    .mapToDouble(Double::doubleValue).sum();
    }

    private List<Item> obterListaItens(List<Compra> compraList) {
        return compraList.stream()
                        .flatMap(e -> e.getItens()
                        .stream())
                        .collect(Collectors.toList());
    }

    private RecomendacaoVinhoDTO setarDadosRecomendacaoVinho(List<Item> itensList, String tipo) {
        RecomendacaoVinhoDTO recomendacaoVinhoDTO = new RecomendacaoVinhoDTO();
        for (Item obterItem : itensList) {
            if (obterItem.getVariedade().equals(tipo)) {
                recomendacaoVinhoDTO.setTipo(obterItem.getVariedade());
                recomendacaoVinhoDTO.setVinho(obterItem.getProduto());
                recomendacaoVinhoDTO.setPais(obterItem.getPais());
                recomendacaoVinhoDTO.setCategoria(obterItem.getCategoria());
                recomendacaoVinhoDTO.setSafra(obterItem.getSafra());
                recomendacaoVinhoDTO.setPreco(obterItem.getPreco());
            }
        }
        return recomendacaoVinhoDTO;
    }

    private boolean verificarSeClienteJaComprouVinho(List<Cliente> clientes, Integer clientId, String tipo) {
        boolean validaVinho = false;
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(clientId)) {
                List<Compra> compraList = cliente.getCompras();
                List<Item> itensList = obterListaItens(compraList);
                for (Item obterItem : itensList) {
                    validaVinho = obterItem.getVariedade().equals(tipo);
                }
            }
        }
        return validaVinho;
    }
}
