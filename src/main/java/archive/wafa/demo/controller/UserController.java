package archive.wafa.demo.controller;


import archive.wafa.demo.model.User;
import archive.wafa.demo.service.UserAuthService;
import archive.wafa.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    private UserService userService;
    private UserAuthService userAuthService;
    private String language;
    private User userD;

    public UserController(UserService userService, UserAuthService userAuthService) {
        this.userService = userService;
        this.userAuthService = userAuthService;
    }

    // save user details during a session
    public User setAppUser (HttpSession session)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userD = userService.findByUsername(auth.getName());
        //System.out.println("----------------" + userD.getUserId());
        session.setAttribute("AppUser" , userD);
        return userD ;
    }

    // Set language as session variable
    //@ModelAttribute("language")
    public String getLanguage(HttpServletRequest request)
    {
        language= request.getParameter("lang");
        if (language== null)
        {
            language="en";
        }
        request.getSession().setAttribute("language",language);
        //System.out.println("-------------777"+request.getSession().getAttribute("language")+"------"+language);
        return language ;
    }

    // Get - Access user page
    @RequestMapping(value="/accessUser" , method = RequestMethod.GET)
    String accessUser_Get(Model model , HttpServletRequest request ){
        model.addAttribute("users_list",userAuthService.listAll());
        model.addAttribute("user", setAppUser (request.getSession()));
        model.addAttribute("appLang",getLanguage(request));
        return "user_access";
    }
   /* @RequestMapping(value="/User/Show", method = RequestMethod.POST)
    public String SearchUser(@RequestParam("email_id") String email_id , Model model){
        UserCommand userCommand =userService.findUserCommandByEmail(email_id);
        UserAuthCommand userAuth = new UserAuthCommand();
        userAuth.setUser(userCommand);
        model.addAttribute("userAuthCommand",userAuth );
        model.addAttribute("roles", roleService.listAllRoles());
        return "show_user";
    }*/



}
