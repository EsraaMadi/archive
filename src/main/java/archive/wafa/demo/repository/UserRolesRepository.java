package archive.wafa.demo.repository;


import archive.wafa.demo.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    /*@Query(value ="DELETE FROM XX_DMS_USER_ACCESS T WHERE T.ACCESS_GROUP_ID = :roleId AND T.USER_ID = :userId ")
    void deletRole(@Param("userId") Long userId ,@Param("roleId") Long roleId);*/
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM XX_DMS_USER_ACCESS T WHERE T.ACCESS_GROUP_ID = ?2 AND T.USER_ID = ?1 " , nativeQuery=true)
    void deletRole( Long userId , Long roleId);
}
