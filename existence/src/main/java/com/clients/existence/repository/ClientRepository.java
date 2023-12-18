package com.clients.existence.repository;

import com.clients.existence.entity.Client;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@EnableAutoConfiguration
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}
