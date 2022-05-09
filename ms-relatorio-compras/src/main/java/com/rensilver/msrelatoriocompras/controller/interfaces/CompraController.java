package com.rensilver.msrelatoriocompras.controller.interfaces;

import com.rensilver.msrelatoriocompras.entity.Compra;

import java.util.List;

public interface CompraController {

    List<Compra> obterCompras();

    Compra obterMaiorCompraDoAno( String ano);
}
