//package br.com.gjnv.petshop.controller;
//
//import br.com.gjnv.petshop.model.Funcionario;
//import br.com.gjnv.petshop.service.FuncionarioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/funcionarios")
//public class FuncionarioController {
//
//    @Autowired
//    private FuncionarioService funcionarioService;
//
//    @GetMapping
//    public List<Funcionario> listarTodosFuncionarios() {
//        return funcionarioService.listarTodosFuncionarios();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Funcionario> buscarPorId(@PathVariable UUID id) {
//        return funcionarioService.buscarPorId(id);
//    }
//
//    @PostMapping
//    public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario) {
//        return funcionarioService.salvarFuncionario(funcionario);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletarFuncionario(@PathVariable UUID id) {
//        funcionarioService.deletarFuncionario(id);
//    }
//}
