package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Pessoa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaDTOTest {

    @Test
    void testSetterGetter() {
        Long id = 1L;
        String cpf = "12345678901";
        String nome = "Leopoldo";

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(id);
        pessoaDTO.setCpf(cpf);
        pessoaDTO.setNome(nome);

        assertEquals(id, pessoaDTO.getId());
        assertEquals(cpf, pessoaDTO.getCpf());
        assertEquals(nome, pessoaDTO.getNome());
    }

    @Test
    public void testConverterParaDTO() {
        Pessoa entity = new Pessoa();
        entity.setId(1L);
        entity.setCpf("12345678901");
        entity.setNome("Leonardo");

        PessoaDTO dto = PessoaDTO.converterParaDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getCpf(), dto.getCpf());
        assertEquals(entity.getNome(), dto.getNome());
    }
}
