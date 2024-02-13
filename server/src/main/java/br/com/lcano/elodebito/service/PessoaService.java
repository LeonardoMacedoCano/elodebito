package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.NovaPessoaDTO;
import br.com.lcano.elodebito.dto.PessoaDTO;
import br.com.lcano.elodebito.repository.PessoaRepository;
import br.com.lcano.elodebito.util.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void salvarPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    public Pessoa criarPessoa(NovaPessoaDTO data) {
        Pessoa novapessoa = new Pessoa();
        novapessoa.setNome(data.getNome());
        novapessoa.setCpf(data.getCpf());
        return novapessoa;
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new CustomException.PessoaNaoEncontradaComIdException(id));
    }

    public Page<PessoaDTO> getAllPessoas(Pageable pageable) {
        return this.pessoaRepository.findAll(pageable).map(PessoaDTO::converterParaDTO);
    }

    @Transactional
    public void gerarPessoa(NovaPessoaDTO data) {
        Pessoa novapessoa = criarPessoa(data);
        novapessoa.validarPessoa();
        salvarPessoa(novapessoa);
    }
}
