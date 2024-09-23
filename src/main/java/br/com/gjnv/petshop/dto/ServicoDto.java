package br.com.gjnv.petshop.dto;

public class ServicoDto {

    private int id;
    private String tipoServico;
    private double preco;
    private int duracao;

    public ServicoDto(int id, String tipoServico, double preco, int duracao) {
        this.id = id;
        this.tipoServico = tipoServico;
        this.preco = preco;
        this.duracao = duracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
