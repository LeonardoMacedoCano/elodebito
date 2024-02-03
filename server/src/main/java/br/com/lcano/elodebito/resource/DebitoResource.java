package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.dto.NovoDebitoDTO;
import br.com.lcano.elodebito.service.DebitoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("debito")
public class DebitoResource {
    private final DebitoService debitoService;

    public DebitoResource(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    @GetMapping("getAll")
    public ResponseEntity<Page<DebitoDTO>> getAllDebitos(Pageable pageable) {
        return ResponseEntity.ok(this.debitoService.getAllDebitos(pageable));
    }

    @PostMapping("adicionar")
    public ResponseEntity<Object> adicionarDebito(@RequestBody NovoDebitoDTO data) {
        return debitoService.adicionarNovoDebito(data);
    }
}
