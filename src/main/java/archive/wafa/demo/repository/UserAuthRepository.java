package archive.wafa.demo.repository;


import archive.wafa.demo.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAuthRepository extends JpaRepository<UserAuth, Long > {
    public UserAuth findByUser(Long userid);
	
}
