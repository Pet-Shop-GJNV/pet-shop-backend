package br.com.gjnv.petshop.dto;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class FuncionarioDto {
    private UUID id;
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;
}
