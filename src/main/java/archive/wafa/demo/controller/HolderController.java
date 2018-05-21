package archive.wafa.demo.controller;


import archive.wafa.demo.model.Holder;
import archive.wafa.demo.model.User;
import archive.wafa.demo.service.BatchTypService;
import archive.wafa.demo.service.HolderService;
import archive.wafa.demo.service.HolderTypService;
import archive.wafa.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HolderController {

    private UserService userService;
    private String language;
    private User userD;
    private HolderTypService holderTypService;
    private BatchTypService batchTypService;
    private HolderService holderService;

    public HolderController(UserService userService, HolderTypService holderTypService, BatchTypService batchTypService, HolderService holderService) {
        this.userService = userService;
        this.batchTypService = batchTypService;
        this.holderTypService = holderTypService;
        this.holderService = holderService;
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

    // Get - holder page
    @RequestMapping(value="/holder" , method = RequestMethod.GET)
    String Holder_Get(Model model , HttpServletRequest request ){
        model.addAttribute("batches",batchTypService.listAll());
        model.addAttribute("holderTypes",holderTypService.listAll());
        model.addAttribute("user", setAppUser (request.getSession()));
        model.addAttribute("appLang",getLanguage(request));
        //model.addAttribute("shelfNo" ,"ttt");
        model.addAttribute("holders",holderService.listAll());
        return "holder";
    }

    // Post - holder page (is called from JS function )
    @RequestMapping(value="/holder/create" , method = RequestMethod.POST)
    public @ResponseBody
    Holder Holder_Create_JS( HttpServletRequest request){
        Long batchId =  Long.parseLong (request.getParameter("batchId"));
        Long holderTypId =Long.parseLong (request.getParameter("holderTypId"));
        String shelfNo = request.getParameter("shelfNoId");
        /*System.out.println("--------------------- Get batch id +  "+ batchId);
        System.out.println("--------------------- Get holder type id +  "+ holderTypId);
        System.out.println("--------------------- Get shelf no +  "+ shelfNo);
        System.out.println("--------------------- Get user+  "+ userD.getUserId());
        System.out.println("--------------------- Get language +  "+ language);*/
        Holder holder = holderService.newHolder(batchId , holderTypId ,shelfNo,userD.getUserId(),language );
        //System.out.println("--------------------- Done +  "+ holder.getHolderNo());
        return holder;
    }


    // Get - holder page - barcode (is called from JS function )
    @RequestMapping(value="/barcode/create" , method = RequestMethod.GET)
    public @ResponseBody
    int Barcode_Create_JS( HttpServletRequest request){
        String barcode = request.getParameter("barcode");
        holderService.createHolderBarcodePDF(barcode);
        return 1;
    }

    /*
     @RequestMapping(value="/holder" , method = RequestMethod.GET)
    String Holder_Create(@RequestParam("batchId") Long batchId , @RequestParam("holderTypId") Long holderTypId , @RequestParam("shelfNoId") String shelfNo){
        System.out.println("--------------------- Get batch id +  "+ batchId);
        System.out.println("--------------------- Get holder type id +  "+ holderTypId);
        System.out.println("--------------------- Get shelf no +  "+ shelfNo);
        System.out.println("--------------------- Get user+  "+ userD.getUserId());
        System.out.println("--------------------- Get language +  "+ language);
        //Holder holder = holderService.newHolder(batchId , holderTypId ,shelfNo,userD.getUserId(),language );
        //System.out.println("--------------------- Done +  "+ holder.getHolderNo());
        return "holder";
    }
     */
}
