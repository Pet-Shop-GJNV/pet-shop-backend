package br.com.gjnv.petshop.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public abstract class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String cpf;

//    @Embedded
//    private Endereco endereco;

    private String telefone;

    @Temporal(TemporalType.DATE)
    private Date dataContratacao;

    private String horarioTrabalho;

    private String cargo;

    private double salario;

    public abstract double calcularSalario();

    public void registrarPonto() {
        System.out.println("Ponto registrado para o funcionário: " + nome);
    }

    public void visualizarDadosPessoais() {
        System.out.println("Dados pessoais: " + nome + ", " + cpf);
    }

    public void atualizarDadosPessoais(String nome, Endereco endereco, String telefone) {
        this.nome = nome;
//        this.endereco = endereco;
        this.telefone = telefone;
    }
}
