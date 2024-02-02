package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoRepository;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public Page<DebitoDTO> getAllDebitos(Pageable pageable) {
        return this.debitoRepository.findAll(pageable).map(DebitoDTO::converterParaDTO);
    }

    @Transactional
    public ResponseEntity<Object> adicionarNovoDebito(NovoDebitoDTO data) {
        Debito novoDebito = criarDebito(data);
        criarParcelas(novoDebito, data.getParcelasDTO());
        salvarDebito(novoDebito);
        debitoParcelaService.salvarListaParcelas(novoDebito.getParcelas());
        return CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO);
    }

    private Debito criarDebito(NovoDebitoDTO data) {
        Debito novoDebito = new Debito();
        novoDebito.setPessoa(pessoaService.getPessoaById(data.getIdPessoa()));
        novoDebito.setDataLancamento(data.getDataLancamento());
        return novoDebito;
    }

    private void criarParcelas(Debito debito, List<NovoDebitoParcelaDTO> parcelasDTO) {
        parcelasDTO.forEach(parcelaDTO -> debito.getParcelas().add(debitoParcelaService.criarNovaParcela(parcelaDTO, debito)));
    }

    private void salvarDebito(Debito debito) {
        debitoRepository.save(debito);
    }
}
