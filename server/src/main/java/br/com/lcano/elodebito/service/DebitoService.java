package br.com.lcano.elodebito.service;

import br.com.lcano.elodebito.domain.Debito;
import br.com.lcano.elodebito.dto.DebitoDTO;
import br.com.lcano.elodebito.repository.DebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DebitoService {
    @Autowired
    private final DebitoRepository debitoRepository;

    public DebitoService(DebitoRepository debitoRepository) {
        this.debitoRepository = debitoRepository;
    }

    public List<DebitoDTO> getAllDebitos() {
        List<Debito> debitos = debitoRepository.findAll();
        return debitos.stream().map(DebitoDTO::converterParaDTO).toList();
    }
}
