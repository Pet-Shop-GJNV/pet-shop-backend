package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    public PagamentoRepository pagamentoRepository;

    public String pagamentoPix(double valor) {
        return "Gerando chave pix / código QR. Valor: " + valor;
    }

    public String pagamentoDinheiro(double valor) {
        return "Efetue pagamento no estabelecimento. Valor: " + valor;
    }

    public String pagamentoCredito(double valor) {
        return "Efetue pagamento no estabelecimento. Valor: " + valor;
    }

    public String pagamentoDebito(double valor) {
        return "Efetue pagamento no estabelecimento. Valor: " + valor;
    }
}
