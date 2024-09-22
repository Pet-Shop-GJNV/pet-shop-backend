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
        // Lógica para cadastrar um cliente
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    @Override
    public Cliente consultarCliente(Long id) {
        // Lógica para consultar um cliente
        System.out.println("Cliente consultado com ID: " + id);
        return new Cliente(); // Retorna um cliente fictício
    }

    @Override
    public void excluirCliente(Long id) {
        // Lógica para excluir um cliente
        System.out.println("Cliente excluído com ID: " + id);
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        // Lógica para atualizar um cliente
        System.out.println("Cliente atualizado: " + cliente.getNome());
    }
// OBS: Trocar Object por Pet
    @Override
    public void cadastrarPet(Pet pet) {
        // Lógica para cadastrar um pet
        //System.out.println("Pet cadastrado: " + pet.getNome());
    }

    @Override
    public Pet consultarPet(Long id) {
        // Lógica para consultar um pet
        System.out.println("Pet consultado com ID: " + id);
       return new Pet(); // Retorna um pet fictício
    }

    @Override
    public void excluirPet(Long id) {
        // Lógica para excluir um pet
//        System.out.println("Pet excluído com ID: " + id);
    }

    @Override
    public void atualizarPet(Pet pet) {
        // Lógica para atualizar um pet
         //System.out.println("Pet atualizado: " + pet.getNome());
    }

    public void realizarAtendimento() {
        // Lógica para realizar atendimento
        System.out.println("Atendimento realizado.");
    }

    public void consultarAgenda() {
        // Lógica para consultar agenda
        System.out.println("Agenda consultada.");
    }

    public void registrarServico() {
        // Lógica para registrar serviço
        System.out.println("Serviço registrado.");
    }

    public void atualizarPagamento() {
        // Lógica para atualizar pagamento
        System.out.println("Pagamento atualizado.");
    }

    public void atualizarStatusServico() {
        // Lógica para atualizar status do serviço
        this.servicoRealizado = true;
        System.out.println("Status do serviço atualizado.");
    }
}