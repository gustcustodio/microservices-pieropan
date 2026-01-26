package com.gustcustodio.proposta.app.controllers;

import com.gustcustodio.proposta.app.dtos.PropostaRequestDTO;
import com.gustcustodio.proposta.app.dtos.PropostaResponseDTO;
import com.gustcustodio.proposta.app.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO) {
        PropostaResponseDTO responseDTO = propostaService.criar(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDTO>> obterProposta() {
        return ResponseEntity.ok(propostaService.obterProposta());
    }

}
