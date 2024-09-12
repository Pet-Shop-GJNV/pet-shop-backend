package br.com.gjnv.petshop.model;
<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public abstract class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String cpf;

//    @Embedded
//    private Endereco endereco;

=======

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class Funcionario {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(nullable = false)
>>>>>>> fb6344f81df0d8abf6f8bcf7a715ac897757c733
    private String telefone;

    @Temporal(TemporalType.DATE)
    private Date dataContratacao;

<<<<<<< HEAD
    private String horarioTrabalho;

    private String cargo;

    private double salario;

    public abstract double calcularSalario();

    public void registrarPonto() {
        System.out.println("Ponto registrado para o funcionário: " + nome);
    }

    public void visualizarDadosPessoais() {
        System.out.println("Dados pessoais: " + nome + ", " + cpf);
    }

    public void atualizarDadosPessoais(String nome, Endereco endereco, String telefone) {
        this.nome = nome;
//        this.endereco = endereco;
        this.telefone = telefone;
    }
=======
    @Column(name = "horario_trabalho", nullable = false)
    private String horarioTrabalho;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private double salario;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
>>>>>>> fb6344f81df0d8abf6f8bcf7a715ac897757c733
}
