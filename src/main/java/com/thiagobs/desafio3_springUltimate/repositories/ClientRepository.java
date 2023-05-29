package com.thiagobs.desafio3_springUltimate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagobs.desafio3_springUltimate.entities.Client;

@Repository
public interface ClientRepository extends  JpaRepository<Client, Long> {



}
