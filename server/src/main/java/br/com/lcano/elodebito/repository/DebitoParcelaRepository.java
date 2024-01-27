package br.com.lcano.elodebito.repository;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DebitoParcelaRepository extends JpaRepository<DebitoParcela, Long> {
    List<DebitoParcela> findByDebito(Debito debito);
}
