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

    private NovoDebitoParcelaDTO criarNovoDebitoParcelaDTO(int numero, Date dataVencimento, Double valor, char situacao) {
        NovoDebitoParcelaDTO dto = new NovoDebitoParcelaDTO();
        dto.setNumero(numero);
        dto.setDataVencimento(dataVencimento);
        dto.setValor(valor);
        dto.setSituacao(situacao);
        return dto;
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
        verify(debitoParcelaRepository, times(1)).saveAll(any());
    }

    @Test
    void testCriarParcela() {
        NovoDebitoParcelaDTO parcelaDTO = criarNovoDebitoParcelaDTO(1, new Date(), 100.0, 'A');
        Debito debito = new Debito();
        DebitoParcela novaParcela = debitoParcelaService.criarParcela(parcelaDTO, debito);

        assertEquals(parcelaDTO.getNumero(), novaParcela.getNumero());
        assertEquals(parcelaDTO.getDataVencimento(), novaParcela.getDataVencimento());
        assertEquals(parcelaDTO.getValor(), novaParcela.getValor());
        assertEquals(parcelaDTO.getSituacao(), novaParcela.getSituacao());
        assertEquals(debito, novaParcela.getDebito());
    }

    @Test
    void testCriarListaParcela() {
        Debito debito = new Debito();
        List<NovoDebitoParcelaDTO> parcelasDTO = List.of(
            criarNovoDebitoParcelaDTO(1, new Date(), 33.34, 'B'),
            criarNovoDebitoParcelaDTO(2, new Date(), 33.33, 'A'),
            criarNovoDebitoParcelaDTO(3, new Date(), 33.33, 'A')
        );

        List<DebitoParcela> parcelas = debitoParcelaService.criarListaParcelas(debito, parcelasDTO);

        assertEquals(parcelasDTO.size(), parcelas.size());

        for (int i = 0; i < parcelasDTO.size(); i++) {
            NovoDebitoParcelaDTO dto = parcelasDTO.get(i);
            DebitoParcela parcela = parcelas.get(i);

            assertEquals(dto.getNumero(), parcela.getNumero());
            assertEquals(dto.getDataVencimento(), parcela.getDataVencimento());
            assertEquals(dto.getValor(), parcela.getValor());
            assertEquals(dto.getSituacao(), parcela.getSituacao());
            assertEquals(debito, parcela.getDebito());
        }
    }

    @Test
    void testValidarParcelas() {
        DebitoParcela parcela1 = mock(DebitoParcela.class);
        DebitoParcela parcela2 = mock(DebitoParcela.class);
        DebitoParcela parcela3 = mock(DebitoParcela.class);

        List<DebitoParcela> parcelas = Arrays.asList(parcela1, parcela2, parcela3);

        debitoParcelaService.validarParcelas(parcelas);

        for (DebitoParcela parcela : parcelas) {
            verify(parcela, times(1)).validarParcela(debitoParcelaRepository);
        }
    }
}
