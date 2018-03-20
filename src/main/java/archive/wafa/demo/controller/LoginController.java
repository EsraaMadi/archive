package archive.wafa.demo.controller;


import archive.wafa.demo.command.UserAuthCommand;
import archive.wafa.demo.command.UserCommand;
import archive.wafa.demo.convertor.UserRolesToUserRolesCommand;
import archive.wafa.demo.model.User;
import archive.wafa.demo.service.RoleService;
import archive.wafa.demo.service.UserAuthService;
import archive.wafa.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
//@SessionAttributes("userAuth")
public class LoginController {
	

	private UserService userService;
	private RoleService roleService ;
	private UserAuthService userAuthService;
	private UserRolesToUserRolesCommand userRolesConvertor;


	public LoginController(UserService userService, RoleService roleService, UserAuthService userAuthService, UserRolesToUserRolesCommand userRolesConvertor) {
		this.userService = userService;
		this.roleService = roleService;
		this.userAuthService = userAuthService;
		this.userRolesConvertor = userRolesConvertor;
	}

	// Get - Login page
	@RequestMapping(value={"/", "/login_page"}, method = RequestMethod.GET)
	public String  login_page(@Valid @ModelAttribute("userAuthCommand") UserAuthCommand userAuth , BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			bindingResult.getAllErrors().forEach(objectError -> {
				log.error(objectError.toString());
			});
		}
		return "login";
	}

	// Get - index page
	@RequestMapping("/index")
	String index(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = new User();
		user.setUsername(auth.getName());
		model.addAttribute("user", user);
		return "index";
	}

	// Get - Access denied page
	@RequestMapping("/access-denied")
	String access_denies(){
		return "access-denied";
	}


	// Post - Create new User
	@RequestMapping(value = "/NewUser", method = RequestMethod.POST)
	public String createUser (@Valid @ModelAttribute("userAuthCommand") UserAuthCommand userAuth , BindingResult bindingResult , RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
            });
			return "redirect:/login_page";
        }

        UserCommand savedUser = userService.saveOrUpdateManual(userAuth.getUser().getEmail(), userAuthService.getEncryptedPassword(userAuth.getPassword()));
		userAuth.setUser( savedUser);
		userAuth.setEskaUserName( savedUser.getUsername());
		//System.out.println("0000000000000000000   "+savedUser.getIsActionTaken());
		redirectAttributes.addFlashAttribute("userAuthCommand", userAuth);
		if (savedUser.getIsActionTaken()== 1) {
			return "redirect:/login_page?create=true";
		}
		else
		{
			return "redirect:/login_page?sign=true";
		}
	}

	/*
	// Post - JS - Create new User
	@RequestMapping(value = "/NewUserW", method = RequestMethod.POST)
	public @ResponseBody UserCommand  createUser_POST_JS ( HttpServletResponse response ,@Valid @ModelAttribute("userAuthCommand") UserAuthCommand userAuth , BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			bindingResult.getAllErrors().forEach(objectError -> {
				log.error(objectError.toString());
				System.out.println("--------------------------"+objectError.getDefaultMessage());
			});
			return new UserCommand();
		}
		System.out.println("--------------------------00000");
		String userEmail = "gggg@@EE";
		String userPassword =userAuth.getPassword();
		System.out.println("--------------------------"+userEmail);
		System.out.println("--------------------------"+userPassword);

		UserCommand savedUser =userService.saveOrUpdateManual(userEmail, userAuthService.getEncryptedPassword(userPassword));
		//System.out.println("--------------------------"+savedUser.getEmail());
		return savedUser;

	}*/


  /*  @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }*/

	/*private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}*/
	/*@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}*/
	
	/*@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveOrUpdate(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}*/

	/*@RequestMapping(value="/NewUser", method = RequestMethod.GET)
	public ModelAndView createNewUserArch(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		//List <UserRoles> userRoles= roleService.listAll();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("NewUser");
		return modelAndView;
	}*
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


	
	/*@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("userName", "Welcome " + user.getUsername()+" (" + user.getEmail() + ")");
		model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
		return "home" ;
	}*/
		@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getUsername()+" (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("home");
		return modelAndView;
	}
}