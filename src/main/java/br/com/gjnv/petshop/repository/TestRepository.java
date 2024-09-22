package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Tests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Tests, UUID> {
}
