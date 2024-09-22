package br.com.gjnv.petshop.dto;

import br.com.gjnv.petshop.model.Motorista;

import java.util.List;
import java.util.UUID;

public class GerenteDto {
    private UUID id;
    private String setorResponsavel;
    private List<Motorista> equipe;
    private double metaMensal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

    public List<Motorista> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Motorista> equipe) {
        this.equipe = equipe;
    }

    public double getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(double metaMensal) {
        this.metaMensal = metaMensal;
    }
}