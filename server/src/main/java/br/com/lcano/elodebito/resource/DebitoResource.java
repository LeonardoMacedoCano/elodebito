package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.service.DebitoService;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/debitos")
public class DebitoResource {
    private final DebitoService debitoService;

    public DebitoResource(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    @GetMapping
    public ResponseEntity<Page<DebitoDTO>> getAllDebitos(
            @RequestParam(required = false) java.sql.Date dataLancamento,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nomePessoa,
            Pageable pageable
    ) {
        Page<DebitoDTO> debitos;
        if (dataLancamento != null || cpf != null || nomePessoa != null) {
            debitos = debitoService.findCustomAllDebitos(dataLancamento, cpf, nomePessoa, pageable);
        } else {
            debitos = debitoService.getAllDebitos(pageable);
        }
        return ResponseEntity.ok(debitos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebitoDTO> getDebitoById(@PathVariable("id") Long idDebito) {
        return ResponseEntity.ok(DebitoDTO.converterParaDTO(this.debitoService.getDebitoById(idDebito)));
    }

    @PostMapping
    public ResponseEntity<Object> gerarDebito(@RequestBody NovoDebitoDTO data) {
        debitoService.gerarDebito(data);
        return CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_ADICIONADO_COM_SUCESSO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDebito(@PathVariable("id") Long idDebito) {
        debitoService.deletarDebito(this.debitoService.getDebitoById(idDebito));
        return CustomSuccess.buildResponseEntity(MensagemUtils.DEBITO_DELETADO_COM_SUCESSO);
    }
}
