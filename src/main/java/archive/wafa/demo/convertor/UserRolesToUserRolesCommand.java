package archive.wafa.demo.convertor;


import archive.wafa.demo.command.UserRolesCommand;
import archive.wafa.demo.model.UserRoles;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserRolesToUserRolesCommand implements Converter<UserRoles, UserRolesCommand> {

    @Synchronized
    @Nullable
    @Override
    public UserRolesCommand convert(UserRoles userRoles) {

        if (userRoles == null) {
            return null;
        }
        final UserRolesCommand command = new UserRolesCommand();
        command.setAccessDesc(userRoles.getAccessDesc());
        command.setRoleId(userRoles.getRoleId());

        return command;
    }
}
