package archive.wafa.demo.service;


import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.convertor.UserCommandToUser;
import archive.wafa.demo.convertor.UserToUserCommand;
import archive.wafa.demo.exception.NotFoundException;
import archive.wafa.demo.model.User;
import archive.wafa.demo.repository.SortedProcedureRepository;
import archive.wafa.demo.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Profile({"datsusers", "default"})
public class UserServiceImpl implements UserService{

	 private UserRepository userRepository;
	 private UserToUserCommand userConvertor ;
	 private UserCommandToUser userCommandConvertor ;
	private SortedProcedureRepository sortedProcedureRepository ;
	private EntityManager em;

	public UserServiceImpl(UserRepository userRepository, UserToUserCommand userConvertor, UserCommandToUser userCommandConvertor, SortedProcedureRepository sortedProcedureRepository, EntityManager em) {
		this.userRepository = userRepository;
		this.userConvertor = userConvertor;
		this.userCommandConvertor = userCommandConvertor;
		this.sortedProcedureRepository = sortedProcedureRepository;
		this.em = em;
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
	    	User userTemp = userRepository.findByUsernameIgnoreCase(domainObject.getUsername());
			userTemp.toString();
	    	if (userTemp != null )
			{

				return  userTemp;
			}
	        return userRepository.save(domainObject);
	    }

	@Override
	public UserCommand findUserCommandByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmailIgnoreCase(email);
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
	        return userRepository.findByUsernameIgnoreCase(username);
	    }

	@Override
	public UserCommand findUserCommandByUserId(Long id) {
		Optional<User> userOptional =  userRepository.findById(id);
		return userConvertor.convert(userOptional.get());
	}

	@Override
	public UserCommand saveOrUpdate(UserCommand userCommand) {
		User userTemp = userRepository.findByUsernameIgnoreCase(userCommand.getUsername());
		User detachUser = userCommandConvertor.convert(userCommand);
		User savedUser = userRepository.save(detachUser);
		return userConvertor.convert(savedUser);
	}

	@Override
	public UserCommand saveOrUpdateManual(String userEmail , String userPassword) {

		//System.out.println("0000000000000000000"+userEmail);
		//System.out.println("0000000000000000000"+userPassword);
		StoredProcedureQuery Query = em.createNamedStoredProcedureQuery("CREATE_USER");
		Query.setParameter("P_EMAIL_IN", userEmail);
		Query.setParameter("P_PASSWORD_IN", userPassword);
		Query.execute();
		Long userId = (Long) Query.getOutputParameterValue("P_USER_ID_OUT");
		Long isActionTaken = (Long) Query.getOutputParameterValue("P_ACTION_OUT");
		UserCommand user = findUserCommandByUserId(userId);
		user.setIsActionTaken(isActionTaken);

		//System.out.println("0000000000000000000"+isActionTaked);
		//Long userId = sortedProcedureRepository.createUser(userEmail , userPassword);
		return user;
		//return findUserCommandByUserId(new Long(506));
	}
}
