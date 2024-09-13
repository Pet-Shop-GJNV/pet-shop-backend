package br.com.gjnv.petshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;

    private boolean horarioDisponivel;

    private LocalTime horario;

    public boolean verificarDisponibilidade(LocalTime horario, LocalDate data) {
        return this.horario.equals(horario) && this.data.equals(data) && this.horarioDisponivel;
    }

    public void agendarHorario(LocalTime horario, LocalDate data) {
        if (verificarDisponibilidade(horario, data)) {
            this.horarioDisponivel = false;
        } else {
            throw new IllegalStateException("Horário não disponível para agendamento.");
        }
    }
}