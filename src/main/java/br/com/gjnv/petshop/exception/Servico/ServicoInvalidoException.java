package br.com.gjnv.petshop.exception.Servico;

public class ServicoInvalidoException extends RuntimeException {
    public ServicoInvalidoException() {
        super("O tipo do serviço está inválido.");
    }
}