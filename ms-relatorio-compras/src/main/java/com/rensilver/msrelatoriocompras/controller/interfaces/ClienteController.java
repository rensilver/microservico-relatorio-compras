package com.rensilver.msrelatoriocompras.controller.interfaces;

import com.rensilver.msrelatoriocompras.entity.ClienteTopDTO;
import com.rensilver.msrelatoriocompras.entity.RecomendacaoVinhoDTO;

import java.util.List;

public interface ClienteController {

    List<ClienteTopDTO> obterClientesFieis();

    RecomendacaoVinhoDTO obterRecomendacaoParaCliente(Integer clientId, String tipo);
}
