package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.manager.IClienteManager;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "gerentes")
public class Gerente extends Funcionario implements IClienteManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String setorResponsavel;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipe_id")
    private List<Motorista> equipe;

    @Column(nullable = false)
    private double metaMensal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Gerente() {}

    public Gerente(String setorResponsavel, List<Motorista> equipe, double metaMensal, Endereco endereco) {
        this.setorResponsavel = setorResponsavel;
        this.equipe = equipe;
        this.metaMensal = metaMensal;
        this.endereco = endereco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

    public List<Motorista> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Motorista> equipe) {
        this.equipe = equipe;
    }

    public double getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(double metaMensal) {
        this.metaMensal = metaMensal;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gerente gerente = (Gerente) o;
        return Objects.equals(id, gerente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "id=" + id +
                ", setorResponsavel='" + setorResponsavel + '\'' +
                ", equipe=" + equipe +
                ", metaMensal=" + metaMensal +
                ", endereco=" + endereco +
                '}';
    }

    @Override
    public void cadastrarCliente(Cliente cliente) {
        // Implementação do método
    }

    @Override
    public Cliente consultarCliente(Long id) {
        // Implementação do método
        return null;
    }

    @Override
    public void excluirCliente(Long id) {
        // Implementação do método
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        // Implementação do método
    }
}