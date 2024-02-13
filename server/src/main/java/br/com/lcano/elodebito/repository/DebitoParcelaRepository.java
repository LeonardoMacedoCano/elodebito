package br.com.lcano.elodebito.repository;

import br.com.lcano.elodebito.domain.DebitoParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitoParcelaRepository extends JpaRepository<DebitoParcela, Long> {
    DebitoParcela findByDebitoIdAndNumero(Long id, int numero);

    @Query("SELECT SUM(dp.valor) FROM DebitoParcela dp")
    Double getValorTotalParcelas();
}
