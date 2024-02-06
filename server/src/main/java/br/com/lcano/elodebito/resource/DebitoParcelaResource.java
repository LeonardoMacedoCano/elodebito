package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.service.DebitoParcelaService;
import br.com.lcano.elodebito.service.DebitoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("parcela")
public class DebitoParcelaResource {
    private final DebitoParcelaService debitoParcelaService;
    private final DebitoService debitoService;

    public DebitoParcelaResource(DebitoParcelaService debitoParcelaService, DebitoService debitoService) {
        this.debitoParcelaService = debitoParcelaService;
        this.debitoService = debitoService;
    }

    @PostMapping(path = "/{idDebito}/adicionar")
    public ResponseEntity<Object> adicionarParcelas(@PathVariable("idDebito") Long idDebito, @RequestBody List<NovoDebitoParcelaDTO> data) {
        return debitoParcelaService.adicionarNovasParcelasAoDebito(debitoService.getDebitoById(idDebito), data);
    }

    @GetMapping("getValorTotalParcelas")
    public ResponseEntity<Object> getValorTotalParcelas() {
        return debitoParcelaService.getValorTotalParcelas();
    }
}
