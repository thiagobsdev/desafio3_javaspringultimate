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
import com.thiagobs.desafio3_springUltimate.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clients")
public class ClientController {
	
	@Autowired
	ClientService service;
	
	
	@GetMapping
	public ResponseEntity<Page<ClientDto>> findAll( Pageable pageable) {
		Page<ClientDto> entity = service.findAll(pageable);
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable (required = true) Long id ) {
		ClientDto entity = service.findById(id);
		return ResponseEntity.ok(entity);
	}
	
	@PostMapping
	public ResponseEntity<ClientDto> insert( @Valid  @RequestBody ClientDto dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDto>  update (@PathVariable(required = true) Long id, @Valid @RequestBody ClientDto dto ) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete ( @PathVariable (required = true) Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	
	

}
