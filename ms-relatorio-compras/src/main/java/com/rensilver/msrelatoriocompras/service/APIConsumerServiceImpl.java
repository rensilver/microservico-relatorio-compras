package com.rensilver.msrelatoriocompras.service;

import com.rensilver.msrelatoriocompras.entity.Cliente;
import com.rensilver.msrelatoriocompras.entity.Compra;
import com.rensilver.msrelatoriocompras.service.exception.ExternalAPIConnectionException;
import com.rensilver.msrelatoriocompras.service.validation.ExternalAPIConnectionValidation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class APIConsumerServiceImpl implements APIConsumerService {

    private final WebClient webClient;

    public APIConsumerServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Compra> consumirAPIEndpointCompras() {
        String completeUrlCompras = "http://www.mocky.io/v2/598b16861100004905515ec7";
        if (ExternalAPIConnectionValidation.verificarConexaoAPIExterna(completeUrlCompras)) {
            Mono<List<Compra>> monoCompra = this.webClient
                    .method(HttpMethod.GET)
                    .uri("/598b16861100004905515ec7")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Compra>>() {
                    });
            return monoCompra.block();
        } else {
            throw new ExternalAPIConnectionException("Não foi possível obter dados de Compras.");
        }
    }

    @Override
    public List<Cliente> consumirAPIEndpointClientes() {
        String completeUrlClientes = "http://www.mocky.io/v2/598b16291100004705515ec5";
        if (ExternalAPIConnectionValidation.verificarConexaoAPIExterna(completeUrlClientes)) {
            Mono<List<Cliente>> monoCliente = this.webClient
                    .method(HttpMethod.GET)
                    .uri("/598b16291100004705515ec5")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Cliente>>() {
                    });
            return monoCliente.block();
        } else {
            throw new ExternalAPIConnectionException("Não foi possível obter dados de Clientes.");
        }
    }
}
