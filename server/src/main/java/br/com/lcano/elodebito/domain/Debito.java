package br.com.lcano.elodebito.domain;

import br.com.lcano.elodebito.util.CustomException;
import br.com.lcano.elodebito.util.DateUtils;
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
@ToString(of = "id")
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

    @OneToMany(mappedBy = "debito", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DebitoParcela> parcelas = new ArrayList<>();

    private void validarDataLancamento() {
        if (this.dataLancamento == null || this.dataLancamento.compareTo(DateUtils.getDataAtual()) > 0) {
            throw new CustomException.DebitoDataLancamentoInvalidoException();
        }
    }

    private void validarParcelas() {
        if (this.parcelas.isEmpty()) {
            throw new CustomException.DebitoQuantidadeParcelasInvalidasException();
        }
    }

    public void validarDebito() {
        this.pessoa.validarPessoa();
        this.validarDataLancamento();
        this.validarParcelas();
    }
}
