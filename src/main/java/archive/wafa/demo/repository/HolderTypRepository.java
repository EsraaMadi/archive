package archive.wafa.demo.repository;

import archive.wafa.demo.model.HolderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderTypRepository extends JpaRepository<HolderType ,Long> {
}
