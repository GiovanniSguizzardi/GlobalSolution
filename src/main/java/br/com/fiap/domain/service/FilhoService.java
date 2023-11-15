package br.com.fiap.domain.service;
import br.com.fiap.domain.entity.Filho;
import br.com.fiap.domain.repository.FilhoRepository;
import java.util.List;

public class FilhoService implements Service<Filho, Long>{

    private FilhoRepository repo = FilhoRepository.build();
    @Override
    public List<Filho> findAll() {
        return repo.findAll();
    }

    @Override
    public Filho findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Filho persist(Filho filho) {
        return repo.persist(filho);
    }
}
