package com.rensilver.msrelatoriocompras.controller;

import com.rensilver.msrelatoriocompras.entity.ClienteTopDTO;
import com.rensilver.msrelatoriocompras.entity.RecomendacaoVinhoDTO;
import com.rensilver.msrelatoriocompras.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes-fieis")
    public List<ClienteTopDTO> obterClientesFieis() {
        return clienteService.obterClientesFieis();
    }

    @GetMapping("/recomendacao/{clientId}/{tipo}")
    public RecomendacaoVinhoDTO obterRecomendacaoParaCliente(@PathVariable Integer clientId, @PathVariable String tipo) {
        return clienteService.obterRecomendacaoParaCliente(clientId, tipo);
    }
}
