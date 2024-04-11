package com.crud.rest.controllers;

import com.crud.rest.dtos.ClientDTO;
import com.crud.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping("")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO request){
        var savedUser = service.create(request);
        return ResponseEntity.ok().body(savedUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO request){
        var updatedClient = service.update(id, request);
        return ResponseEntity.ok().body(updatedClient);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
         service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        var foundUser = service.findById(id);
        return ResponseEntity.ok().body(foundUser);
    }

    @GetMapping("")
    public ResponseEntity<Page<ClientDTO>> allProducts(Pageable pageable) {
        var clients = service.findAll(pageable);
        return ResponseEntity.ok(clients);
    }
}
