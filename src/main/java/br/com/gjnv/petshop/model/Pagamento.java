package br.com.gjnv.petshop.model;

import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pagamento() {
    }

    public Pagamento(double valor, Cliente cliente) {
        this.valor = valor;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
