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

    public List<DebitoDTO> getAllDebitos() {
        List<Debito> debitos = debitoRepository.findAll();
        return debitos.stream().map(DebitoDTO::converterParaDTO).toList();
    }

    @Transactional
    public ResponseEntity<Object> adicionarNovoDebito(NovoDebitoDTO data) {
        Debito novoDebito = criarDebito(data);
        salvarDebito(novoDebito);
        adicionarNovasParcelas(novoDebito, data.getParcelasDTO());
        return CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO);
    }

    private Debito criarDebito(NovoDebitoDTO data) {
        Debito novoDebito = new Debito();
        novoDebito.setPessoa(pessoaService.getPessoaById(data.getIdPessoa()));
        novoDebito.setDataLancamento(data.getDataLancamento());
        return novoDebito;
    }

    private void adicionarNovasParcelas(Debito debito, List<NovoDebitoParcelaDTO> parcelasDTO) {
        parcelasDTO.forEach(parcelaDTO -> debitoParcelaService.adicionarNovaParcela(parcelaDTO, debito));
    }

    public void salvarDebito(Debito debito) {
        debitoRepository.save(debito);
    }
}
