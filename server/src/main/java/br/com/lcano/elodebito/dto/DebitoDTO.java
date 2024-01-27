package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Debito;
import lombok.Data;
import java.util.Date;

@Data
public class DebitoDTO {
    private Long id;
    private PessoaDTO pessoaDTO;
    private Date dataLancamento;

    public static DebitoDTO converterParaDTO(Debito entity) {
        DebitoDTO dto = new DebitoDTO();
        dto.setId(entity.getId());
        dto.setPessoaDTO(PessoaDTO.converterParaDTO(entity.getPessoa()));
        dto.setDataLancamento(entity.getDataLancamento());
        return dto;
    }

    public Debito converterParaEntidade() {
        return new Debito(this.getId(), this.getPessoaDTO().converterParaEntidade(), this.getDataLancamento());
    }
}
