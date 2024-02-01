package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.repository.PessoaRepository;
import br.com.lcano.elodebito.util.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {
    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

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
}
