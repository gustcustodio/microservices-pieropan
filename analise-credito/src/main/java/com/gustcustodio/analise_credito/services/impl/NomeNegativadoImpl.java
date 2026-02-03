package com.gustcustodio.analise_credito.services.impl;

import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.services.strategy.CalculoPonto;

import java.util.Random;

public class NomeNegativadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) {
            throw new RuntimeException("Nome negativado");
        }
        return 100;
    }

    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }

}
