package archive.wafa.demo.controller;


import archive.wafa.demo.model.User;
import archive.wafa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	

	private UserService userService;
	//private RoleService roleService ;

	@Autowired
	public void setUserService(@Qualifier("userServiceImpl") UserService userService) {
		this.userService = userService;
	}

	/*@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}*/

	/*@RequestMapping(value={"/", "/login_page"}, method = RequestMethod.GET)
	public ModelAndView login_page(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login_page");
		return modelAndView;
	}*/

	@RequestMapping(value={"/", "/login_page"}, method = RequestMethod.GET)
	public String  login_page(Model model){
		return "login_page";
	}
	

	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
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