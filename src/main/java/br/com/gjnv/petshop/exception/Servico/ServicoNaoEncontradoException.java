package br.com.gjnv.petshop.exception.Servico;

public class ServicoNaoEncontradoException extends RuntimeException {
    public ServicoNaoEncontradoException(int id) {
        super("Serviço com ID " + id + " não encontrado.");
    }
}
