package br.com.fiap.domain.service;
import br.com.fiap.domain.entity.Gravida;
import br.com.fiap.domain.repository.GravidaRepository;

import java.util.List;

public class GravidaService implements Service<Gravida, Long>{

    private GravidaRepository repo = GravidaRepository.build();
    @Override
    public List<Gravida> findAll() {
        return repo.findAll();
    }

    @Override
    public Gravida findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Gravida persist(Gravida gravida) {
        return repo.persist(gravida);
    }
}
