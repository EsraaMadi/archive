package archive.wafa.demo.service;


import archive.wafa.demo.command.UserRolesCommand;
import archive.wafa.demo.model.UserRoles;

import java.util.Set;

public interface RoleService extends CRUDService<UserRoles> {
     Set<UserRolesCommand> listAllRoles();
     void deletRole(Long userId, Long roleId);

}
