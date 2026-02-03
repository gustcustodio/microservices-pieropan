package com.gustcustodio.analise_credito.services.impl;

import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.services.strategy.CalculoPonto;

public class PrazoPagamentoInferiorDezAnos implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() < 120 ? 80 : 0;
    }

}
