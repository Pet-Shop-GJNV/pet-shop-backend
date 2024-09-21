package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@DataJpaTest
@SpringJUnitConfig
class EnderecoRepositoryTest {


    @Autowired

    private EnderecoRepository enderecoRepository;


    @Test
    public void deveSalvarEndereco() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", "Estado Teste");
        enderecoRepository.save(endereco);
        List<Endereco> enderecos = enderecoRepository.findAll();
        Assertions.assertTrue(!enderecos.isEmpty(), "Teste falhou");
    }

    @Test
    public void deveBuscarEnderecoPorId() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", "Estado Teste");
        enderecoRepository.save(endereco);
        Endereco enderecoBuscado = enderecoRepository.findById(endereco.getId()).orElse(null);
        Assertions.assertTrue(enderecoBuscado != null, "Teste falhou");
    }
}