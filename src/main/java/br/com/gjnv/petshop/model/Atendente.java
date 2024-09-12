//package br.com.gjnv.petshop.model;
//
//import jakarta.persistence.*;
//import java.util.List;
//import java.util.Objects;
//
//@Entity
//@Table(name = "atendentes")
//public class Atendente extends Funcionario {
//
//    @OneToMany
//    @JoinColumn(name = "cliente_id")
//    private List<Objects> clientesAtendidos;
//
//    public Atendente() {}
//    //TODO: Trocar Objects por Cliente
//    public Atendente(List<Objects> clientesAtendidos) {
//        this.clientesAtendidos = clientesAtendidos;
//    }
//
//    public List<Objects> getClientesAtendidos() {
//        return clientesAtendidos;
//    }
//
//    public void setClientesAtendidos(List<Objects> clientesAtendidos) {
//        this.clientesAtendidos = clientesAtendidos;
//    }
//
//    public void realizarAtendimento() {
//        // Implementação do método para realizar atendimento
//    }
//
//    public void consultarAgenda() {
//        // Implementação do método para consultar agenda
//    }
//
//    public void registrarServico() {
//        // Implementação do método para registrar serviço
//    }
//
//    public void atualizarPagamento() {
//        // Implementação do método para atualizar pagamento
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Atendente atendente = (Atendente) o;
//        return Objects.equals(clientesAtendidos, atendente.clientesAtendidos);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(clientesAtendidos);
//    }
//
//    @Override
//    public String toString() {
//        return "Atendente{" +
//                "clientesAtendidos=" + clientesAtendidos +
//                '}';
//    }
//}