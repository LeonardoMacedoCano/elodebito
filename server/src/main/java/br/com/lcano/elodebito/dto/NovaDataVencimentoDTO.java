package br.com.lcano.elodebito.dto;

import lombok.Data;
import java.util.Date;

@Data
public class NovaDataVencimentoDTO {
    private Long idParcela;
    private Date dataVencimento;
}
