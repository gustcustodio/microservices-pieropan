package com.gustcustodio.analise_credito.services.impl;

import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.services.strategy.CalculoPonto;

import java.util.Random;

public class OutrosEmprestimosEmAndamentoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmprestimosEmAndamento() {
        return new Random().nextBoolean();
    }

}
