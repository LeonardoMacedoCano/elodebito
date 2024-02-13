package br.com.lcano.elodebito.resource;

import br.com.lcano.elodebito.dto.NovaPessoaDTO;
import br.com.lcano.elodebito.dto.PessoaDTO;
import br.com.lcano.elodebito.service.PessoaService;
import br.com.lcano.elodebito.util.CustomSuccess;
import br.com.lcano.elodebito.util.MensagemUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> getAllPessoas(Pageable pageable) {
        Page<PessoaDTO> pessoaDTOS = pessoaService.getAllPessoas(pageable);
        return ResponseEntity.ok(pessoaDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> getPessoaById(@PathVariable("id") Long idPessoa) {
        return ResponseEntity.ok(PessoaDTO.converterParaDTO(this.pessoaService.getPessoaById(idPessoa)));
    }

    @PostMapping
    public ResponseEntity<Object> gerarPessoa(@RequestBody NovaPessoaDTO data) {
        pessoaService.gerarPessoa(data);
        return CustomSuccess.buildResponseEntity(MensagemUtils.PESSOA_ADICIONADA_COM_SUCESSO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPessoa(@PathVariable("id") Long idPessoa) {
        pessoaService.deletarPessoa(this.pessoaService.getPessoaById(idPessoa));
        return CustomSuccess.buildResponseEntity(MensagemUtils.PESSOA_DELETADA_COM_SUCESSO);
    }

}
