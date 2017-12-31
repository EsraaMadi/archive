package archive.wafa.demo.convertor;


import archive.wafa.demo.command.UserRolesCommand;
import archive.wafa.demo.model.UserRoles;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserRolesCommandToUserRoles implements Converter<UserRolesCommand, UserRoles> {


    @Synchronized
    @Nullable
    @Override
    public UserRoles convert(UserRolesCommand userRolesCommand) {
        if (userRolesCommand== null) {
            return null;
        }
        final UserRoles command = new UserRoles();
        command.setAccessDesc(userRolesCommand.getAccessDesc());
        command.setRoleId(userRolesCommand.getRoleId());

        return command;

    }
}
