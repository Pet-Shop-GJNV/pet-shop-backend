package br.com.gjnv.petshop.dto;

import java.util.List;
import java.util.UUID;

public class GerenteDto extends FuncionarioDto {

    private String setorResponsavel;
    private List<UUID> atendentesIds;
    private List<UUID> motoristasIds;
    private double metaMensal;


    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

    public List<UUID> getAtendentesIds() {
        return atendentesIds;
    }

    public void setAtendentesIds(List<UUID> atendentesIds) {
        this.atendentesIds = atendentesIds;
    }

    public List<UUID> getMotoristasIds() {
        return motoristasIds;
    }

    public void setMotoristasIds(List<UUID> motoristasIds) {
        this.motoristasIds = motoristasIds;
    }

    public double getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(double metaMensal) {
        this.metaMensal = metaMensal;
    }
}
