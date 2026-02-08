package com.gustcustodio.proposta.app.listener;

import com.gustcustodio.proposta.app.dtos.PropostaResponseDTO;
import com.gustcustodio.proposta.app.entities.Proposta;
import com.gustcustodio.proposta.app.mapper.PropostaMapper;
import com.gustcustodio.proposta.app.repositories.PropostaRepository;
import com.gustcustodio.proposta.app.services.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private WebSocketService webSocketService;
    private PropostaRepository propostaRepository;

    public PropostaConcluidaListener(WebSocketService webSocketService, PropostaRepository propostaRepository) {
        this.webSocketService = webSocketService;
        this.propostaRepository = propostaRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        propostaRepository.findById(proposta.getId()).ifPresentOrElse(propostaNoBanco -> {
            propostaNoBanco.setAprovada(proposta.getAprovada());
            propostaNoBanco.setObservacao(proposta.getObservacao());
            propostaRepository.save(propostaNoBanco);
            webSocketService.notificar(PropostaMapper.INSTANCE.convertEntityToDto(propostaNoBanco));
        }, () -> {
            throw new RuntimeException("Proposta não encontrada: " + proposta.getId());
        });
    }

}
