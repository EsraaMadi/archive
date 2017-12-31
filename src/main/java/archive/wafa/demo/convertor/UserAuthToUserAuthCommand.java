package archive.wafa.demo.convertor;


import archive.wafa.demo.command.UserAuthCommand;
import archive.wafa.demo.model.UserAuth;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserAuthToUserAuthCommand implements Converter<UserAuth, UserAuthCommand> {

    private final UserToUserCommand userConvertor ;

    public UserAuthToUserAuthCommand(UserToUserCommand userConvertor) {
        this.userConvertor = userConvertor;
    }

    @Synchronized
    @Nullable
    @Override
    public UserAuthCommand convert(UserAuth userAuth) {
        if (userAuth == null) {
            return null;
        }
        final UserAuthCommand command = new UserAuthCommand();
        command.setAccessId(userAuth.getAccessId());
        command.setEncreptedPassword(userAuth.getencreptedPassword());
        command.setEskaUserName(userAuth.getEskaUserName());
        command.setPassword(userAuth.getPassword());
        command.setUser(userConvertor.convert(userAuth.getUser()));

        return command;
    }
}
