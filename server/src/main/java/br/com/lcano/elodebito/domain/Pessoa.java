package br.com.lcano.elodebito.domain;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;
}
