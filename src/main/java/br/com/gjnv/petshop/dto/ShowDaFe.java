package br.com.gjnv.petshop.dto;

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