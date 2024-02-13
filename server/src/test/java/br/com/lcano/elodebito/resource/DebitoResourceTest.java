package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.service.DebitoService;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DebitoResourceTest {

    private DebitoService debitoService;
    private DebitoResource debitoResource;

    @BeforeEach
    void setUp() {
        debitoService = mock(DebitoService.class);
        debitoResource = new DebitoResource(debitoService);
    }

    @Test
    void testGetAllDebitos() {
        java.sql.Date dataLancamentoInicio = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date dataLancamentoFim = new java.sql.Date(System.currentTimeMillis());
        String cpf = "12345678901";
        String nomePessoa = "João da Silva";
        Pageable pageable = mock(Pageable.class);
        Page<DebitoDTO> page = new PageImpl<>(Collections.emptyList());

        when(debitoService.findCustomAllDebitos(dataLancamentoInicio, dataLancamentoFim, cpf, nomePessoa, pageable)).thenReturn(page);

        ResponseEntity<Page<DebitoDTO>> responseEntity = debitoResource.getAllDebitos(dataLancamentoInicio, dataLancamentoFim, cpf, nomePessoa, pageable);
        assertEquals(ResponseEntity.ok(page).getStatusCode(), responseEntity.getStatusCode());
        verify(debitoService, times(1)).findCustomAllDebitos(dataLancamentoInicio, dataLancamentoFim, cpf, nomePessoa, pageable);
    }

    @Test
    void testGetDebitoById() {
        Long idDebito = 1L;
        Debito debito = new Debito(1L,
                                    new Pessoa(1L, "12345678901", "Leopoldo"),
                                    new Date(),
                                    new ArrayList<>()
        );

        when(debitoService.getDebitoById(idDebito)).thenReturn(debito);

        ResponseEntity<DebitoDTO> responseEntity = debitoResource.getDebitoById(idDebito);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(debitoService, times(1)).getDebitoById(idDebito);
    }

    @Test
    void testGerarDebito() {
        NovoDebitoDTO data = new NovoDebitoDTO();
        ResponseEntity<Object> responseEntity = debitoResource.gerarDebito(data);
        assertEquals(CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO).getStatusCode(), responseEntity.getStatusCode());
        verify(debitoService, times(1)).gerarDebito(data);
    }

    @Test
    void testDeletarDebito() {
        Long idDebito = 1L;
        ResponseEntity<Object> responseEntity = debitoResource.deletarDebito(idDebito);
        assertEquals(CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_DELETADO_COM_SUCESSO).getStatusCode(), responseEntity.getStatusCode());
        verify(debitoService, times(1)).deletarDebito(any());
    }
}