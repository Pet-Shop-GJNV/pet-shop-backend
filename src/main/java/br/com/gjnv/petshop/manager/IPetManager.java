package br.com.gjnv.petshop.manager;

import br.com.gjnv.petshop.model.Pet;

public interface IPetManager {
    void cadastrarPet(Pet pet);

    Pet consultarPet(Long id);

    void atualizarPet(Pet pet);

    void excluirPet(Long id);
}
