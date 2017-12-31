package archive.wafa.demo.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class UserCommand {

    private Long userId;
    private String username;
    private int accessType;

    @Email(message = "*Please provide a valid Email2")
    @NotEmpty(message = "*Please provide an email2")
    private String email;
    private Set<UserRolesCommand> userRoles = new HashSet<>();


    public void addRole(UserRolesCommand role){
        if(!this.userRoles.contains(role)){
            this.userRoles.add(role);
        }
    }
}
