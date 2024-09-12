package br.com.gjnv.petshop.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public class VagaDto {

    private Integer id;
    private LocalDate data;
    private boolean horarioDisponivel;
    private LocalTime horario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(boolean horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}