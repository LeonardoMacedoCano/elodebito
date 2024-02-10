package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.NovaDataVencimentoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoParcelaDTO;
import br.com.lcano.elodebito.service.DebitoParcelaService;
import br.com.lcano.elodebito.service.DebitoService;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
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
        return ResponseEntity.ok(debitoParcelaService.getValorTotalParcelas());
    }

    @PostMapping("/{idDebito}")
    public ResponseEntity<Object> gerarParcelas(@PathVariable("idDebito") Long idDebito, @RequestBody List<NovoDebitoParcelaDTO> data) {
        debitoParcelaService.gerarParcelas(debitoService.getDebitoById(idDebito), data);
        return CustomSuccess.buildResponseEntity(MensagemUtils.PARCELA_ADICIONADO_COM_SUCESSO);
    }

    @PutMapping("/data-vencimento")
    public ResponseEntity<Object> alterarDataVencimentoParcelas(@RequestBody List<NovaDataVencimentoDTO> listaNovaDataVencimento) {
        debitoParcelaService.alterarDataVencimentoParcelas(listaNovaDataVencimento);
        return CustomSuccess.buildResponseEntity(MensagemUtils.PARCELA_DATA_VENCIMENTO_ATUALIZADA_COM_SUCESSO);
    }
}
