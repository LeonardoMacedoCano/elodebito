package br.com.lcano.elodebito.repository;

import br.com.lcano.elodebito.domain.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Long> {
}
