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
	public Page<ClientDto> findAll(Pageable pageable) {
		Page<Client> result = repository.findAll(pageable);
		return result.map(x -> new ClientDto(x));

	}

	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Client client = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id não encontrado"));
		ClientDto dto = new ClientDto(client);
		return dto;

	}

	@Transactional
	public ClientDto insert(ClientDto dto) {
		Client client = new Client();
		transferToClient(client, dto);
		
		return new ClientDto(client);
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
			throw new ResourceNotFoundException("Id não encontrado");
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
