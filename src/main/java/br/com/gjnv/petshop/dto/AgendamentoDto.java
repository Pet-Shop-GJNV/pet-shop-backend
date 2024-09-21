package br.com.gjnv.petshop.dto;

public class AgendamentoDto {

    private int id;
    private int servicoId;
    private int clientId;
    private int funcionarioId;
    private boolean vagaDisponivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int tipoServicoId) {
        this.servicoId = tipoServicoId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public boolean isVagaDisponivel() {
        return vagaDisponivel;
    }

    public void setVagaDisponivel(boolean vagaDisponivel) {
        this.vagaDisponivel = vagaDisponivel;
    }
}
