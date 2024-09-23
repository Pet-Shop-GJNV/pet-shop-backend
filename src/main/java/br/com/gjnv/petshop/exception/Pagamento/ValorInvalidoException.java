package br.com.gjnv.petshop.exception.Pagamento;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException() {
        super("Valor inválido.");
    }
}