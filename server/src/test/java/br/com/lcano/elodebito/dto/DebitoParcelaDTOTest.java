package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.DebitoParcela;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DebitoParcelaDTOTest {

    @Test
    void testConverterParaDTO() {
        DebitoParcela entity = new DebitoParcela();
        entity.setId(1L);
        entity.setNumero(1);
        entity.setDataVencimento(new Date());
        entity.setValor(100.00);
        entity.setSituacao('A');

        DebitoParcelaDTO dto = DebitoParcelaDTO.converterParaDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNumero(), dto.getNumero());
        assertEquals(entity.getDataVencimento(), dto.getDataVencimento());
        assertEquals(entity.getValor(), dto.getValor());
        assertEquals(entity.getSituacao(), dto.getSituacao());
    }

    @Test
    void testConverterParaEntidade() {
        DebitoParcelaDTO dto = new DebitoParcelaDTO();
        dto.setId(1L);
        dto.setNumero(1);
        dto.setDataVencimento(new Date());
        dto.setValor(100.00);
        dto.setSituacao('A');

        DebitoParcela entity = dto.converterParaEntidade();

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNumero(), entity.getNumero());
        assertEquals(dto.getDataVencimento(), entity.getDataVencimento());
        assertEquals(dto.getValor(), entity.getValor());
        assertEquals(dto.getSituacao(), entity.getSituacao());
    }

    @Test
    void testConverterListaParaDTO() {
        List<DebitoParcela> debitoParcelas = new ArrayList<>();

        DebitoParcela debitoParcela1 = new DebitoParcela();
        debitoParcela1.setId(1L);
        debitoParcela1.setNumero(1);
        debitoParcela1.setDataVencimento(new Date());
        debitoParcela1.setValor(100.00);
        debitoParcela1.setSituacao('A');

        DebitoParcela debitoParcela2 = new DebitoParcela();
        debitoParcela2.setId(2L);
        debitoParcela2.setNumero(2);
        debitoParcela2.setDataVencimento(new Date());
        debitoParcela2.setValor(100.00);
        debitoParcela2.setSituacao('A');

        debitoParcelas.add(debitoParcela1);
        debitoParcelas.add(debitoParcela2);

        List<DebitoParcelaDTO> dtos = DebitoParcelaDTO.converterListaParaDTO(debitoParcelas);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        assertEquals(debitoParcela1.getId(), dtos.get(0).getId());
        assertEquals(debitoParcela1.getNumero(), dtos.get(0).getNumero());
        assertEquals(debitoParcela1.getDataVencimento(), dtos.get(0).getDataVencimento());
        assertEquals(debitoParcela1.getValor(), dtos.get(0).getValor());
        assertEquals(debitoParcela1.getSituacao(), dtos.get(0).getSituacao());

        assertEquals(debitoParcela2.getId(), dtos.get(1).getId());
        assertEquals(debitoParcela2.getNumero(), dtos.get(1).getNumero());
        assertEquals(debitoParcela2.getDataVencimento(), dtos.get(1).getDataVencimento());
        assertEquals(debitoParcela2.getValor(), dtos.get(1).getValor());
        assertEquals(debitoParcela2.getSituacao(), dtos.get(1).getSituacao());
    }
}
