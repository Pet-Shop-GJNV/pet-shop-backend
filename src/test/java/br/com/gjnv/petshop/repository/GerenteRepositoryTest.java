package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Gerente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class GerenteRepositoryTest {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Test
    public void testFindAll() {
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        enderecoRepository.save(endereco1);
        enderecoRepository.save(endereco2);

        Gerente gerente1 = new Gerente();
        gerente1.setNome("Gerente 1");
        gerente1.setEndereco(endereco1);
        gerente1.setCpf("12345678901");
        gerente1.setSetorResponsavel("Setor 1");
        gerente1.setMetaMensal(1000.0);
        gerente1.setCargo("Gerente");
        gerente1.setDataContratacao(new Date());
        gerente1.setHorarioTrabalho("09:00 - 19:00");
        gerente1.setTelefone("123456789");
        gerenteRepository.save(gerente1);

        Gerente gerente2 = new Gerente();
        gerente2.setNome("Gerente 2");
        gerente2.setEndereco(endereco2);
        gerente2.setCpf("12345678902");
        gerente2.setSetorResponsavel("Setor 2");
        gerente2.setMetaMensal(2000.0);
        gerente2.setCargo("Gerente");
        gerente2.setDataContratacao(new Date());
        gerente2.setHorarioTrabalho("08:00 - 18:00");
        gerente2.setTelefone("123456789");
        gerenteRepository.save(gerente2);

        List<Gerente> gerentes = gerenteRepository.findAll();

        assertThat(gerentes).hasSize(2);
        assertThat(gerentes).contains(gerente1, gerente2);
    }

    @Test
    public void testFindById() {
        Endereco endereco = new Endereco();
        Gerente gerente = new Gerente();
        gerente.setEndereco(endereco);
        gerente.setSetorResponsavel("Setor 1");
        gerente.setMetaMensal(1000.0);
        gerenteRepository.save(gerente);


        Optional<Gerente> optionalGerente = gerenteRepository.findById(gerente.getId());

        assertThat(optionalGerente).isPresent();
        assertThat(optionalGerente.get()).isEqualTo(gerente);
    }

    @Test
    void testSave() {
        Endereco endereco = new Endereco();

        enderecoRepository.save(endereco);

        Gerente gerente = new Gerente();
        gerente.setNome("Gerente 1");
        gerente.setEndereco(endereco);
        gerente.setCpf("12345678901");
        gerente.setSetorResponsavel("Setor 1");
        gerente.setMetaMensal(1000.0);
        gerente.setCargo("Gerente");
        gerente.setDataContratacao(new Date());
        gerente.setHorarioTrabalho("09:00 - 19:00");
        gerente.setTelefone("123456789");
        gerenteRepository.save(gerente);

        gerenteRepository.save(gerente);

        Optional<Gerente> optionalGerente = gerenteRepository.findById(gerente.getId());

        assertThat(optionalGerente).isPresent();
        assertThat(optionalGerente.get()).isEqualTo(gerente);
    }

    @Test
    void testDelete() {
        Endereco endereco = new Endereco();
        enderecoRepository.save(endereco);

        Gerente gerente = new Gerente();
        gerente.setEndereco(endereco);
        gerente.setSetorResponsavel("Setor 1");
        gerente.setMetaMensal(1000.0);
        gerenteRepository.save(gerente);

        gerenteRepository.deleteById(gerente.getId());

        Optional<Gerente> optionalGerente = gerenteRepository.findById(gerente.getId());

        assertThat(optionalGerente).isEmpty();
    }

    @Test
    void updateGerente() {
        Endereco endereco = new Endereco();
        enderecoRepository.save(endereco);

        Gerente gerente = new Gerente();
        gerente.setEndereco(endereco);
        gerente.setSetorResponsavel("Setor 1");
        gerente.setMetaMensal(1000.0);
        gerenteRepository.save(gerente);

        gerente.setSetorResponsavel("Setor 2");
        gerenteRepository.save(gerente);

        Optional<Gerente> optionalGerente = gerenteRepository.findById(gerente.getId());

        assertThat(optionalGerente).isPresent();
        assertThat(optionalGerente.get().getSetorResponsavel()).isEqualTo("Setor 2");
    }
}
