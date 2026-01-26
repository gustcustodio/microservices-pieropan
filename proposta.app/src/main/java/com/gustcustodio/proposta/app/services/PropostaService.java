package com.gustcustodio.proposta.app.services;

import com.gustcustodio.proposta.app.dtos.PropostaRequestDTO;
import com.gustcustodio.proposta.app.dtos.PropostaResponseDTO;
import com.gustcustodio.proposta.app.entities.Proposta;
import com.gustcustodio.proposta.app.mapper.PropostaMapper;
import com.gustcustodio.proposta.app.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PropostaService {

    private final PropostaMapper propostaMapper;
    private final PropostaRepository propostaRepository;

    @Transactional
    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        Proposta entity = propostaMapper.convertDtoToEntity(requestDTO);
        propostaRepository.save(entity);
        return propostaMapper.convertEntityToDto(entity);
    }

    @Transactional
    public List<PropostaResponseDTO> obterProposta() {
        return propostaMapper.convertListEntityToListDto(propostaRepository.findAll());
    }

}