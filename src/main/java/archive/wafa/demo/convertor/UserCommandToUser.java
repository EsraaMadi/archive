package archive.wafa.demo.convertor;


import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.command.UserRolesCommand;
import archive.wafa.demo.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final UserRolesCommandToUserRoles rolesCommandConvertor ;


    public UserCommandToUser(UserRolesCommandToUserRoles rolesCommandConvertor) {
        this.rolesCommandConvertor = rolesCommandConvertor;
    }

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand userCommand) {
        if (userCommand == null)
        {
            return  null ;
        }
        final User command = new User();
        command.setUserId( userCommand.getUserId());
        command.setAccessType(userCommand.getAccessType());
        command.setEmail(userCommand.getEmail());
        command.setUsername(userCommand.getUsername());
        if (userCommand.getUserRoles() != null && userCommand.getUserRoles().size() > 0){
            userCommand.getUserRoles()
                    .forEach((UserRolesCommand userRoles) -> command.getUserRoles().add(rolesCommandConvertor.convert(userRoles)));
        }

        return command;


    }
}
