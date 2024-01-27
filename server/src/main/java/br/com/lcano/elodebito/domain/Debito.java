package br.com.lcano.elodebito.domain;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "debito")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Debito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    @Column(name = "datalancamento", nullable = false)
    private Date dataLancamento;
}
