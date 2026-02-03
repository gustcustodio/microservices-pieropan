package com.gustcustodio.analise_credito.services.impl;

import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.services.strategy.CalculoPonto;

public class RendaMaiorValorSolicitado implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    public boolean rendaMaiorValorSolicitado(Proposta proposta) {
        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }
}
