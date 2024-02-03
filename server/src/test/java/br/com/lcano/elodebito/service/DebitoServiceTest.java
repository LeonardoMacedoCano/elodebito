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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    private NovoDebitoDTO criarNovoDebitoDTO(Long idPessoa, Date dataLancamento) {
        NovoDebitoDTO dto = new NovoDebitoDTO();
        dto.setIdPessoa(idPessoa);
        dto.setDataLancamento(dataLancamento);
        return dto;
    }

    @Test
    void testSalvarDebito() {
        Debito debito = new Debito();
        debitoService.salvarDebito(debito);
        verify(debitoRepository, times(1)).save(debito);
    }

    @Test
    void testCriarDebito() {
        Long idPessoa = anyLong();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(idPessoa);

        NovoDebitoDTO debitoDTO = criarNovoDebitoDTO(idPessoa, new Date());

        when(pessoaService.getPessoaById(idPessoa)).thenReturn(pessoa);

        Debito debito = debitoService.criarDebito(debitoDTO);

        assertEquals(debitoDTO.getIdPessoa(), debito.getPessoa().getId());
        assertEquals(debitoDTO.getDataLancamento(), debito.getDataLancamento());
    }

    @Test
    public void testGetAllDebitos() {
        Pageable pageable = Pageable.unpaged();
        Pessoa pessoa = new Pessoa();
        List<Debito> debitos = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Debito debito = new Debito();
            debito.setId((long) i);
            debito.setPessoa(pessoa);
            debito.setDataLancamento(new Date());
            debitos.add(debito);
        }

        Page<Debito> pageDebitos = new PageImpl<>(debitos, pageable, debitos.size());
        when(debitoRepository.findAll(pageable)).thenReturn(pageDebitos);
        Page<DebitoDTO> debitoDTOPage = debitoService.getAllDebitos(pageable);
        assertEquals(debitos.size(), debitoDTOPage.getContent().size());
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
        verify(debitoParcelaService, times(1)).criarListaParcelas(any(), any());
    }
}
