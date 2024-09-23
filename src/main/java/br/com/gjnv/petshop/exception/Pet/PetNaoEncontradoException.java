package br.com.gjnv.petshop.exception.Pet;

public class PetNaoEncontradoException extends RuntimeException {
    public PetNaoEncontradoException(Long id) {
        super("Pet com ID " + id + " não encontrado.");
    }
}
