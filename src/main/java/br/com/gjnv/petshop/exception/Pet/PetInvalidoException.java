package br.com.gjnv.petshop.exception.Pet;

public class PetInvalidoException extends RuntimeException {
    public PetInvalidoException() {
        super("Algum campo do pet está inválido.");
    }
}
