package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.model.Pessoa;
import lombok.Data;

@Data
public class PessoaDTO {
    private Long id;
    private String cpf;
    private String nome;

    public static PessoaDTO converterParaDTO(Pessoa entity) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        return dto;
    }

    public Pessoa converterParaEntidade() {
        Pessoa pessoa = new Pessoa(this.getId(), this.getCpf(), this.getNome());
        return pessoa;
    }
}
