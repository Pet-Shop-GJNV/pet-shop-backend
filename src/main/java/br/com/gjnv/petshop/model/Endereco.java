package br.com.gjnv.petshop.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String complemento;
}
