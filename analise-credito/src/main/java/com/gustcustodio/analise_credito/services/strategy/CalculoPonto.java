package com.gustcustodio.analise_credito.services.strategy;

import com.gustcustodio.analise_credito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);

}
