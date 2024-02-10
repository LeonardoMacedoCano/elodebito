package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import br.com.lcano.elodebito.dto.NovaDataVencimentoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoParcelaRepository;
import br.com.lcano.elodebito.util.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebitoParcelaService {
    private final DebitoParcelaRepository debitoParcelaRepository;

    @Autowired
    public DebitoParcelaService(DebitoParcelaRepository debitoParcelaRepository) {
        this.debitoParcelaRepository = debitoParcelaRepository;
    }

    public void salvarParcela(DebitoParcela parcela) {
        debitoParcelaRepository.save(parcela);
    }

    public void salvarParcelas(List<DebitoParcela> parcelas) {
        debitoParcelaRepository.saveAll(parcelas);
    }

    public DebitoParcela getParcelaById(Long id) {
        return debitoParcelaRepository.findById(id)
                .orElseThrow(() -> new CustomException.ParcelaNaoEncontradaComIdException(id));
    }

    public DebitoParcela criarParcela(NovoDebitoParcelaDTO data, Debito debito) {
        DebitoParcela novaParcela = new DebitoParcela();
        novaParcela.setDebito(debito);
        novaParcela.setNumero(data.getNumero());
        novaParcela.setDataVencimento(data.getDataVencimento());
        novaParcela.setValor(data.getValor());
        novaParcela.setSituacao(data.getSituacao());
        return novaParcela;
    }

    public List<DebitoParcela> criarParcelas(Debito debito, List<NovoDebitoParcelaDTO> parcelasDTO) {
        return parcelasDTO.stream()
            .map(parcelaDTO -> criarParcela(parcelaDTO, debito))
            .collect(Collectors.toList());
    }

    @Transactional
    public void gerarParcelas(Debito debito, List<NovoDebitoParcelaDTO> parcelasDTO) {
        salvarParcelas(criarParcelas(debito, parcelasDTO));
    }

    public void validarParcelas(List<DebitoParcela> parcelas) {
        parcelas.forEach(debitoParcela -> debitoParcela.validarParcela(debitoParcelaRepository));
    }

    public Double getValorTotalParcelas() {
        return debitoParcelaRepository.getValorTotalParcelas();
    }

    @Transactional
    public void alterarDataVencimentoParcelas(List<NovaDataVencimentoDTO> listaNovaDataVencimento) {
        for (NovaDataVencimentoDTO novaDataVencimentoDTO : listaNovaDataVencimento) {
            DebitoParcela parcela = this.getParcelaById(novaDataVencimentoDTO.getIdParcela());
            parcela.setDataVencimento(novaDataVencimentoDTO.getDataVencimento());
            salvarParcela(parcela);
        }
    }
}
