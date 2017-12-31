package archive.wafa.demo.service;


import archive.wafa.demo.command.UserAuthCommand;
import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.model.UserAuth;

public interface UserAuthService extends CRUDService <UserAuth > {
     UserAuth findByUser(Long userid);
     UserAuthCommand saveOrUpdate(UserAuthCommand authCommand);
}
