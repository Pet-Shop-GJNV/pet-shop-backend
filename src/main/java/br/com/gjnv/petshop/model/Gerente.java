package br.com.gjnv.petshop.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Gerente extends Funcionario {

    @Column(nullable = false)
    private String setorResponsavel;

    @OneToMany
    @JoinTable(
            name = "gerente_motoristas",
            joinColumns = @JoinColumn(name = "gerente_id"),
            inverseJoinColumns = @JoinColumn(name = "motorista_id")
    )
    private List<Motorista> motoristas;

    @OneToMany
    @JoinTable(
            name = "gerente_atendentes",
            joinColumns = @JoinColumn(name = "gerente_id"),
            inverseJoinColumns = @JoinColumn(name = "atendente_id")
    )
    private List<Atendente> atendentes;

    @Column(nullable = false)
    private double metaMensal;

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }

    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(List<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    public List<Atendente> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<Atendente> atendentes) {
        this.atendentes = atendentes;
    }

    public double getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(double metaMensal) {
        this.metaMensal = metaMensal;
    }
}
