package com.rensilver.msrelatoriocompras.controller;

import com.rensilver.msrelatoriocompras.entity.Compra;
import com.rensilver.msrelatoriocompras.service.CompraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/compras")
    public List<Compra> obterCompras() {
        return compraService.obterCompras();
    }

    @GetMapping("/maior-compra/{ano}")
    public Compra obterMaiorCompraDoAno(@PathVariable String ano) {
        return compraService.obterMaiorCompraDoAno(ano);
    }
}
