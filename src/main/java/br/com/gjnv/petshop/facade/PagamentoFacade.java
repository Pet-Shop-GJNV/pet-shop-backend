package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.model.Pagamento;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.PagamentoRepository;
import br.com.gjnv.petshop.service.PagamentoService;
import br.com.gjnv.petshop.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PagamentoFacade {

    private final PagamentoService pagamentoService;
    private final PagamentoRepository pagamentoRepository;
    private final ServicoService servicoService;

    @Autowired
    public PagamentoFacade(PagamentoService pagamentoService, PagamentoRepository pagamentoRepository, ServicoService servicoService) {
        this.pagamentoService = pagamentoService;
        this.pagamentoRepository = pagamentoRepository;
        this.servicoService = servicoService;
    }

    public boolean realizarPagamentoPix(double valor, int servicoId) {
        Optional<Servico> servicoOptional = servicoService.consultarServico(servicoId);

        if (servicoOptional.isEmpty()) {
            return false;
        }

        Servico servico = servicoOptional.get();

        boolean isValid = pagamentoService.pagamentoPix(valor, servico);

        if (isValid) {
            Pagamento pagamento = new Pagamento(valor, servico);
            pagamentoRepository.save(pagamento);
        }
        return isValid;
    }

    public String realizarPagamentoDinheiro(double valor, int servicoId) {
        Optional<Servico> servicoOptional = servicoService.consultarServico(servicoId);
        if (servicoOptional.isEmpty()) {
            return "Serviço não encontrado.";
        }

        Pagamento pagamento = new Pagamento(valor, servicoOptional.get());
        pagamentoRepository.save(pagamento);
        return pagamentoService.pagamentoDinheiro(valor);
    }

    public String realizarPagamentoCredito(double valor, int servicoId) {
        Optional<Servico> servicoOptional = servicoService.consultarServico(servicoId);
        if (servicoOptional.isEmpty()) {
            return "Serviço não encontrado.";
        }

        Pagamento pagamento = new Pagamento(valor, servicoOptional.get());
        pagamentoRepository.save(pagamento);
        return pagamentoService.pagamentoCredito(valor);
    }

    public String realizarPagamentoDebito(double valor, int servicoId) {
        Optional<Servico> servicoOptional = servicoService.consultarServico(servicoId);
        if (servicoOptional.isEmpty()) {
            return "Serviço não encontrado.";
        }

        Pagamento pagamento = new Pagamento(valor, servicoOptional.get());
        pagamentoRepository.save(pagamento);
        return pagamentoService.pagamentoDebito(valor);
    }
}
