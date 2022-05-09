package com.rensilver.msrelatoriocompras.service.interfaces;

import com.rensilver.msrelatoriocompras.entity.Compra;

import java.util.List;

public interface CompraService {

    List<Compra> obterCompras();

    Compra obterMaiorCompraDoAno(String ano);
}
