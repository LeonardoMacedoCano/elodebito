package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.repository.DebitoRepository;
import br.com.lcano.elodebito.util.CustomException;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DebitoService {
    private final DebitoRepository debitoRepository;
    private  final DebitoParcelaService debitoParcelaService;
    private final PessoaService pessoaService;

    @Autowired
    public DebitoService(DebitoRepository debitoRepository,
                         DebitoParcelaService debitoParcelaService,
                         PessoaService pessoaService) {
        this.debitoRepository = debitoRepository;
        this.debitoParcelaService = debitoParcelaService;
        this.pessoaService = pessoaService;
    }

    public void salvarDebito(Debito debito) {
        debitoRepository.save(debito);
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

    @Transactional
    public ResponseEntity<Object> adicionarNovoDebito(NovoDebitoDTO data) {
        Debito novoDebito = criarDebito(data);
        novoDebito.getParcelas().addAll(debitoParcelaService.criarListaParcelas(novoDebito, data.getParcelasDTO()));
        salvarDebito(novoDebito);
        debitoParcelaService.salvarListaParcelas(novoDebito.getParcelas());
        return CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO);
    }
}
