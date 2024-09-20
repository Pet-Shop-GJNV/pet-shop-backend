package br.com.gjnv.petshop.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "motoristas")
public class Motorista extends Funcionario {

    @Column(nullable = false, unique = true)
    private String cnh;

    @Column(nullable = false)
    private String veiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rota_atual_id")
    private Endereco rotaAtual;

    public Motorista(String nome, String cpf, Endereco endereco, String telefone, String cnh, String veiculo, Date dataContratacao, String horarioTrabalho, String cargo, double salario) {
        super(nome, cpf, endereco, telefone, dataContratacao, horarioTrabalho, cargo, salario);
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.rotaAtual = null;
    }

    public Motorista() {
        super();
        this.rotaAtual = null;
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

    public Endereco getRotaAtual() {
        return rotaAtual;
    }

    public void setRotaAtual(Endereco rotaAtual) {
        this.rotaAtual = rotaAtual;
    }

    public void realizarColeta(Endereco endereco) {
        this.rotaAtual = endereco;
        System.out.println("Coleta realizada no endereço: " + endereco);
    }

    public void realizarEntrega(Endereco endereco) {
        this.rotaAtual = endereco;
        System.out.println("Entrega realizada no endereço: " + endereco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorista motorista = (Motorista) o;
        return Objects.equals(cnh, motorista.cnh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnh);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "cnh='" + cnh + '\'' +
                ", veiculo='" + veiculo + '\'' +
                ", rotaAtual=" + rotaAtual +
                '}';
    }
}