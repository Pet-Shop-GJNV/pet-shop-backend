package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.manager.IClienteManager;
import br.com.gjnv.petshop.manager.IPetManager;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "atendentes")
public class Atendente extends Funcionario implements IPetManager, IClienteManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean servicoRealizado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Atendente() {}

    public Atendente(String nome, String cpf, Endereco endereco, String telefone, String cnh, String veiculo, Date dataContratacao, String horarioTrabalho, String cargo, double salario ){
        super(nome, cpf, endereco, telefone, dataContratacao, horarioTrabalho, cargo, salario);
        this.servicoRealizado = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendente atendente = (Atendente) o;
        return Objects.equals(id, atendente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Atendente{" +
                "id=" + id +
                ", servicoRealizado=" + servicoRealizado +
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
//TODO:  trocar Object por Pet
    @Override
    public void cadastrarPet(Object pet) {
        // Implementação do método
    }

    @Override
    public Object consultarPet(Long id) {
        // Implementação do método
        return null;
    }
    @Override
    public void atualizarPet(Object pet) {
        // Implementação do método
    }

    @Override
    public void excluirPet(Long id) {
        // Implementação do método
    }

    public void realizarAtendimento() {
        // Implementação do método
    }

    public void consultarAgenda() {
        // Implementação do método
    }

    public void registrarServico() {
        // Implementação do método
    }

    public void atualizarPagamento() {
        // Implementação do método
    }

    public void atualizarStatusServico() {
        // Implementação do método
    }
}