package com.gustcustodio.analise_credito.services;

import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.exceptions.StrategyException;
import com.gustcustodio.analise_credito.services.strategy.CalculoPonto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {

    private List<CalculoPonto> calculoPontoList;
    private NotificacaoRabbitService notificacaoRabbitService;

    @Value("${rabbitmq.proposta-concluida.exchange}")
    private String exchangePropostaConcluida;

    public AnaliseCreditoService(List<CalculoPonto> calculoPontoList, NotificacaoRabbitService notificacaoRabbitService) {
        this.calculoPontoList = calculoPontoList;
        this.notificacaoRabbitService = notificacaoRabbitService;
    }

    public void analisar(Proposta proposta) {
        try {
            int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos > 350);
        } catch (StrategyException exception) {
            proposta.setAprovada(false);
            proposta.setObservacao(exception.getMessage());
        }
        notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
    }

}
