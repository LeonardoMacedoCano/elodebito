package br.com.lcano.elodebito.domain;

import br.com.lcano.elodebito.util.CustomException;
import br.com.lcano.elodebito.util.DateUtils;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DebitoTest {

    @Test
    void testCreate() {
        Debito debito = new Debito();
        assertNotNull(debito);
    }

    @Test
    void testSetterGetter() {
        Debito debito = new Debito();
        debito.setId(1L);
        debito.setPessoa(new Pessoa());
        debito.setDataLancamento(new Date());

        List<DebitoParcela> parcelas = new ArrayList<>();
        parcelas.add(new DebitoParcela(1L, 1, DateUtils.getDataAtual(), 10.0, 'A', debito));

        debito.setParcelas(parcelas);

        assertEquals(1L, debito.getId());
        assertNotNull(debito.getPessoa());
        assertNotNull(debito.getDataLancamento());
        assertEquals(parcelas, debito.getParcelas());
    }

    @Test
    void testValidarDebito_DataLancamentoInvalido() {
        Date DataAmanha = new Date(DateUtils.getDataAtual().getTime() + (DateUtils.UM_DIA_EM_MILISEGUNDOS));

        Debito debito = new Debito();
        debito.setId(1L);
        debito.setPessoa(new Pessoa(1L, "12345678901", "Leo"));
        debito.setDataLancamento(DataAmanha);

        List<DebitoParcela> parcelas = new ArrayList<>();
        parcelas.add(new DebitoParcela(1L, 1, DateUtils.getDataAtual(), 10.0, 'A', debito));

        debito.setParcelas(parcelas);

        assertThrows(CustomException.DebitoDataLancamentoInvalidoException.class, debito::validarDebito);
    }

    @Test
    void testValidarDebito_QuantidadeParcelasInvalida() {
        Debito debito = new Debito();
        debito.setId(1L);
        debito.setPessoa(new Pessoa(1L, "12345678901", "Leo"));
        debito.setDataLancamento(DateUtils.getDataAtual());
        debito.setParcelas(new ArrayList<>());

        assertThrows(CustomException.DebitoQuantidadeParcelasInvalidasException.class, debito::validarDebito);
    }

    @Test
    void testValidarDebito_Valido() {
        Debito debito = new Debito();
        debito.setId(1L);
        debito.setPessoa(new Pessoa(1L, "12345678901", "Leo"));
        debito.setDataLancamento(DateUtils.getDataAtual());

        List<DebitoParcela> parcelas = new ArrayList<>();
        parcelas.add(new DebitoParcela(1L, 1, DateUtils.getDataAtual(), 10.0, 'A', debito));

        debito.setParcelas(parcelas);

        assertDoesNotThrow(debito::validarDebito);
    }
}
