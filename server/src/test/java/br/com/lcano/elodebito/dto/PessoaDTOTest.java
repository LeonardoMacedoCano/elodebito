package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Pessoa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaDTOTest {

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

    @Test
    public void testConverterParaEntidade() {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(1L);
        dto.setCpf("98765432109");
        dto.setNome("Maria");

        Pessoa entity = dto.converterParaEntidade();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCpf(), entity.getCpf());
        assertEquals(dto.getNome(), entity.getNome());
    }
}
