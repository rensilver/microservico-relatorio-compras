package com.rensilver.msrelatoriocompras.service.impl;

import com.rensilver.msrelatoriocompras.entity.Compra;
import com.rensilver.msrelatoriocompras.service.exception.ResourceNotFoundException;
import com.rensilver.msrelatoriocompras.service.interfaces.APIConsumerService;
import com.rensilver.msrelatoriocompras.service.interfaces.CompraService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.rensilver.msrelatoriocompras.service.validation.CompraValidation.verificarSeAnoDaCompraExiste;

@Service
public class CompraServiceImpl implements CompraService {

    private final APIConsumerService apiConsumerService;

    public CompraServiceImpl(APIConsumerService apiConsumerService) {
        this.apiConsumerService = apiConsumerService;
    }

    @Override
    public List<Compra> obterCompras() {
        return apiConsumerService.consumirAPIEndpointCompras()
                .stream()
                .sorted(Comparator.comparing(Compra::getValorTotal))
                .collect(Collectors.toList());
    }

    @Override
    public Compra obterMaiorCompraDoAno(String ano) {
        List<Compra> compraList = apiConsumerService.consumirAPIEndpointCompras();
        if (verificarSeAnoDaCompraExiste(compraList, ano)) {
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


}
