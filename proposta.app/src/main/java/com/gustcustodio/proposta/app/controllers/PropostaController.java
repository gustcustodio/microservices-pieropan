package com.gustcustodio.proposta.app.controllers;

import com.gustcustodio.proposta.app.dtos.PropostaRequestDTO;
import com.gustcustodio.proposta.app.dtos.PropostaResponseDTO;
import com.gustcustodio.proposta.app.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO) {
        PropostaResponseDTO responseDTO = propostaService.criar(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(responseDTO.getId()).toUri();
        return  ResponseEntity.created(uri).body(responseDTO);
    }

}
