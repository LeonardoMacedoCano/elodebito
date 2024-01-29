package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoParcelaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class DebitoParcelaServiceTest {
    @Mock
    private DebitoParcelaRepository debitoParcelaRepository;

    @InjectMocks
    private DebitoParcelaService debitoParcelaService;

    public DebitoParcelaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarParcela() {
        NovoDebitoParcelaDTO novoDebitoParcelaDTO = new NovoDebitoParcelaDTO();
        novoDebitoParcelaDTO.setNumero(1);
        novoDebitoParcelaDTO.setDataVencimento(new Date());
        novoDebitoParcelaDTO.setValor(100.00);
        novoDebitoParcelaDTO.setSituacao('A');

        Debito debito = new Debito();
        debito.setId(1L);

        debitoParcelaService.adicionarParcela(novoDebitoParcelaDTO, debito);

        ArgumentCaptor<DebitoParcela> argumentCaptor = ArgumentCaptor.forClass(DebitoParcela.class);
        verify(debitoParcelaRepository).save(argumentCaptor.capture());

        DebitoParcela debitoParcela = argumentCaptor.getValue();
        assertEquals(novoDebitoParcelaDTO.getNumero(), debitoParcela.getNumero());
        assertEquals(novoDebitoParcelaDTO.getDataVencimento(), debitoParcela.getDataVencimento());
        assertEquals(novoDebitoParcelaDTO.getValor(), debitoParcela.getValor());
        assertEquals(novoDebitoParcelaDTO.getSituacao(), debitoParcela.getSituacao());
        assertEquals(debito, debitoParcela.getDebito());
    }
}
