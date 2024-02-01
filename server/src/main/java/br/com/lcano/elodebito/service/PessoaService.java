package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.repository.PessoaRepository;
import br.com.lcano.elodebito.util.CustomException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new CustomException.PessoaNaoEncontradaComIdException(id));
    }
}
