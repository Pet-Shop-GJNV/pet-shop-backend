package br.com.gjnv.petshop.model;


import jakarta.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private int idade;
    private String raca;

    // TODO trocar classe de object para cliente

    private String cliente;

    public Pet() {}

    // TODO trocar classe de object para cliente
    public Pet(long id, String nome, int idade, String raca, String cliente) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.cliente = cliente;
    }

    public long getId() {
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

    public void setId(long id) {
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

    // TODO trocar classe de object para cliente
    public String getCliente() {
        return cliente;
    }

    // TODO trocar classe de object para cliente
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}