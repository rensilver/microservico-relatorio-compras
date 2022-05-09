package com.rensilver.msrelatoriocompras.service;

import com.rensilver.msrelatoriocompras.entity.Compra;
import com.rensilver.msrelatoriocompras.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final APIConsumerService apiConsumerService;

    public CompraService(APIConsumerService apiConsumerService) {
        this.apiConsumerService = apiConsumerService;
    }

    public List<Compra> obterCompras() {
        return apiConsumerService.consumirAPIEndpointCompras()
                .stream()
                .sorted(Comparator.comparing(Compra::getValorTotal))
                .collect(Collectors.toList());
    }

    public Compra obterMaiorCompraDoAno(String ano) {
        List<Compra> compraList = apiConsumerService.consumirAPIEndpointCompras();
        if (verificarSeAnoExiste(compraList, ano)) {
            List<Compra> anoFiltrado = new ArrayList<>();
            for (Compra compra : compraList) {
                if (compra.getData().getYear() == Integer.parseInt(ano)) {
                    anoFiltrado.add(compra);
                }
            }
            return anoFiltrado
                    .stream()
                    .max(Comparator.comparing(Compra::getValorTotal))
                    .orElse(null);
        } else {
            throw new ResourceNotFoundException(String.format("Ano %s não encontrado.", ano));
        }
    }

    private boolean verificarSeAnoExiste(List<Compra> compras, String ano) {
        boolean isAno = false;
        for (Compra compra : compras) {
               isAno = compra.getData().getYear() == Integer.parseInt(ano);
               if (isAno) {
                   break;
               }
        }
        return isAno;
    }
}
