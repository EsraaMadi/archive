package archive.wafa.demo.service;


import archive.wafa.demo.model.BatchType;
import archive.wafa.demo.repository.BatchTypRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BatchTypServiceImpl implements BatchTypService {

    private BatchTypRepository batchTypRepository;

    public BatchTypServiceImpl(BatchTypRepository batchTypRepository) {
        this.batchTypRepository = batchTypRepository;
    }

    @Override
    public List<?> listAll() {
        List<BatchType> batches = new ArrayList<>();
        batchTypRepository.findAll().forEach(batches::add); //fun with Java 8
        //batches.remove(6);
        return batches;
    }

    @Override
    public BatchType getById(Long id) {
        Optional<BatchType> batchTypeOptional =  batchTypRepository.findById(id);
        return batchTypeOptional.get();
    }

    @Override
    public BatchType saveOrUpdate(BatchType domainObject) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
