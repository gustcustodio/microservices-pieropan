package com.gustcustodio.proposta.app.services;

import com.gustcustodio.proposta.app.entities.Proposta;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificacaoRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
