package br.com.gjnv.petshop.dto;

import br.com.gjnv.petshop.model.Endereco;
import java.util.Date;
import java.util.UUID;

public class FuncionarioDto {

    private UUID id;
    private String nome;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private Date dataContratacao;
    private String horarioTrabalho;
    private String cargo;
    private double salario;

    public FuncionarioDto() {}

    public FuncionarioDto(UUID id, String nome, String cpf, Endereco endereco, String telefone, Date dataContratacao, String horarioTrabalho, String cargo, double salario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataContratacao = dataContratacao;
        this.horarioTrabalho = horarioTrabalho;
        this.cargo = cargo;
        this.salario = salario;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
}
