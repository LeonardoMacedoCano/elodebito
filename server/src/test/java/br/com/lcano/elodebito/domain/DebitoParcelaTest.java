package br.com.lcano.elodebito.domain;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DebitoParcelaTest {

    @Test
    void testCreate() {
        DebitoParcela debitoParcela = new DebitoParcela();
        assertNotNull(debitoParcela);
    }

    @Test
    void testSetterGetter() {
        DebitoParcela debitoParcela = new DebitoParcela();
        debitoParcela.setId(1L);
        debitoParcela.setNumero(1);
        debitoParcela.setDataVencimento(new Date());
        debitoParcela.setValor(100.00);
        debitoParcela.setSituacao('A');

        assertEquals(1L, debitoParcela.getId());
        assertEquals(1, debitoParcela.getNumero());
        assertNotNull(debitoParcela.getDataVencimento());
        assertEquals(100.00, debitoParcela.getValor());
        assertEquals('A', debitoParcela.getSituacao());
    }
}
