package archive.wafa.demo.controller;

import archive.wafa.demo.model.ArchiveReport;
import archive.wafa.demo.model.Document;
import archive.wafa.demo.model.User;
import archive.wafa.demo.service.DocumentService;
import archive.wafa.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;


@Slf4j
@Controller
public class DocumentController {


    private DocumentService documentService;
    private UserService userService;
    private String language;
    private User userD;


    public DocumentController( DocumentService documentService, UserService userService) {
        this.documentService = documentService;
        this.userService = userService;
    }


    // save user details during a session
    public User setAppUser (HttpSession session)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userD = userService.findByUsername(auth.getName());
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

    // Get - archive page
    @RequestMapping(value="/archive" , method = RequestMethod.GET)
    String ArchiveDoc_Get(Model model ,HttpServletRequest request ){

        Document document = new Document();
        model.addAttribute("document", document);
        model.addAttribute("user", setAppUser (request.getSession()));
        model.addAttribute("appLang",getLanguage(request));
        ArchiveReport report = documentService.DocumentReport(userD.getUsername());
        System.out.println("-----"+report.getPassDocOnceSum()+"-----"+report.getPassDocTwiceSum()+"-----"+report.getFaildDocOnceSum()+"-----"+report.getFaildDocTwiceSum());
        model.addAttribute("report",report);

        return "archive";
    }

    // Get - archive page (is called from JS function )
    @RequestMapping(value="/Archive/doc/{holderNo}", method = RequestMethod.GET)
    public @ResponseBody Document ArchiveDoc_Get_JS(@PathVariable String holderNo , HttpServletRequest request ,@SessionAttribute("AppUser") User user){
        String docno = request.getParameter("documentNo");
        //System.out.println( "000000000000000000000----------------  "+ user.getUsername() + "------" );
        //System.out.println( "111111111111111111111----------------  "+ docno + "------" );
        Document document =  documentService.archiveDocument(new Long( docno), holderNo , user.getUsername(), language);
        //System.out.println( "22222222222222222222----------------  "+ document.getDocumentNo()+ "------" + document.getArchiveStatus()+ document.getRersultMsg());
        return document;
    }

    /*
    // Get - archive page (old version)
    @RequestMapping(value="/scan/doc", method = RequestMethod.GET)
    public String scanDoc( Model model){
        Document document = new Document();
        model.addAttribute("document", document);
        return "Scan";
    }*/

}
