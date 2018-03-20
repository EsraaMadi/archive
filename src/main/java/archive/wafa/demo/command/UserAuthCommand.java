package archive.wafa.demo.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.*;
import javax.validation.Valid;

@Setter
@Getter
@NoArgsConstructor
public class UserAuthCommand {

    private Long accessId;

    private String eskaUserName;
    @Valid
    private UserCommand  user ;
    private String encreptedPassword;
    @Length (min = 5 , max = 15)//(min = 5, message = "*Your password must have at least 5 characters2")
    @NotEmpty//(message = "*Please provide your password2")
    private String password;

}
