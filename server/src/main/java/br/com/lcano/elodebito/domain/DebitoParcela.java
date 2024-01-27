package br.com.lcano.elodebito.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "debitoparcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DebitoParcela implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numero;

    @Column(name = "datavencimento", nullable = false)
    private Date dataVencimento;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private char situacao;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "iddebito")
    private Debito debito;
}
