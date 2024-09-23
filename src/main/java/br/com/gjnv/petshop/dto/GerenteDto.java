package br.com.gjnv.petshop.dto;

import br.com.gjnv.petshop.model.Endereco;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GerenteDto extends FuncionarioDto {

    private String setorResponsavel;
    private List<UUID> atendentesIds;
    private List<UUID> motoristasIds;
    private double metaMensal;
    private Long enderecoId;
    private Endereco endereco;

    public GerenteDto() {}

    public GerenteDto(String setorResponsavel, List<UUID> atendentesIds, List<UUID> motoristasIds, double metaMensal) {
        this.setorResponsavel = setorResponsavel;
        this.atendentesIds = atendentesIds;
        this.motoristasIds = motoristasIds;
        this.metaMensal = metaMensal;
    }

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

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
