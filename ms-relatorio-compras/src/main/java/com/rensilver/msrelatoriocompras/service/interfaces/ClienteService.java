package com.rensilver.msrelatoriocompras.service.interfaces;

import com.rensilver.msrelatoriocompras.entity.ClienteTopDTO;
import com.rensilver.msrelatoriocompras.entity.RecomendacaoVinhoDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteTopDTO> obterClientesFieis();

    RecomendacaoVinhoDTO obterRecomendacaoParaCliente(Integer clientId, String tipo);
}
