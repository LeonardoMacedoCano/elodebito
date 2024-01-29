package br.com.lcano.elodebito.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {

    @Test
    public void testCreatePessoa() {
        Pessoa pessoa = new Pessoa();
        assertNull(pessoa.getId());
        assertNull(pessoa.getCpf());
        assertNull(pessoa.getNome());
    }

    @Test
    public void testPessoaWithId() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        assertEquals(id, pessoa.getId());
    }

    @Test
    public void testEqualsAndHashCode() {
        String cpf1 = "12345678901";
        String cpf2 = "98765432109";
        String nome1 = "Pessoa 1";
        String nome2 = "Pessoa 2";

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setCpf(cpf1);
        pessoa1.setNome(nome1);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setCpf(cpf1);
        pessoa2.setNome(nome1);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setCpf(cpf2);
        pessoa3.setNome(nome2);

        assertEquals(pessoa1.getCpf(), pessoa2.getCpf());
        assertEquals(pessoa1.getNome(), pessoa2.getNome());
        assertNotEquals(pessoa1.getCpf(), pessoa3.getCpf());
        assertNotEquals(pessoa1.getNome(), pessoa3.getNome());

        assertEquals(pessoa1.getCpf().hashCode(), pessoa2.getCpf().hashCode());
        assertEquals(pessoa1.getNome().hashCode(), pessoa2.getNome().hashCode());
        assertNotEquals(pessoa1.getCpf().hashCode(), pessoa3.getCpf().hashCode());
        assertNotEquals(pessoa1.getNome().hashCode(), pessoa3.getNome().hashCode());
    }
}
