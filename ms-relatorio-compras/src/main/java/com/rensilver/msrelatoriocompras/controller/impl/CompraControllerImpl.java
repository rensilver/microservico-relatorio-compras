package com.rensilver.msrelatoriocompras.controller.impl;

import com.rensilver.msrelatoriocompras.controller.interfaces.CompraController;
import com.rensilver.msrelatoriocompras.entity.Compra;
import com.rensilver.msrelatoriocompras.service.impl.CompraServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraControllerImpl implements CompraController {

    private final CompraServiceImpl compraServiceImpl;

    public CompraControllerImpl(CompraServiceImpl compraServiceImpl) {
        this.compraServiceImpl = compraServiceImpl;
    }

    @GetMapping("/compras")
    @Override
    public List<Compra> obterCompras() {
        return compraServiceImpl.obterCompras();
    }

    @GetMapping("/maior-compra/{ano}")
    @Override
    public Compra obterMaiorCompraDoAno(@PathVariable String ano) {
        return compraServiceImpl.obterMaiorCompraDoAno(ano);
    }
}
