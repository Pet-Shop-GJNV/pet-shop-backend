package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
