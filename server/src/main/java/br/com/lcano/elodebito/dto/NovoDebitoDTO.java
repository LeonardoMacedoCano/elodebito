package br.com.lcano.elodebito.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class NovoDebitoDTO {
    private Long idPessoa;

    private Date dataLancamento;

    @JsonProperty("parcelas")
    private List<NovoDebitoParcelaDTO> parcelasDTO;
}
