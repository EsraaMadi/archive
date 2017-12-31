package archive.wafa.demo.service;


import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.convertor.UserCommandToUser;
import archive.wafa.demo.convertor.UserToUserCommand;
import archive.wafa.demo.exception.NotFoundException;
import archive.wafa.demo.model.User;
import archive.wafa.demo.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Profile({"datsusers", "default"})
public class UserServiceImpl implements UserService{

	 private UserRepository userRepository;
	 private UserToUserCommand userConvertor ;
	 private UserCommandToUser userCommandConvertor ;

	public UserServiceImpl(UserRepository userRepository, UserToUserCommand userConvertor, UserCommandToUser userCommandConvertor) {
		this.userRepository = userRepository;
		this.userConvertor = userConvertor;
		this.userCommandConvertor = userCommandConvertor;
	}


	@Override
	    public List<?> listAll() {
	        List<User> users = new ArrayList<>();
	        userRepository.findAll().forEach(users::add); //fun with Java 8
	        return users;
	    }

	    @Override
	    public User getById(Long id) {
			Optional<User> userOptional =  userRepository.findById(id);
			return userOptional.get();
	    }

	    @Override
	    public User saveOrUpdate(User domainObject) {
	    	User userTemp = userRepository.findByUsername(domainObject.getUsername());
			userTemp.toString();
	    	if (userTemp != null )
			{

				return  userTemp;
			}
	        return userRepository.save(domainObject);
	    }

	@Override
	public UserCommand findUserCommandByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (!userOptional.isPresent())
		{
			throw new NotFoundException("User Not Found For Email :" + email);
		}
		return userConvertor.convert(userOptional.get());
	}

	@Override
	      @Transactional
	       public void delete(Long id) {
	        userRepository.deleteById(id);
	    }

	    @Override
	    public User findByUsername(String username) {
		//System.out.print(username);
	        return userRepository.findByUsername(username);
	    }

	@Override
	public UserCommand findUserCommandByUserId(Long id) {
		Optional<User> userOptional =  userRepository.findById(id);
		return userConvertor.convert(userOptional.get());
	}

	@Override
	public UserCommand saveOrUpdate(UserCommand userCommand) {
		User userTemp = userRepository.findByUsername(userCommand.getUsername());
		User detachUser = userCommandConvertor.convert(userCommand);
		User savedUser = userRepository.save(detachUser);
		return userConvertor.convert(savedUser);
	}


}
