package br.com.lcano.elodebito.util;

import java.sql.Date;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateUtilsTest {

    @Test
    public void testDataAtual() {
        Date dataAtual = DateUtils.getDataAtual();
        LocalDate dataLocalAtual = LocalDate.now();

        assertNotNull(dataAtual);

        Date dataAtualEsperada = Date.valueOf(dataLocalAtual);

        assertEquals(dataAtualEsperada, dataAtual);
    }
}
