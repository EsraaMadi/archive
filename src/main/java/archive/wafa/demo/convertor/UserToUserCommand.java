package archive.wafa.demo.convertor;


import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.model.User;
import archive.wafa.demo.model.UserRoles;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {


    private final UserRolesToUserRolesCommand userRolesConvertor ;

    public UserToUserCommand( UserRolesToUserRolesCommand userRolesConvertor) {

        this.userRolesConvertor = userRolesConvertor;
    }

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User user) {
        if (user == null) {
            return null;
        }

        final UserCommand command = new UserCommand();
        command.setAccessType( user.getAccessType());
        command.setEmail(user.getEmail());
        command.setUserId(user.getUserId());
        command.setUsername(user.getUsername());

        if (user.getUserRoles() != null && user.getUserRoles().size() > 0){
            user.getUserRoles()
                    .forEach((UserRoles userRoles) -> command.getUserRoles().add(userRolesConvertor.convert(userRoles)));
        }

        return command;
    }
}
