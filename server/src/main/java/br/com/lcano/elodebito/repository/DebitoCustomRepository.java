package br.com.lcano.elodebito.repository;

import br.com.lcano.elodebito.domain.Debito;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DebitoCustomRepository {
    private final EntityManager entityManager;

    public DebitoCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Page<Debito> find(
            @RequestParam(required = false) java.sql.Date dataLancamentoInicio,
            @RequestParam(required = false) java.sql.Date dataLancamentoFim,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nomePessoa,
            Pageable pageable
    ) {
        String queryStr = "SELECT d FROM Debito d WHERE 1=1 ";
        Map<String, Object> params = new HashMap<>();

        if (dataLancamentoInicio != null && dataLancamentoFim != null) {
            queryStr += "AND d.dataLancamento BETWEEN :dataInicio AND :dataFim ";
            params.put("dataInicio", dataLancamentoInicio);
            params.put("dataFim", dataLancamentoFim);
        }

        if (cpf != null && !cpf.isEmpty()) {
            queryStr += "AND LOWER(d.pessoa.cpf) LIKE LOWER(:cpf) ";
            params.put("cpf", "%" + cpf.toLowerCase() + "%");
        }

        if (nomePessoa != null && !nomePessoa.isEmpty()) {
            queryStr += "AND LOWER(d.pessoa.nome) LIKE LOWER(:nomePessoa) ";
            params.put("nomePessoa", "%" + nomePessoa.toLowerCase() + "%");
        }

        TypedQuery<Debito> query = entityManager.createQuery(queryStr, Debito.class);
        params.forEach(query::setParameter);

        int totalRows = query.getResultList().size();
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(query.getResultList(), pageable, totalRows);
    }
}
