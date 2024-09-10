package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.model.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Funcionario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Embedded
    private Endereco endereco;

    @NonNull
    @Column(nullable = false)
    private String telefone;

    @NonNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_contratacao", nullable = false)
    private Date dataContratacao;

    @NonNull
    @Column(name = "horario_trabalho", nullable = false)
    private String horarioTrabalho;

    @NonNull
    @Column(nullable = false)
    private String cargo;

    @NonNull
    @Column(nullable = false)
    private double salario;
}
