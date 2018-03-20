package archive.wafa.demo.service;


import archive.wafa.demo.command.UserAuthCommand;
import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.convertor.UserAuthCommandToUserAuth;
import archive.wafa.demo.convertor.UserAuthToUserAuthCommand;
import archive.wafa.demo.model.UserAuth;
import archive.wafa.demo.repository.UserAuthRepository;
import archive.wafa.demo.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAythServiceImpl implements UserAuthService{

    private final  UserAuthRepository userAuthRepository;
    private final UserAuthCommandToUserAuth userAuthCommandConvertor ;
    private final UserAuthToUserAuthCommand userAuthConvertor ;


    private EncryptionService encryptionService;

    public UserAythServiceImpl(UserAuthRepository userAuthRepository, UserAuthCommandToUserAuth userAuthCommandConvertor, UserAuthToUserAuthCommand userAuthConvertor) {
        this.userAuthRepository = userAuthRepository;
        this.userAuthCommandConvertor = userAuthCommandConvertor;
        this.userAuthConvertor = userAuthConvertor;
    }


    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

        @Override
        public List<?> listAll() {
            List <UserAuth> auth = new ArrayList<>();
            userAuthRepository.findAll().forEach(auth::add);
            return auth ;
        }

        @Override
        public UserAuth getById(Long id) {
            Optional<UserAuth> userAuthOptional =  userAuthRepository.findById(id);
            return userAuthOptional.get();
        }


    @Override
        public UserAuth saveOrUpdate(UserAuth domainObject) {
            if(domainObject.getPassword()!= null){
                domainObject.setencreptedPassword(encryptionService.encryptString(domainObject.getPassword()));
            }

            return userAuthRepository.save(domainObject);

        }

    @Override
    public UserAuthCommand saveOrUpdate(UserAuthCommand authCommand) {
        if(authCommand.getPassword()!= null){
            authCommand.setEncreptedPassword(encryptionService.encryptString(authCommand.getPassword()));
        }
        UserAuth detacheUserAuth = userAuthCommandConvertor.convert(authCommand);
        UserAuth savedUserAuth =  userAuthRepository.save(detacheUserAuth);
        return userAuthConvertor.convert(savedUserAuth);
    }

    @Override
    public String getEncryptedPassword(String Password) {
        return encryptionService.encryptString(Password);
    }

    @Override
        public void delete(Long id) {
            userAuthRepository.deleteById(id);
        }

        @Override
        public UserAuth findByUser(Long userid )
        {
            return userAuthRepository.findByUser(userid);
        }
    }

