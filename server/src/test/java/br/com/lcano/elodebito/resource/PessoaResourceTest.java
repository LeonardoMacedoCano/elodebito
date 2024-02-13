package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.NovaPessoaDTO;
import br.com.lcano.elodebito.dto.PessoaDTO;
import br.com.lcano.elodebito.service.PessoaService;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PessoaResourceTest {
    private PessoaService pessoaService;
    private PessoaResource pessoaResource;

    @BeforeEach
    void setUp() {
        pessoaService = mock(PessoaService.class);
        pessoaResource = new PessoaResource(pessoaService);
    }

    @Test
    void testGetAllPessoas() {
        Pageable pageable = mock(Pageable.class);
        Page<PessoaDTO> page = new PageImpl<>(Collections.emptyList());

        ResponseEntity<Page<PessoaDTO>> responseEntity = pessoaResource.getAllPessoas(pageable);
        assertEquals(ResponseEntity.ok(page).getStatusCode(), responseEntity.getStatusCode());
        verify(pessoaService, times(1)).getAllPessoas(pageable);
    }

    @Test
    void testGetPessoaById() {
        Long idPessoa = 1L;
        Pessoa pessoa = new Pessoa(1L, "12345678901", "Leopoldo");

        when(pessoaService.getPessoaById(idPessoa)).thenReturn(pessoa);

        ResponseEntity<PessoaDTO> responseEntity = pessoaResource.getPessoaById(idPessoa);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(pessoaService, times(1)).getPessoaById(idPessoa);
    }

    @Test
    void testGerarPessoa() {
        NovaPessoaDTO data = new NovaPessoaDTO();
        ResponseEntity<Object> responseEntity = pessoaResource.gerarPessoa(data);
        assertEquals(CustomSuccess.buildResponseEntity(MensagemUtils.PESSOA_ADICIONADA_COM_SUCESSO).getStatusCode(), responseEntity.getStatusCode());
        verify(pessoaService, times(1)).gerarPessoa(data);
    }

    @Test
    void testDeletarPessoa() {
        Long idPessoa = 1L;
        ResponseEntity<Object> responseEntity = pessoaResource.deletarPessoa(idPessoa);
        assertEquals(CustomSuccess.buildResponseEntity(MensagemUtils.PESSOA_DELETADA_COM_SUCESSO).getStatusCode(), responseEntity.getStatusCode());
        verify(pessoaService, times(1)).deletarPessoa(any());
    }
}
