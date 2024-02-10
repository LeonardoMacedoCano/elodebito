package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.domain.DebitoParcela;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DebitoParcelaDTO {
    private Long id;

    private int numero;

    private Date dataVencimento;

    private double valor;

    private char situacao;

    @JsonIgnore
    private Debito debito;

    public static DebitoParcelaDTO converterParaDTO(DebitoParcela entity) {
        DebitoParcelaDTO dto = new DebitoParcelaDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setValor(entity.getValor());
        dto.setSituacao(entity.getSituacao());
        return dto;
    }

    public static List<DebitoParcelaDTO> converterListaParaDTO(List<DebitoParcela> debitoParcelas) {
        List<DebitoParcelaDTO> dto = new ArrayList<>();
        for (DebitoParcela debitoParcela : debitoParcelas) {
            dto.add(converterParaDTO(debitoParcela));
        }
        return dto;
    }
}
