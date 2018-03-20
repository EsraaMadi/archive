package archive.wafa.demo.repository;

import archive.wafa.demo.model.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderRepository extends JpaRepository <Holder , Long>{

}
