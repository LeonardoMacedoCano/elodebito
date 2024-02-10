package br.com.lcano.elodebito.dto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NovoDebitoDTOTest {

    @Test
    void testSetterGetter() {
        Long idPessoa = 1L;
        Date dataLancamento = new Date();
        List<NovoDebitoParcelaDTO> parcelasDTO = new ArrayList<>();

        NovoDebitoDTO novoDebitoDTO = new NovoDebitoDTO();
        novoDebitoDTO.setIdPessoa(idPessoa);
        novoDebitoDTO.setDataLancamento(dataLancamento);
        novoDebitoDTO.setParcelasDTO(parcelasDTO);

        assertEquals(idPessoa, novoDebitoDTO.getIdPessoa());
        assertEquals(dataLancamento, novoDebitoDTO.getDataLancamento());
        assertEquals(parcelasDTO, novoDebitoDTO.getParcelasDTO());
    }
}
