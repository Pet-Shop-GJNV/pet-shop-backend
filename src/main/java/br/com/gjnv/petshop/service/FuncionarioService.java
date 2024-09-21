package br.com.gjnv.petshop.service;
import br.com.gjnv.petshop.dto.FuncionarioDto;
import br.com.gjnv.petshop.model.Funcionario;
import br.com.gjnv.petshop.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(UUID id) {
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioDto convertToDTO(Funcionario funcionario) {
        FuncionarioDto dto = new FuncionarioDto();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setCpf(funcionario.getCpf());
        dto.setTelefone(funcionario.getTelefone());
        dto.setCargo(funcionario.getCargo());
        return dto;
    }
}
