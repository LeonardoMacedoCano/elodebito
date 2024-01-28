package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoRepository;
import br.com.lcano.elodebito.repository.PessoaRepository;
import br.com.lcano.elodebito.util.CustomException;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DebitoService {
    @Autowired
    private final DebitoRepository debitoRepository;

    @Autowired
    private  final DebitoParcelaService debitoParcelaService;

    @Autowired
    private final PessoaRepository pessoaRepository;

    public DebitoService(DebitoRepository debitoRepository,
                         DebitoParcelaService debitoParcelaService,
                         PessoaRepository pessoaRepository) {
        this.debitoRepository = debitoRepository;
        this.debitoParcelaService = debitoParcelaService;
        this.pessoaRepository = pessoaRepository;
    }

    public List<DebitoDTO> getAllDebitos() {
        List<Debito> debitos = debitoRepository.findAll();
        return debitos.stream().map(DebitoDTO::converterParaDTO).toList();
    }

    @Transactional
    public ResponseEntity<Object> adicionarDebito(NovoDebitoDTO data) {
        Optional<Pessoa> optionalPessoa = getPessoaById(data.getIdPessoa());

        if (optionalPessoa.isPresent()) {
            Debito novoDebito = new Debito();
            novoDebito.setPessoa(optionalPessoa.get());
            novoDebito.setDataLancamento(data.getDataLancamento());
            debitoRepository.save(novoDebito);

            List<NovoDebitoParcelaDTO> parcelaDTOS = data.getParcelasDTO();
            for (NovoDebitoParcelaDTO parcelaDTO : parcelaDTOS) {
                debitoParcelaService.adicionarParcela(parcelaDTO, novoDebito);
            }
            return CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO);
        } else {
            throw new CustomException.PessoaNaoEncontradaComIdException(data.getIdPessoa());
        }
    }

    private Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }
}
