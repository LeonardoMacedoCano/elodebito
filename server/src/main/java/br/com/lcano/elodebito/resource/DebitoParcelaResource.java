package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.NovaDataVencimentoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.service.DebitoParcelaService;
import br.com.lcano.elodebito.service.DebitoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parcelas")
public class DebitoParcelaResource {
    private final DebitoParcelaService debitoParcelaService;
    private final DebitoService debitoService;

    public DebitoParcelaResource(DebitoParcelaService debitoParcelaService, DebitoService debitoService) {
        this.debitoParcelaService = debitoParcelaService;
        this.debitoService = debitoService;
    }

    @GetMapping("/valor-total")
    public ResponseEntity<Object> getValorTotalParcelas() {
        return debitoParcelaService.getValorTotalParcelas();
    }

    @PostMapping("/{idDebito}")
    public ResponseEntity<Object> gerarParcelas(@PathVariable("idDebito") Long idDebito, @RequestBody List<NovoDebitoParcelaDTO> data) {
        return debitoParcelaService.gerarParcelas(debitoService.getDebitoById(idDebito), data);
    }

    @PutMapping("/data-vencimento")
    public ResponseEntity<Object> alterarDataVencimentoParcelas(@RequestBody List<NovaDataVencimentoDTO> listaNovaDataVencimento) {
        return debitoParcelaService.alterarDataVencimentoParcelas(listaNovaDataVencimento);
    }
}
