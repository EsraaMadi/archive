package archive.wafa.demo.convertor;


import archive.wafa.demo.command.UserAuthCommand;
import archive.wafa.demo.model.UserAuth;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserAuthCommandToUserAuth implements Converter<UserAuthCommand, UserAuth> {


    private final UserCommandToUser userCommandConvertor ;

    public UserAuthCommandToUserAuth(UserCommandToUser userCommandConvertor) {
        this.userCommandConvertor = userCommandConvertor;
    }

    @Synchronized
    @Nullable
    @Override
    public UserAuth convert(UserAuthCommand userAuthCommand) {

        if (userAuthCommand == null) {
            return null;
        }
        final UserAuth command = new UserAuth();

        command.setencreptedPassword(userAuthCommand.getEncreptedPassword());
        command.setAccessId(userAuthCommand.getAccessId());
        command.setEskaUserName(userAuthCommand.getEskaUserName());
        command.setPassword(userAuthCommand.getPassword());
        command.setUser(userCommandConvertor.convert(userAuthCommand.getUser()));

        return command;
    }
}
