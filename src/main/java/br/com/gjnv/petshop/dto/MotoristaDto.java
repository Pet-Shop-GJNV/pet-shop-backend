package br.com.gjnv.petshop.dto;

import java.util.UUID;

public class MotoristaDto extends FuncionarioDto{

    private UUID id;
    private String cnh;
    private String veiculo;
    private UUID rotaAtualId;

    public MotoristaDto() {}

    public MotoristaDto(UUID id, String cnh, String veiculo, UUID rotaAtualId) {
        this.id = id;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.rotaAtualId = rotaAtualId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public UUID getRotaAtualId() {
        return rotaAtualId;
    }

    public void setRotaAtualId(UUID rotaAtualId) {
        this.rotaAtualId = rotaAtualId;
    }
}