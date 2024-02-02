package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoParcelaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DebitoParcelaServiceTest {

    @Mock
    private DebitoParcelaRepository debitoParcelaRepository;

    @InjectMocks
    private DebitoParcelaService debitoParcelaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        debitoParcelaService = new DebitoParcelaService(debitoParcelaRepository);
    }

    @Test
    void testCriarNovaParcela() {
        NovoDebitoParcelaDTO parcelaDTO = new NovoDebitoParcelaDTO();
        parcelaDTO.setNumero(1);
        parcelaDTO.setDataVencimento(new Date());
        parcelaDTO.setValor(100.0);
        parcelaDTO.setSituacao('A');

        Debito debito = new Debito();
        DebitoParcela novaParcela = debitoParcelaService.criarNovaParcela(parcelaDTO, debito);

        assertEquals(parcelaDTO.getNumero(), novaParcela.getNumero());
        assertEquals(parcelaDTO.getDataVencimento(), novaParcela.getDataVencimento());
        assertEquals(parcelaDTO.getValor(), novaParcela.getValor());
        assertEquals(parcelaDTO.getSituacao(), novaParcela.getSituacao());
        assertEquals(debito, novaParcela.getDebito());
    }

    @Test
    void testSalvarParcela() {
        DebitoParcela parcela = new DebitoParcela();
        debitoParcelaService.salvarParcela(parcela);
        verify(debitoParcelaRepository, times(1)).save(parcela);
    }

    @Test
    void testSalvarListaParcelas() {
        List<DebitoParcela> parcelas = Arrays.asList(new DebitoParcela(), new DebitoParcela(), new DebitoParcela());
        debitoParcelaService.salvarListaParcelas(parcelas);
        verify(debitoParcelaRepository, times(3)).save(any());
    }
}
