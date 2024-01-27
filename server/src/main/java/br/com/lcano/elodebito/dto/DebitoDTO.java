package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.ArrayList;
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

    public Debito converterParaEntidade() {
        Debito debito = new Debito();
        debito.setId(this.getId());
        debito.setPessoa(this.getPessoaDTO().converterParaEntidade());
        debito.setDataLancamento(this.getDataLancamento());

        List<DebitoParcela> parcelas = new ArrayList<>();
        if (this.parcelasDTO != null) {
            for (DebitoParcelaDTO parcelaDTO : this.parcelasDTO) {
                parcelas.add(parcelaDTO.converterParaEntidade());
            }
        }
        debito.setParcelas(parcelas);

        return debito;
    }
}
