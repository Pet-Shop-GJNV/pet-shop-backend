package br.com.gjnv.petshop.model;

<<<<<<< HEAD
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
=======
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

>>>>>>> fb6344f81df0d8abf6f8bcf7a715ac897757c733
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
