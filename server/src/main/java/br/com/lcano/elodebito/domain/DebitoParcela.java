package br.com.lcano.elodebito.domain;

import br.com.lcano.elodebito.repository.DebitoParcelaRepository;
import br.com.lcano.elodebito.util.CustomException;
import br.com.lcano.elodebito.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "debitoparcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddebito")
    private Debito debito;

    private void validarNumero(DebitoParcelaRepository parcelaRepository) {
        DebitoParcela parcelaExistente = parcelaRepository.findByDebitoIdAndNumero(this.debito.getId(), this.numero);
        if (parcelaExistente != null && parcelaExistente.getId() != null && !parcelaExistente.getId().equals(this.id)) {
            throw new CustomException.ParcelaNumeroInvalidoDebitoException();
        }
    }

    private void validarDataVencimento() {
        if (Objects.isNull(this.dataVencimento) || this.dataVencimento.compareTo(DateUtils.getDataAtual()) < 0) {
            throw new CustomException.ParcelaDataVencimentoInvalidoException();
        }
    }

    private void validarValor() {
        if (this.valor <= 0.0) {
            throw new CustomException.ParcelaValorInvalidoException();
        }
    }

    public void validarParcela(DebitoParcelaRepository parcelaRepository) {
        this.validarNumero(parcelaRepository);
        this.validarDataVencimento();
        this.validarValor();
    }
}
