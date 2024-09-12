package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
}