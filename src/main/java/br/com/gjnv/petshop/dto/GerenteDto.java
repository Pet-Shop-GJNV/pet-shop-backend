package br.com.gjnv.petshop.dto;

import java.util.List;

public class GerenteDto {

    private Long id;
    private String setorResponsavel;
    private List<Long> equipeIds;
    private double metaMensal;

    public GerenteDto() {}

    public GerenteDto(Long id, String setorResponsavel, List<Long> equipeIds, double metaMensal) {
        this.id = id;
        this.setorResponsavel = setorResponsavel;
        this.equipeIds = equipeIds;
        this.metaMensal = metaMensal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

    public List<Long> getEquipeIds() {
        return equipeIds;
    }

    public void setEquipeIds(List<Long> equipeIds) {
        this.equipeIds = equipeIds;
    }

    public double getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(double metaMensal) {
        this.metaMensal = metaMensal;
    }
}