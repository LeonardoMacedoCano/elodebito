package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.Pessoa;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.repository.DebitoCustomRepository;
import br.com.lcano.elodebito.repository.DebitoParcelaRepository;
import br.com.lcano.elodebito.repository.DebitoRepository;
import br.com.lcano.elodebito.util.DateUtils;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DebitoServiceTest {
    @Mock
    private DebitoRepository debitoRepository;

    @Mock
    private DebitoCustomRepository debitoCustomRepository;

    @Mock
    private DebitoParcelaRepository debitoParcelaRepository;

    @InjectMocks
    private DebitoParcelaService debitoParcelaService;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private DebitoService debitoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        debitoService = new DebitoService(debitoRepository, debitoCustomRepository, debitoParcelaService, pessoaService);
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
    void testDeletarDebito() {
        Debito debito = new Debito();
        debitoService.deletarDebito(debito);
        verify(debitoRepository, times(1)).delete(debito);
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
    void testGetDebitoById() {
        Long id = 1L;
        Debito debitoMock = new Debito();
        debitoMock.setId(id);

        when(debitoRepository.findById(id)).thenReturn(Optional.of(debitoMock));

        Debito debito = debitoService.getDebitoById(id);

        assertEquals(id, debito.getId());
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
    public void testFindCustomAllDebitos() {
        java.util.Date dataLancamento = new java.util.Date();
        String cpf = "12345678901";
        String nomePessoa = "João da Silva";
        Pageable pageable = PageRequest.of(0, 10);

        Pessoa pessoa = new Pessoa(1L, cpf, nomePessoa);
        Debito debito1 = new Debito(1L, pessoa, dataLancamento, new ArrayList<>());
        Debito debito2 = new Debito(2L, pessoa, dataLancamento, new ArrayList<>());

        List<Debito> listaDebitos = Arrays.asList(debito1, debito2);
        Page<Debito> paginaDebitos = new PageImpl<>(listaDebitos, pageable, listaDebitos.size());

        when(debitoCustomRepository.find(any(java.sql.Date.class), eq(cpf), eq(nomePessoa), eq(pageable))).thenReturn(paginaDebitos);

        Page<DebitoDTO> resultado = debitoService.findCustomAllDebitos(new java.sql.Date(dataLancamento.getTime()), cpf, nomePessoa, pageable);

        verify(debitoCustomRepository).find(any(java.sql.Date.class), eq(cpf), eq(nomePessoa), eq(pageable));

        assertNotNull(resultado);
        assertEquals(listaDebitos.size(), resultado.getContent().size());
    }


    @Test
    public void testGerarDebito() {
        List<NovoDebitoParcelaDTO> parcelasDTO = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            NovoDebitoParcelaDTO novaParcelaDTO = new NovoDebitoParcelaDTO();
            novaParcelaDTO.setNumero(i);
            novaParcelaDTO.setDataVencimento(new Date());
            novaParcelaDTO.setSituacao('A');
            novaParcelaDTO.setValor(10.0);
            parcelasDTO.add(novaParcelaDTO);
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setCpf("11111111111");
        pessoa.setNome("Osvaldo");

        NovoDebitoDTO novoDebitoDTO = new NovoDebitoDTO();
        novoDebitoDTO.setDataLancamento(DateUtils.getDataAtual());
        novoDebitoDTO.setIdPessoa(pessoa.getId());
        novoDebitoDTO.setParcelasDTO(parcelasDTO);

        when(pessoaService.getPessoaById(pessoa.getId())).thenReturn(pessoa);

        ResponseEntity<Object> response = debitoService.gerarDebito(novoDebitoDTO);

        verify(debitoRepository, times(1)).save(any(Debito.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("success", MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO), response.getBody());
        verify(debitoParcelaRepository, times(1)).saveAll(anyIterable());
    }
}
