package archive.wafa.demo.bootstrap;


import archive.wafa.demo.service.RoleService;
import archive.wafa.demo.service.UserAuthService;
import archive.wafa.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private RoleService roleService;
    private UserAuthService userAuthService ;
    private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {




        /*User user = new User("test-h", 4, "test-h@wafainsurance.com");
        UserAuth auth = new UserAuth(user, "123456");


        UserRoles role = roleService.getById(new Long (4));
        System.out.print( role.toString());
        user.addRole(role);
        user.setUserAuth(auth);
        System.out.print( role.toString());
        userAuthService.saveOrUpdate(auth);*/





       /* UserRoles role = new UserRoles("tets");
        user.addRole(role);
        user.setUserAuth(auth);
         userService.saveOrUpdate(user);*/



        /* User user = new User("test-h" , 4 , "test-h@wafainsurance.com");
        UserAuth auth = new UserAuth(user , "1234");
        UserRoles role = new UserRoles("tets"); //roleService.getById(num.longValue());
        user.addRole(role);
        //user.setUserAuth(auth);
        role.addUser(user);
        userService.saveOrUpdate(user);
        roleService.saveOrUpdate(role);
        userAuthService.saveOrUpdate(auth);*/
    }


}


