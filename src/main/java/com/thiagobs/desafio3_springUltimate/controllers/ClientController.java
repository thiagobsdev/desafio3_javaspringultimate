package com.thiagobs.desafio3_springUltimate.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiagobs.desafio3_springUltimate.dto.ClientDto;
import com.thiagobs.desafio3_springUltimate.entities.Client;
import com.thiagobs.desafio3_springUltimate.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {
	
	@Autowired
	ClientService service;
	
	
	@GetMapping
	public ResponseEntity<Page<Client>> findAll( Pageable pageable) {
		Page<Client> entity = service.findAll(pageable);
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client entity = service.findById(id);
		return ResponseEntity.ok(entity);
	}
	
	@PostMapping
	public ResponseEntity<Client> insert( @RequestBody Client client) {
		client = service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).body(client);
	}
	
	@PutMapping("/{id}")
	public Client  update (@PathVariable Long id, @RequestBody ClientDto dto ) {
		Client cliente = service.update(id, dto);
		
		return cliente;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete ( @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	
	

}
