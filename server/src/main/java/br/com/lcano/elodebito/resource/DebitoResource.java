package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.service.DebitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("debito")
public class DebitoResource {
    @Autowired
    private final DebitoService debitoService;

    public DebitoResource(DebitoService debitoService) {
        this.debitoService = debitoService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<DebitoDTO>> getAllDebitos() {
        List<DebitoDTO> debitoDTOS = debitoService.getAllDebitos();
        return ResponseEntity.ok(debitoDTOS);
    }
}
