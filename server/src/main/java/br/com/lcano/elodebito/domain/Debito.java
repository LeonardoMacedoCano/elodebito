package br.com.lcano.elodebito.domain;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "debito")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Debito implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

    @Column(name = "datalancamento", nullable = false)
    private Date dataLancamento;

    @OneToMany(mappedBy = "debito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DebitoParcela> parcelas = new ArrayList<>();
}
