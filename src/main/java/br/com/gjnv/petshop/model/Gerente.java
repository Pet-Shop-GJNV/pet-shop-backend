package br.com.gjnv.petshop.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gerentes")
public class Gerente extends Funcionario {

    @Column(nullable = false)
    private String setorResponsavel;

    @OneToMany
    @JoinColumn(name = "equipe_id")
    private List<Motorista> equipe; // Alterado para uma entidade concreta

    @Column(nullable = false)
    private double metaMensal;

    public Gerente() {}

    public Gerente(String setorResponsavel, List<Motorista> equipe, double metaMensal) {
        this.setorResponsavel = setorResponsavel;
        this.equipe = equipe;
        this.metaMensal = metaMensal;
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

    public void definirMetas(double metaMensal) {
        this.metaMensal = metaMensal;
    }
    //TODO: Terminar a imoplementação dos métodos abaixo
    public void contatarFuncionario(Motorista funcionario) {
        // Implementação do método para contatar funcionário
    }

    public void demitirFuncionario(Motorista funcionario) {
        // Implementação do método para demitir funcionário
    }

    public void avaliacaoDesempenho(List<Motorista> funcionarios) {
        // Implementação do método para avaliação de desempenho
    }

    public void avaliarFuncionario(Motorista funcionario) {
        // Implementação do método para avaliar funcionário
    }

    public String gerarRelatorioVendas() {
        // Implementação do método para gerar relatório de vendas
        return "Relatório de Vendas";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gerente gerente = (Gerente) o;
        return Objects.equals(setorResponsavel, gerente.setorResponsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setorResponsavel);
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "setorResponsavel='" + setorResponsavel + '\'' +
                ", equipe=" + equipe +
                ", metaMensal=" + metaMensal +
                '}';
    }
}