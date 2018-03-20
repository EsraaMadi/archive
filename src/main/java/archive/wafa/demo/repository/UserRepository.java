package archive.wafa.demo.repository;


import archive.wafa.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {
    public User findByUsernameIgnoreCase(String username);
    public Optional<User> findByEmailIgnoreCase(String email);

}