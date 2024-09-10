package br.com.gjnv.petshop.dto;
import lombok.Data;

@Data
public class EnderecoDto {

    private Long id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String complemento;

}
