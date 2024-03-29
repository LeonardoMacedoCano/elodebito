package br.com.lcano.elodebito.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NovoDebitoParcelaDTOTest {

    @Test
    void testSetterGetter() {
        int numero = 1;
        Date dataVencimento = new Date();
        double valor = 100.0;

        NovoDebitoParcelaDTO novoDebitoParcelaDTO = new NovoDebitoParcelaDTO();
        novoDebitoParcelaDTO.setNumero(numero);
        novoDebitoParcelaDTO.setDataVencimento(dataVencimento);
        novoDebitoParcelaDTO.setValor(valor);

        assertEquals(numero, novoDebitoParcelaDTO.getNumero());
        assertEquals(dataVencimento, novoDebitoParcelaDTO.getDataVencimento());
        assertEquals(valor, novoDebitoParcelaDTO.getValor());
    }
}
