package br.com.gjnv.petshop.exception.Pet;

public class PetIdadeInvalidaException extends RuntimeException {
    public PetIdadeInvalidaException() {
        super("A idade do pet deve ser maior ou igual a zero.");
    }
}