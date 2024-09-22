package br.com.gjnv.petshop.dto;

import br.com.gjnv.petshop.model.Cliente;

public class PetDto {
    private Long id;
    private String nome;
    private int idade;
    private String raca;
    private Long clienteId;

    public Long getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setId(){
        this.id = id; 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Long getClienteId(){
        return clienteId;
    }

    public void setClienteId(Long clienteId){
        this.clienteId = clienteId;
    }
}
