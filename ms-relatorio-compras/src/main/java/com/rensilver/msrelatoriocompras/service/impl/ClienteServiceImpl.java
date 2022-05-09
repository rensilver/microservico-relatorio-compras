package com.rensilver.msrelatoriocompras.service.impl;

import com.rensilver.msrelatoriocompras.entity.*;
import com.rensilver.msrelatoriocompras.service.exception.ResourceNotFoundException;
import com.rensilver.msrelatoriocompras.service.interfaces.APIConsumerService;
import com.rensilver.msrelatoriocompras.service.interfaces.ClienteService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.rensilver.msrelatoriocompras.service.validation.ClienteValidation.verificarSeClienteJaComprouVinho;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final APIConsumerService apiConsumerService;

    public ClienteServiceImpl(APIConsumerService apiConsumerService) {
        this.apiConsumerService = apiConsumerService;
    }

    @Override
    public List<ClienteTopDTO> obterClientesFieis() {
        List<ClienteTopDTO> clienteTopDTOList = new ArrayList<>();
        List<Cliente> clientesObtidos = obterClientesComCompras();
        for (Cliente cliente : clientesObtidos) {
            List<Compra> compraList = cliente.getCompras();
            clienteTopDTOList.add(new ClienteTopDTO(
                    cliente.getNome(), cliente.getCpf(), cliente.getCompras().size(), somarComprasDoCliente(compraList)
            ));
        }
        return clienteTopDTOList.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public RecomendacaoVinhoDTO obterRecomendacaoParaCliente(Integer clientId, String tipo) {
        List<Cliente> clientesObtidos = obterClientesComCompras();
        if (verificarSeClienteJaComprouVinho(clientesObtidos, clientId, tipo)) {
            RecomendacaoVinhoDTO recomendacaoVinhoDTO = new RecomendacaoVinhoDTO();
            for (Cliente cliente : clientesObtidos) {
                if (cliente.getId().equals(clientId)) {
                    List<Compra> compraList = cliente.getCompras();
                    List<Item> itensList = obterListaItensCompraDoCliente(compraList);
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

    private double somarComprasDoCliente(List<Compra> compras) {
        return compras.stream()
                    .map(Compra::getValorTotal)
                    .mapToDouble(Double::doubleValue).sum();
    }

    private List<Item> obterListaItensCompraDoCliente(List<Compra> compraList) {
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
}
