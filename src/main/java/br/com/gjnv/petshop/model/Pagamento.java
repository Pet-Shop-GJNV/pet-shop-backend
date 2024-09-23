package br.com.gjnv.petshop.model;

import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Pagamento() {
    }

    public Pagamento(double valor, Servico servico) {
        this.valor = valor;
        this.servico = servico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String pagamentoPix() {
        return "Pagamento de R$" + valor + " realizado via Pix.";
    }

    public String pagamentoDinheiro() {
        return "Pagamento de R$" + valor + " realizado em dinheiro.";
    }

    public String pagamentoCredito() {
        return "Pagamento de R$" + valor + " realizado via crédito.";
    }

    public String pagamentoDebito() {
        return "Pagamento de R$" + valor + " realizado via débito.";
    }
}
