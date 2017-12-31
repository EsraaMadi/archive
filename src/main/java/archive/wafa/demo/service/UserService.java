package archive.wafa.demo.service;


import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.model.User;

import java.util.Optional;

public interface UserService extends CRUDService<User>  {
	
	 User findByUsername(String username);
	 UserCommand findUserCommandByEmail(String email);
	UserCommand findUserCommandByUserId(Long id );
	UserCommand saveOrUpdate(UserCommand userCommand);
	//public Optional<User> findByEmail(String email);

}

