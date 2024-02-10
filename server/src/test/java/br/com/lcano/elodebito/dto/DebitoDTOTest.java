package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import br.com.lcano.elodebito.domain.Pessoa;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DebitoDTOTest {

    @Test
    void testSetterGetter() {
        Long id = 1L;
        PessoaDTO pessoaDTO = new PessoaDTO();
        Date dataLancamento = new Date();
        List<DebitoParcelaDTO> parcelasDTO = new ArrayList<>();

        DebitoDTO debitoDTO = new DebitoDTO();
        debitoDTO.setId(id);
        debitoDTO.setPessoaDTO(pessoaDTO);
        debitoDTO.setDataLancamento(dataLancamento);
        debitoDTO.setParcelasDTO(parcelasDTO);

        assertEquals(id, debitoDTO.getId());
        assertEquals(pessoaDTO, debitoDTO.getPessoaDTO());
        assertEquals(dataLancamento, debitoDTO.getDataLancamento());
        assertEquals(parcelasDTO, debitoDTO.getParcelasDTO());
    }

    @Test
    void testConverterParaDTO() {
        Debito entity = new Debito();
        entity.setId(1L);
        entity.setPessoa(new Pessoa(1L, "12345678901", "Leopoldo"));
        entity.setDataLancamento(new Date());
        entity.setParcelas(List.of(new DebitoParcela(1L, 1, new Date(), 10.0, 'A', entity)));

        DebitoDTO dto = DebitoDTO.converterParaDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getPessoa().getId(), dto.getPessoaDTO().getId());
        assertEquals(entity.getPessoa().getCpf(), dto.getPessoaDTO().getCpf());
        assertEquals(entity.getPessoa().getNome(), dto.getPessoaDTO().getNome());
        assertEquals(entity.getDataLancamento(), dto.getDataLancamento());
        assertEquals(entity.getParcelas().get(0).getId(), dto.getParcelasDTO().get(0).getId());
        assertEquals(entity.getParcelas().get(0).getNumero(), dto.getParcelasDTO().get(0).getNumero());
        assertEquals(entity.getParcelas().get(0).getDataVencimento(), dto.getParcelasDTO().get(0).getDataVencimento());
        assertEquals(entity.getParcelas().get(0).getValor(), dto.getParcelasDTO().get(0).getValor());
        assertEquals(entity.getParcelas().get(0).getSituacao(), dto.getParcelasDTO().get(0).getSituacao());
    }
}