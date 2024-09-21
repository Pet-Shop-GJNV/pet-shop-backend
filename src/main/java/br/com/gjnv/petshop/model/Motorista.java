package br.com.gjnv.petshop.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "motoristas")
public class Motorista extends Funcionario {

//    @NonNull
//TODO: verificar se é necessário, se for qual import dar
    @Column(nullable = false, unique = true)
    private String cnh;

//    @NonNull
//TODO: verificar se é necessário, se for qual import dar
    @Column(nullable = false)
    private String veiculo;

    @OneToOne
    @JoinColumn(name = "rota_atual_id")
    private Endereco rotaAtual;


    public Motorista(String nome, String cpf, Endereco endereco, String telefone, String cnh, String veiculo, Endereco rotaAtual, Date dataContratacao, String horarioTrabalho, String cargo, double salario) {
        super(nome, cpf, endereco, telefone, dataContratacao, horarioTrabalho, cargo, salario);
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.rotaAtual = rotaAtual;
    }

    public Motorista() {
        super();
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
    //TODO: Verificar se é com Object mesmo
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