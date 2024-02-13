package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.repository.DebitoRepository;
import br.com.lcano.elodebito.repository.DebitoCustomRepository;
import br.com.lcano.elodebito.util.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class DebitoService {
    private final DebitoRepository debitoRepository;
    private final DebitoCustomRepository debitoCustomRepository;
    private  final DebitoParcelaService debitoParcelaService;
    private final PessoaService pessoaService;

    @Autowired
    public DebitoService(DebitoRepository debitoRepository,
                         DebitoCustomRepository debitoCustomRepository,
                         DebitoParcelaService debitoParcelaService,
                         PessoaService pessoaService) {
        this.debitoRepository = debitoRepository;
        this.debitoCustomRepository = debitoCustomRepository;
        this.debitoParcelaService = debitoParcelaService;
        this.pessoaService = pessoaService;
    }

    public void salvarDebito(Debito debito) {
        debitoRepository.save(debito);
    }

    public void deletarDebito(Debito debito) {
        debitoRepository.delete(debito);
    }

    public Debito criarDebito(NovoDebitoDTO data) {
        Debito novoDebito = new Debito();
        novoDebito.setPessoa(pessoaService.getPessoaById(data.getIdPessoa()));
        novoDebito.setDataLancamento(data.getDataLancamento());
        return novoDebito;
    }

    public Debito getDebitoById(Long id) {
        return debitoRepository.findById(id)
                .orElseThrow(() -> new CustomException.DebitoNaoEncontradaComIdException(id));
    }

    public Page<DebitoDTO> getAllDebitos(Pageable pageable) {
        return this.debitoRepository.findAll(pageable).map(DebitoDTO::converterParaDTO);
    }

    public Page<DebitoDTO> findCustomAllDebitos(
            @RequestParam(required = false) java.sql.Date dataLancamentoInicio,
            @RequestParam(required = false) java.sql.Date dataLancamentoFim,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nomePessoa,
            Pageable pageable) {
        return debitoCustomRepository.find(dataLancamentoInicio, dataLancamentoFim, cpf, nomePessoa, pageable).map(DebitoDTO::converterParaDTO);
    }

    @Transactional
    public void gerarDebito(NovoDebitoDTO data) {
        Debito novoDebito = criarDebito(data);
        novoDebito.getParcelas().addAll(debitoParcelaService.criarParcelas(novoDebito, data.getParcelasDTO()));
        novoDebito.validarDebito();
        debitoParcelaService.validarParcelas(novoDebito.getParcelas());
        salvarDebito(novoDebito);
        debitoParcelaService.salvarParcelas(novoDebito.getParcelas());
    }
}
