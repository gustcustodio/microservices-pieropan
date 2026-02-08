package com.gustcustodio.proposta.app.services;

import com.gustcustodio.proposta.app.dtos.PropostaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notificar(PropostaResponseDTO propostaResponseDTO) {
        simpMessagingTemplate.convertAndSend("/propostas", propostaResponseDTO);
    }

}
