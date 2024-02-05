package br.com.lcano.elodebito.domain;

import br.com.lcano.elodebito.util.CustomException;
import br.com.lcano.elodebito.util.DateUtils;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testValidarParcela_NumeroInvalido() {
        DebitoParcela parcelaExistente = new DebitoParcela();
        parcelaExistente.setId(1L);
        parcelaExistente.setNumero(1);

        Debito debito = new Debito();
        List<DebitoParcela> parcelasExistentes = new ArrayList<>();
        parcelasExistentes.add(parcelaExistente);
        debito.setParcelas(parcelasExistentes);

        DebitoParcela novaParcela = new DebitoParcela();
        novaParcela.setId(2L);
        novaParcela.setNumero(1);
        novaParcela.setDataVencimento(new Date());
        novaParcela.setValor(100.00);
        novaParcela.setSituacao('A');
        novaParcela.setDebito(debito);

        assertThrows(CustomException.ParcelaNumeroInvalidoDebitoException.class, novaParcela::validarParcela);
    }

    @Test
    void testValidarParcela_DataVencimentoInvalido() {
        Date DataOntem = new Date(DateUtils.getDataAtual().getTime() - (1000 * 60 * 60 * 24));

        DebitoParcela parcela = new DebitoParcela();
        parcela.setId(1L);
        parcela.setNumero(1);
        parcela.setDataVencimento(DataOntem);
        parcela.setValor(100.00);
        parcela.setSituacao('A');
        parcela.setDebito(new Debito());

        assertThrows(CustomException.ParcelaDataVencimentoInvalidoException.class, parcela::validarParcela);
    }

    @Test
    void testValidarParcela_ValorInvalido() {
        DebitoParcela parcela = new DebitoParcela();
        parcela.setId(1L);
        parcela.setNumero(2);
        parcela.setDataVencimento(new Date());
        parcela.setValor(-100.00);
        parcela.setSituacao('A');
        parcela.setDebito(new Debito());

        assertThrows(CustomException.ParcelaValorInvalidoException.class, parcela::validarParcela);
    }

    @Test
    void testValidarParcela_Valida() {
        DebitoParcela parcela = new DebitoParcela();
        parcela.setId(1L);
        parcela.setNumero(1);
        parcela.setDataVencimento(new Date());
        parcela.setValor(100.00);
        parcela.setSituacao('A');
        parcela.setDebito(new Debito());

        assertDoesNotThrow(parcela::validarParcela);
    }
}
