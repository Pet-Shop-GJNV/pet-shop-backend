package br.com.gjnv.petshop.dto;
import lombok.Data;

import java.util.UUID;

@Data
public class EnderecoDto {

    private int id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String complemento;

}
