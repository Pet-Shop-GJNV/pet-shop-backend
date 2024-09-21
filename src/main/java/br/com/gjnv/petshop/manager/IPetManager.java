package br.com.gjnv.petshop.manager;
//TODO: trocar Object por Pet
public interface IPetManager {
    void cadastrarPet(Object pet);
    Object consultarPet(Long id);
    void atualizarPet(Object pet);
    void excluirPet(Long id);
}
