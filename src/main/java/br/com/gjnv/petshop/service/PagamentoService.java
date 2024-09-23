package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.exception.Pagamento.ValorInvalidoException;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    public PagamentoRepository pagamentoRepository;

    public boolean pagamentoPix(double valor, Servico servico) {
        if (servico.getPreco() != valor) {
            throw new ValorInvalidoException();
        }
        return true;
    }

    public String pagamentoDinheiro(double valor) {
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
        return "Efetue pagamento no estabelecimento. Valor: " + valor;
    }

    public String pagamentoCredito(double valor) {
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
        return "Efetue pagamento no estabelecimento. Valor: " + valor;
    }

    public String pagamentoDebito(double valor) {
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
        return "Efetue pagamento no estabelecimento. Valor: " + valor;
    }
}