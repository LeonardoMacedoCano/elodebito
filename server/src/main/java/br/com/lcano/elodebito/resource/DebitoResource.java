package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.service.DebitoService;
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
    public ResponseEntity<Page<DebitoDTO>> getAllDebitos(Pageable pageable) {
        return ResponseEntity.ok(this.debitoService.getAllDebitos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebitoDTO> getDebitoById(@PathVariable("id") Long idDebito) {
        return ResponseEntity.ok(DebitoDTO.converterParaDTO(this.debitoService.getDebitoById(idDebito)));
    }

    @PostMapping
    public ResponseEntity<Object> gerarDebito(@RequestBody NovoDebitoDTO data) {
        return debitoService.gerarDebito(data);
    }
}
