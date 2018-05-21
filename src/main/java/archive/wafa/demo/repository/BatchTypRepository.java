package archive.wafa.demo.repository;

import archive.wafa.demo.model.BatchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchTypRepository extends JpaRepository<BatchType, Long> {
}
