package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.NovaPessoaDTO;
import br.com.lcano.elodebito.dto.PessoaDTO;
import br.com.lcano.elodebito.repository.PessoaRepository;
import br.com.lcano.elodebito.util.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {
    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void testSalvarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoaService.salvarPessoa(pessoa);
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void testDeletarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoaService.deletarPessoa(pessoa);
        verify(pessoaRepository, times(1)).delete(pessoa);
    }

    @Test
    void testCriarPessoa() {
        NovaPessoaDTO novaPessoaDTO = new NovaPessoaDTO();
        novaPessoaDTO.setCpf("12345678901");
        novaPessoaDTO.setNome("Leopoldo");

        Pessoa novaPessoa = pessoaService.criarPessoa(novaPessoaDTO);

        assertEquals(novaPessoaDTO.getCpf(), novaPessoa.getCpf());
        assertEquals(novaPessoaDTO.getNome(), novaPessoa.getNome());
    }

    @Test
    void testGetPessoaById() {
        Long id = 1L;
        Pessoa pessoaMock = new Pessoa();
        pessoaMock.setId(id);

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaMock));

        Pessoa pessoa = pessoaService.getPessoaById(id);

        assertEquals(id, pessoa.getId());
    }

    @Test
    void testGetPessoaById_ThrowsException() {
        Long id = 1L;

        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CustomException.PessoaNaoEncontradaComIdException.class, () -> pessoaService.getPessoaById(id));
    }

    @Test
    public void testGetAllPessoas() {
        Pageable pageable = Pageable.unpaged();
        List<Pessoa> pessoas = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId((long) i);
            pessoa.setNome("nome");
            pessoa.setCpf("12345678901");
            pessoas.add(pessoa);
        }

        Page<Pessoa> pagePessoas = new PageImpl<>(pessoas, pageable, pessoas.size());
        when(pessoaRepository.findAll(pageable)).thenReturn(pagePessoas);
        Page<PessoaDTO> pessoaDTOPage = pessoaService.getAllPessoas(pageable);
        assertEquals(pessoas.size(), pessoaDTOPage.getContent().size());
    }

    @Test
    public void testGerarPessoa() {
        NovaPessoaDTO novaPessoaDTO = new NovaPessoaDTO();
        novaPessoaDTO.setNome("Leonardo");
        novaPessoaDTO.setCpf("12345678901");

        pessoaService.gerarPessoa(novaPessoaDTO);

        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }
}
