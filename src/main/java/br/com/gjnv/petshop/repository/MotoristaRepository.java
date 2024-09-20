package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, UUID> {
}