package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebitoParcelaService {
    private final DebitoParcelaRepository debitoParcelaRepository;

    @Autowired
    public DebitoParcelaService(DebitoParcelaRepository debitoParcelaRepository) {
        this.debitoParcelaRepository = debitoParcelaRepository;
    }

    public DebitoParcela criarNovaParcela(NovoDebitoParcelaDTO data, Debito debito) {
        DebitoParcela novaParcela = new DebitoParcela();
        novaParcela.setDebito(debito);
        novaParcela.setNumero(data.getNumero());
        novaParcela.setDataVencimento(data.getDataVencimento());
        novaParcela.setValor(data.getValor());
        novaParcela.setSituacao(data.getSituacao());
        return novaParcela;
    }

    public void salvarParcela(DebitoParcela parcela) {
        debitoParcelaRepository.save(parcela);
    }

    public void adicionarNovaParcela(NovoDebitoParcelaDTO data, Debito debito) {
        DebitoParcela novaParcela = criarNovaParcela(data, debito);
        salvarParcela(novaParcela);
    }
}
