package br.com.lcano.elodebito.domain;

import br.com.lcano.elodebito.util.CustomException;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    private void validarCPF() {
        if (this.cpf == null || this.cpf.length() != 11 || !this.cpf.matches("\\d+")) {
            throw new CustomException.PessoaCPFInvalidoException();
        }
    }

    private void validarNome() {
        if (this.nome == null || this.nome.trim().isEmpty() || this.nome.length() > 255) {
            throw new CustomException.PessoaNomeInvalidoException();
        }
    }

    public void validarPessoa(){
        validarCPF();
        validarNome();
    }
}
