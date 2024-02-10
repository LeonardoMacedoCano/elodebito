package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.NovaDataVencimentoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.service.DebitoParcelaService;
import br.com.lcano.elodebito.service.DebitoService;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DebitoParcelaResourceTest {
    private DebitoParcelaService debitoParcelaService;
    private DebitoParcelaResource debitoParcelaResource;

    @BeforeEach
    void setUp() {
        debitoParcelaService = mock(DebitoParcelaService.class);
        DebitoService debitoService = mock(DebitoService.class);
        debitoParcelaResource = new DebitoParcelaResource(debitoParcelaService, debitoService);
    }

    @Test
    void testGetValorTotalParcelas() {
        ResponseEntity<Object> responseEntity = debitoParcelaResource.getValorTotalParcelas();
        assertEquals(ResponseEntity.ok().build().getStatusCode(), responseEntity.getStatusCode());
        verify(debitoParcelaService, times(1)).getValorTotalParcelas();
    }

    @Test
    void testGerarParcelas() {
        Long idDebito = 1L;
        List<NovoDebitoParcelaDTO> data = Collections.singletonList(new NovoDebitoParcelaDTO());
        ResponseEntity<Object> responseEntity = debitoParcelaResource.gerarParcelas(idDebito, data);
        assertEquals(CustomSuccess.buildResponseEntity(MensagemUtils.PARCELA_ADICIONADO_COM_SUCESSO).getStatusCode(), responseEntity.getStatusCode());
        verify(debitoParcelaService, times(1)).gerarParcelas(any(), anyList());
    }

    @Test
    void testAlterarDataVencimentoParcelas() {
        List<NovaDataVencimentoDTO> listaNovaDataVencimento = Collections.singletonList(new NovaDataVencimentoDTO());
        ResponseEntity<Object> responseEntity = debitoParcelaResource.alterarDataVencimentoParcelas(listaNovaDataVencimento);
        assertEquals(CustomSuccess.buildResponseEntity(MensagemUtils.PARCELA_DATA_VENCIMENTO_ATUALIZADA_COM_SUCESSO).getStatusCode(), responseEntity.getStatusCode());
        verify(debitoParcelaService, times(1)).alterarDataVencimentoParcelas(anyList());
    }
}