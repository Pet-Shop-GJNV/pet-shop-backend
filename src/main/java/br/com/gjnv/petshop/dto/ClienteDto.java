package br.com.gjnv.petshop.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
}

