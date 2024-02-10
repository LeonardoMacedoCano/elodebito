package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Debito;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class DebitoDTO {
    private Long id;

    @JsonProperty("pessoa")
    private PessoaDTO pessoaDTO;

    private Date dataLancamento;

    @JsonProperty("parcelas")
    private List<DebitoParcelaDTO> parcelasDTO;

    public static DebitoDTO converterParaDTO(Debito entity) {
        DebitoDTO dto = new DebitoDTO();
        dto.setId(entity.getId());
        dto.setPessoaDTO(PessoaDTO.converterParaDTO(entity.getPessoa()));
        dto.setDataLancamento(entity.getDataLancamento());
        dto.setParcelasDTO(DebitoParcelaDTO.converterListaParaDTO(entity.getParcelas()));
        return dto;
    }
}
