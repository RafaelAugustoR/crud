package com.crud.services;

import com.crud.entities.Client;
import com.crud.exceptions.ResourceNotFoundException;
import com.crud.repositories.ClientRepository;
import com.crud.rest.dtos.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public ClientDTO create(ClientDTO request) {
        Client client = Client.builder()
                .name(request.getName())
                .income(request.getIncome())
                .cpf(request.getCpf())
                .birthDate(request.getBirthDate())
                .children(request.getChildren())
                .build();

        Client savedProduct = repository.save(client);

        return ClientDTO.builder()
                .name(savedProduct.getName())
                .income(savedProduct.getIncome())
                .cpf(savedProduct.getCpf())
                .birthDate(savedProduct.getBirthDate())
                .children(savedProduct.getChildren())
                .build();
    }

    public ClientDTO update(Long id, ClientDTO request) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for id: " + id));

        client.setName(request.getName());
        client.setCpf(request.getCpf());
        client.setIncome(request.getIncome());
        client.setBirthDate(request.getBirthDate());
        client.setChildren(request.getChildren());

        Client updatedProduct = repository.save(client);

        return ClientDTO.builder()
                .name(updatedProduct.getName())
                .income(updatedProduct.getIncome())
                .cpf(updatedProduct.getCpf())
                .birthDate(updatedProduct.getBirthDate())
                .children(updatedProduct.getChildren())
                .build();

    }


    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {

        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        return ClientDTO.builder()
                .name(client.getName())
                .income(client.getIncome())
                .cpf(client.getCpf())
                .birthDate(client.getBirthDate())
                .children(client.getChildren())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(ClientDTO::new);
    }
}
