package br.com.lcano.elodebito.dto;

import br.com.lcano.elodebito.domain.Pessoa;
import lombok.Data;

import java.util.Date;

@Data
public class DebitoDTO {
    private Pessoa pessoa;
    private Date dataLancamento;
}
