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

    public Atendente(String nome, String cpf, Endereco endereco, String telefone, String cnh, String veiculo, Date dataContratacao, String horarioTrabalho, String cargo, double salario) {
        super(nome, cpf, endereco, telefone, dataContratacao, horarioTrabalho, cargo, salario);
        this.servicoRealizado = false;
    }

    public Atendente() {
        super();
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
    public void cadastrarCliente(Cliente cliente) {
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    @Override
    public Cliente consultarCliente(Long id) {
        System.out.println("Cliente consultado com ID: " + id);
        return new Cliente();
    }

    @Override
    public void excluirCliente(Long id) {
        System.out.println("Cliente excluído com ID: " + id);
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        System.out.println("Cliente atualizado: " + cliente.getNome());
    }

    public void realizarAtendimento() {
        System.out.println("Atendimento realizado.");
    }

    public void consultarAgenda() {
        System.out.println("Agenda consultada.");
    }

    public void registrarServico() {
        System.out.println("Serviço registrado.");
    }

    public void atualizarPagamento() {
        System.out.println("Pagamento atualizado.");
    }

    public void atualizarStatusServico() {
        this.servicoRealizado = true;
        System.out.println("Status do serviço atualizado.");
    }

    @Override
    public void cadastrarPet(Pet pet) {
    }

    @Override
    public Pet consultarPet(Long id) {
        return null;
    }

    @Override
    public void atualizarPet(Pet pet) {
    }

    @Override
    public void excluirPet(Long id) {
    }
}