package com.thiagobs.desafio3_springUltimate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagobs.desafio3_springUltimate.dto.ClientDto;
import com.thiagobs.desafio3_springUltimate.entities.Client;
import com.thiagobs.desafio3_springUltimate.repositories.ClientRepository;
import com.thiagobs.desafio3_springUltimate.services.exceptions.DataBaseException;
import com.thiagobs.desafio3_springUltimate.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		return repository.findAll(pageable);

	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
		return client;

	}

	@Transactional
	public Client insert(Client client) {
		return repository.save(client);
	}

	@Transactional
	public ClientDto update(Long id, ClientDto dto) {

		try {
			Client client = repository.getReferenceById(id);
			transferToClient(client, dto);
			return new ClientDto(repository.save(client));

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado ao atualizar");
		}
	}

	

	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}
	
	private void transferToClient(Client client, ClientDto dto) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setBirthDate(dto.getBirthDate());
		client.setIncome(dto.getIncome());
		client.setChildren(dto.getChildren());
		
	}

}
