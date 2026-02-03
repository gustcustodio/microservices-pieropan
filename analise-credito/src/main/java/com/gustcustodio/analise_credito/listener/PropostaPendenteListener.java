package com.gustcustodio.analise_credito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    @RabbitListener(queues = "{rabbitmq.queue.proposta.pendente}")
    public void propostaPendente() {

    }

}
