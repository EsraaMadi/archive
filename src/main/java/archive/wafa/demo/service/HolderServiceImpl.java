package archive.wafa.demo.service;

import archive.wafa.demo.model.Holder;
import archive.wafa.demo.repository.HolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolderServiceImpl implements HolderService {

    private HolderRepository holderRepository ;

    public HolderServiceImpl() {
    }

    public HolderServiceImpl(HolderRepository holderRepository) {
        this.holderRepository = holderRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Holder getById(Long id) {
        Optional<Holder> holderOptional =  holderRepository.findById(id);
        return holderOptional.get();
    }

    @Override
    public Holder saveOrUpdate(Holder domainObject) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
