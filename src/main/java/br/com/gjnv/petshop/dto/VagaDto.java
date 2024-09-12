package br.com.gjnv.petshop.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VagaDto {

    private Integer id;
    private LocalDate data;
    private boolean horarioDisponivel;
    private LocalTime horario;
}