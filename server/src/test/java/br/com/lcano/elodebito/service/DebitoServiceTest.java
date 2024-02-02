package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoRepository;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DebitoServiceTest {
    @Mock
    private DebitoRepository debitoRepository;

    @Mock
    private DebitoParcelaService debitoParcelaService;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private DebitoService debitoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        debitoService = new DebitoService(debitoRepository, debitoParcelaService, pessoaService);
    }

    @Test
    public void testGetAllDebitos() {
        Pessoa pessoa = new Pessoa();
        List<Debito> debitos = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Debito debito = new Debito();
            debito.setId((long) i);
            debito.setPessoa(pessoa);
            debito.setDataLancamento(new Date());
            debitos.add(debito);
        }

        when(debitoRepository.findAll()).thenReturn(debitos);
        List<DebitoDTO> debitoDTOs = debitoService.getAllDebitos();
        assertEquals(debitos.size(), debitoDTOs.size());
    }


    @Test
    public void testAdicionarNovoDebito() {
        List<NovoDebitoParcelaDTO> parcelasDTO = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            NovoDebitoParcelaDTO novaParcelaDTO = new NovoDebitoParcelaDTO();
            novaParcelaDTO.setNumero(i);
            novaParcelaDTO.setDataVencimento(new Date());
            novaParcelaDTO.setSituacao('A');
            novaParcelaDTO.setValor(10.0);
            parcelasDTO.add(novaParcelaDTO);
        }

        NovoDebitoDTO novoDebitoDTO = new NovoDebitoDTO();
        novoDebitoDTO.setIdPessoa(1L);
        novoDebitoDTO.setParcelasDTO(parcelasDTO);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        Debito debito = new Debito();

        when(pessoaService.getPessoaById(pessoa.getId())).thenReturn(pessoa);
        when(debitoRepository.save(debito)).thenReturn(debito);

        ResponseEntity<Object> response = debitoService.adicionarNovoDebito(novoDebitoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("success", MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO), response.getBody());
        verify(debitoParcelaService, times(12)).criarNovaParcela(any(), any());
    }
}
