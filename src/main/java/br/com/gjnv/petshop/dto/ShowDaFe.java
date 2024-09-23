package br.com.gjnv.petshop.dto;

// TODO verificar implicações da mudança do nome dessa bagaça (nome pode ser GuardaPet ou algo proximo)
public record ShowDaFe(String nome, int idade, String raca, Long clienteId) {
    public ShowDaFe {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa");
        }
        if (raca == null || raca.isBlank()) {
            throw new IllegalArgumentException("Raça não pode ser nula ou vazia");
        }
    }
}