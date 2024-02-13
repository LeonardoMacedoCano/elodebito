package br.com.lcano.elodebito.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NovaPessoaDTOTest {

    @Test
    void testSetterGetter() {
        String nome = "Leopoldo";
        String cpf = "12345678901";

        NovaPessoaDTO novaPessoaDTO = new NovaPessoaDTO();
        novaPessoaDTO.setNome(nome);
        novaPessoaDTO.setCpf(cpf);

        assertEquals(nome, novaPessoaDTO.getNome());
        assertEquals(cpf, novaPessoaDTO.getCpf());
    }
}
