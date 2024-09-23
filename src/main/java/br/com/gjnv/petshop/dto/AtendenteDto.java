package br.com.gjnv.petshop.dto;

import br.com.gjnv.petshop.model.Endereco;

import java.util.UUID;

public class AtendenteDto {

    private UUID id;
    private String setorAtendimento;
    private boolean servicoRealizado;
    private Endereco endereco;

    public AtendenteDto() {
    }

    public AtendenteDto(UUID id, String setorAtendimento, boolean servicoRealizado, Endereco endereco) {
        this.id = id;
        this.setorAtendimento = setorAtendimento;
        this.servicoRealizado = servicoRealizado;
        this.endereco = endereco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSetorAtendimento() {
        return setorAtendimento;
    }

    public void setSetorAtendimento(String setorAtendimento) {
        this.setorAtendimento = setorAtendimento;
    }

    public boolean isServicoRealizado() {
        return servicoRealizado;
    }

    public void setServicoRealizado(boolean servicoRealizado) {
        this.servicoRealizado = servicoRealizado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}