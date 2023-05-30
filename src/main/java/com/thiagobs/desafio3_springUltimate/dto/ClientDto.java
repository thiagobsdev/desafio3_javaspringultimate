package com.thiagobs.desafio3_springUltimate.dto;

import java.time.LocalDate;

import com.thiagobs.desafio3_springUltimate.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class ClientDto {
	
	
	private Long id;
	
	@NotBlank(message = "O nome não pode estar vazio")
	private String name;
	private String cpf;
	private Double income;
	
	@PastOrPresent(message = "A idade mão pode ser data futura")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDto( Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", name=" + name + ", cpf=" + cpf + ", income=" + income + ", birthDate="
				+ birthDate + ", children=" + children + "]";
	}
	
	

}
