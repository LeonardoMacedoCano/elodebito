package br.com.lcano.elodebito.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NovaDataVencimentoDTOTest {

    @Test
    void testSetterGetter() {
        Long idParcela = 1L;
        Date dataVencimento = new Date();

        NovaDataVencimentoDTO novaDataVencimentoDTO = new NovaDataVencimentoDTO();
        novaDataVencimentoDTO.setIdParcela(idParcela);
        novaDataVencimentoDTO.setDataVencimento(dataVencimento);

        assertEquals(idParcela, novaDataVencimentoDTO.getIdParcela());
        assertEquals(dataVencimento, novaDataVencimentoDTO.getDataVencimento());
    }
}
