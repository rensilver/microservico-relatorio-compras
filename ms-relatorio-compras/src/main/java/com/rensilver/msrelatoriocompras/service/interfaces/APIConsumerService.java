package com.rensilver.msrelatoriocompras.service.interfaces;

import com.rensilver.msrelatoriocompras.entity.Cliente;
import com.rensilver.msrelatoriocompras.entity.Compra;

import java.util.List;

public interface APIConsumerService {

    List<Compra> consumirAPIEndpointCompras();

    List<Cliente> consumirAPIEndpointClientes();
}
