package br.com.gjnv.petshop.service;
import br.com.gjnv.petshop.model.Vaga;
import br.com.gjnv.petshop.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    public Vaga verificarDisponibilidade(LocalTime horario, LocalDate data) {
        return vagaRepository.findAll().stream()
                .filter(vaga -> vaga.verificarDisponibilidade(horario, data))
                .findFirst()
                .orElse(null);
    }

    public void agendarHorario(Integer id, LocalTime horario, LocalDate data) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada."));
        vaga.agendarHorario(horario, data);
        vagaRepository.save(vaga);
    }
}