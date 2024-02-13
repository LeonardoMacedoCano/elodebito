package br.com.lcano.elodebito.dto;

import lombok.Data;
import java.util.Date;

@Data
public class NovoDebitoParcelaDTO {
    private int numero;
    private Date dataVencimento;
    private double valor;
}
