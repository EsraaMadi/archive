package archive.wafa.demo.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserRolesCommand {

    private Long roleId;
    private String accessDesc;

}
