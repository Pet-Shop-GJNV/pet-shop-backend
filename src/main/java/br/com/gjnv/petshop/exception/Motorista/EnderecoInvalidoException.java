package br.com.gjnv.petshop.exception.Motorista;

public class EnderecoInvalidoException extends RuntimeException {
    public EnderecoInvalidoException() {
        super("Endereço inválido.");
    }
}