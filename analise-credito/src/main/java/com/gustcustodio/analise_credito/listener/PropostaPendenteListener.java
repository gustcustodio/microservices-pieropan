package com.gustcustodio.analise_credito.listener;

import com.gustcustodio.analise_credito.domain.Proposta;
import com.gustcustodio.analise_credito.services.AnaliseCreditoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    private final AnaliseCreditoService analiseCreditoService;

    public PropostaPendenteListener(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        analiseCreditoService.analisar(proposta);
    }

}
