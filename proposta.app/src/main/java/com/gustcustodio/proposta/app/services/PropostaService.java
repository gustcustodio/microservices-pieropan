package com.gustcustodio.proposta.app.services;

import com.gustcustodio.proposta.app.dtos.PropostaRequestDTO;
import com.gustcustodio.proposta.app.dtos.PropostaResponseDTO;
import com.gustcustodio.proposta.app.entities.Proposta;
import com.gustcustodio.proposta.app.mapper.PropostaMapper;
import com.gustcustodio.proposta.app.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PropostaService {

    private final PropostaMapper propostaMapper;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final PropostaRepository propostaRepository;

    @Value("${rabbitmq.proposta-pendente.exchange}")
    private String exchange;

    private void notificarRabbitMQ(Proposta proposta) {
        try {
            notificacaoRabbitService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    @Transactional
    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        Proposta proposta = propostaMapper.convertDtoToEntity(requestDTO);
        propostaRepository.save(proposta);
        notificarRabbitMQ(proposta);
        return propostaMapper.convertEntityToDto(proposta);
    }

    @Transactional
    public List<PropostaResponseDTO> obterProposta() {
        return propostaMapper.convertListEntityToListDto(propostaRepository.findAll());
    }

}