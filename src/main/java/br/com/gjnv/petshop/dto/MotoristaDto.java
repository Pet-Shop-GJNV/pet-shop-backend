package br.com.gjnv.petshop.dto;

public class MotoristaDto {

    private Long id;
    private String cnh;
    private String veiculo;
    private Long rotaAtualId;

    public MotoristaDto(Long id, String cnh, String veiculo, Long rotaAtualId) {
        this.id = id;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.rotaAtualId = rotaAtualId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getRotaAtualId() {
        return rotaAtualId;
    }

    public void setRotaAtualId(Long rotaAtualId) {
        this.rotaAtualId = rotaAtualId;
    }
}