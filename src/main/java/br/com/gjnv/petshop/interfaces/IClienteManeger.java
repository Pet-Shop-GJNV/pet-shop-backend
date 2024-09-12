package br.com.gjnv.petshop.interfaces;

interface IClienteManeger {
    //TODO: Transformar Object em cliente
    //TODO: Implementar métodos em gerente e atendente
    public void cadastrarCliente(Object cliente);
    public void consultarCliente(Object cliente);
    public void excluirCliente(Object cliente);
    public void atualizarCliente(Object cliente);
}
