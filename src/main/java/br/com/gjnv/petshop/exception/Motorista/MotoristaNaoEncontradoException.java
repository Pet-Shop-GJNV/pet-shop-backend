package br.com.gjnv.petshop.exception.Motorista;

import java.util.UUID;

public class MotoristaNaoEncontradoException extends RuntimeException {
    public MotoristaNaoEncontradoException(UUID id) {
        super("Motorista com ID " + id + " não encontrado.");
    }
}