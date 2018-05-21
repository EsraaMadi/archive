package archive.wafa.demo.service;

import archive.wafa.demo.model.HolderType;

import archive.wafa.demo.repository.HolderTypRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HolderTypeServiceImpl implements HolderTypService {

    private HolderTypRepository holderTypRepository;

    public HolderTypeServiceImpl(HolderTypRepository holderTypRepository) {
        this.holderTypRepository = holderTypRepository;
    }


    @Override
    public List<?> listAll() {
        List<HolderType> holderTypes = new ArrayList<>();
        holderTypRepository.findAll().forEach(holderTypes::add);
        //holderTypes.remove(1);
        return holderTypes;
    }

    @Override
    public HolderType getById(Long id) {
        Optional<HolderType> holderTypeOptionale = holderTypRepository.findById(id);
        return holderTypeOptionale.get();
    }

    @Override
    public HolderType saveOrUpdate(HolderType domainObject) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
