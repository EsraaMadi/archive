package archive.wafa.demo.controller;


import archive.wafa.demo.command.UserAuthCommand;
import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.command.UserRolesCommand;
import archive.wafa.demo.convertor.UserAuthToUserAuthCommand;
import archive.wafa.demo.convertor.UserRolesToUserRolesCommand;
import archive.wafa.demo.exception.NotFoundException;
import archive.wafa.demo.model.UserRoles;
import archive.wafa.demo.repository.SortedProcedureRepository;
import archive.wafa.demo.service.RoleService;
import archive.wafa.demo.service.UserAuthService;
import archive.wafa.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class AdminController {

    private UserAuthService userAuthService;
    private RoleService roleService;
    private UserService userService;
    private UserRolesToUserRolesCommand userRolesConvertor;
    private UserAuthToUserAuthCommand userAuthCommand;
    private SortedProcedureRepository sortedProcedureRepository ;



    public AdminController(SortedProcedureRepository sortedProcedureRepository ,UserAuthService userAuthService, RoleService roleService, UserService userService, UserRolesToUserRolesCommand userRolesConvertor, UserAuthToUserAuthCommand userAuthCommand) {
        this.userAuthService = userAuthService;
        this.roleService = roleService;
        this.userService = userService;
        this.userRolesConvertor = userRolesConvertor;
        this.userAuthCommand = userAuthCommand;
        this.sortedProcedureRepository = sortedProcedureRepository;

    }




   /* @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("userAuthCommand", new UserAuthCommand());
        model.addAttribute("roles", roleService.listAllRoles());
        return "index";
    }*/




    /*@RequestMapping(value="/User/Show", method = RequestMethod.GET)
    public String showUser( @ModelAttribute UserAuthCommand userAuth , Model model){
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("userAuthCommand",userAuth );
        return "show_user";
    }*/
     @RequestMapping(value="/User/{email_id}/Show", method = RequestMethod.GET)
    public String showUser( Model model, @PathVariable String email_id){
        UserCommand userCommand =userService.findUserCommandByEmail(email_id);
        UserAuthCommand userAuth = new UserAuthCommand();
        userAuth.setUser(userCommand);
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("userAuthCommand",userAuth );
        return "show_user";
    }
    @RequestMapping(value="/User/Show", method = RequestMethod.POST)
    public String SearchUser(@RequestParam("email_id") String email_id , Model model){
        UserCommand userCommand =userService.findUserCommandByEmail(email_id);
        UserAuthCommand userAuth = new UserAuthCommand();
        userAuth.setUser(userCommand);
        model.addAttribute("userAuthCommand",userAuth );
        model.addAttribute("roles", roleService.listAllRoles());
        return "show_user";
    }
    @RequestMapping(value = "/NewUserold", method = RequestMethod.POST)
	public String createUser (@Valid @ModelAttribute ("userAuthCommand") UserAuthCommand userAuth /* ,@Valid @ModelAttribute ("userAuthCommand.user") UserCommand user */,BindingResult bindingResult) {

        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
            });

            return "NewUser";
        }
        UserRoles role = roleService.getById(new Long (userAuth.getUser().getAccessType()));
        UserRolesCommand roleCommand = userRolesConvertor.convert(role);
        userAuth.getUser().addRole(roleCommand);
        userAuth.setEskaUserName(userAuth.getUser().getUsername());
        UserAuthCommand savedUser = userAuthService.saveOrUpdate(userAuth);
        return "redirect:/User/"+savedUser.getUser().getEmail()+"/Show";

	}
    @RequestMapping(value="/User/Show/{accessId}/access/new", method = RequestMethod.POST)
    public String NewUserAccess(@PathVariable String accessId ,@RequestParam("roleId") Long roleId  , Model model){
        System.out.print("access id : "+ accessId);
        UserCommand detacheduserCommand = userService.findUserCommandByUserId(new Long (accessId));
        UserRolesCommand roleCommand = userRolesConvertor.convert(roleService.getById(new Long (roleId)));
        detacheduserCommand.addRole(roleCommand);
        UserCommand savedUserCommand = userService.saveOrUpdate(detacheduserCommand);
        return  "redirect:/User/"+savedUserCommand.getEmail()+"/Show";
    }


    @RequestMapping(value="/User/Show/{accessId}/access/{roleId}/delete", method = RequestMethod.GET)
    public String DeleteUserAccess(@PathVariable String accessId , @PathVariable String roleId  ){
        roleService.deletRole(new Long(accessId) , new Long(roleId));
        UserCommand detacheduserCommand = userService.findUserCommandByUserId(new Long (accessId));
        return  "redirect:/User/"+detacheduserCommand.getEmail()+"/Show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound( Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }


		/*@RequestMapping(value = "/NewUser", method = RequestMethod.POST)
	public ModelAndView createNewUserArch(@Valid User user, BindingResult bindingResult) {
		User userExists = userService.findByEmail(user.getEmail());
		//System.out.print( userExists.toString());
		ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("successMessage", "User has been registered successfully");
			if (userExists  == null )
			{
				User u = new User ();
				modelAndView.addObject("user", u);
				System.out.print( u.toString());
			}
			else {
				modelAndView.addObject("user", userExists);
				System.out.print( userExists.toString());
			}

			modelAndView.setViewName("NewUser");

		return modelAndView;
	}*/

   /* @RequestMapping(value="/index", method = RequestMethod.GET)
    public String archiveDocument(Model model){
        model.addAttribute("userAuthCommand", new UserAuthCommand());
        model.addAttribute("roles", roleService.listAllRoles());

        return "index";
    }*/
}
