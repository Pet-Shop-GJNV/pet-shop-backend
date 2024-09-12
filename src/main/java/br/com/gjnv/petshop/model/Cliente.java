package br.com.gjnv.petshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;

    public String consultarServico(Long servicoId) {
        return "Serviço consultado: " + servicoId;
    }

    public String solicitarServico(String tipoServico) {
        return "Serviço solicitado: " + tipoServico;
    }

    public String cancelarServico(Long servicoId) {
        return "Serviço cancelado: " + servicoId;
    }
}
