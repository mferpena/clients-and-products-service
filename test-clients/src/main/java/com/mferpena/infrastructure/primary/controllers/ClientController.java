package com.mferpena.infrastructure.primary.controllers;

import com.mferpena.core.ClientUseCase;
import com.mferpena.infrastructure.primary.controllers.dto.CreateClientDto;
import com.mferpena.infrastructure.primary.controllers.dto.UpdateClientDto;
import com.mferpena.infrastructure.primary.controllers.mapper.ClientDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientDtoMapper clientDtoMapper;
    private final ClientUseCase clientUseCase;

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody CreateClientDto createClientDto) {
        clientUseCase.createClient(clientDtoMapper.toDomain(createClientDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> listClients() {
        return ResponseEntity.ok(clientUseCase.listClient());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        clientUseCase.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("find-by-id")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        return ResponseEntity.ok(clientUseCase.getById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@Valid @RequestBody UpdateClientDto updateClientDto) {
        clientUseCase.updateClient(clientDtoMapper.toDomain(updateClientDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
