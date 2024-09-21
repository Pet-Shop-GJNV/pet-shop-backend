package br.com.gjnv.petshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gjnv.petshop.model.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, UUID> {
}