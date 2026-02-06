package com.gustcustodio.proposta.app.listener;

import com.gustcustodio.proposta.app.entities.Proposta;
import com.gustcustodio.proposta.app.repositories.PropostaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private PropostaRepository propostaRepository;

    public PropostaConcluidaListener(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        propostaRepository.findById(proposta.getId()).ifPresentOrElse(propostaNoBanco -> {
            propostaNoBanco.setAprovada(proposta.getAprovada());
            propostaNoBanco.setObservacao(proposta.getObservacao());
            propostaRepository.save(propostaNoBanco);
        }, () -> {
            throw new RuntimeException("Proposta não encontrada: " + proposta.getId());
        });
    }

}
