package com.gustcustodio.analise_credito.services.impl;

import com.gustcustodio.analise_credito.constants.MensagemConstante;
import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.exceptions.StrategyException;
import com.gustcustodio.analise_credito.services.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class NomeNegativadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) {
            throw new StrategyException(String.format(MensagemConstante.CLIENTE_NEGATIVADO, proposta.getUsuario().getNome()));
        }
        return 100;
    }

    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }

}
