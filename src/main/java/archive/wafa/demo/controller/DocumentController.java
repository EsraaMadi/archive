package archive.wafa.demo.controller;

import archive.wafa.demo.model.Document;
import archive.wafa.demo.model.User;
import archive.wafa.demo.repository.SortedProcedureRepository;
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


    private SortedProcedureRepository sortedProcedureRepository ;
    private DocumentService documentService;
    private UserService userService;

    public DocumentController(SortedProcedureRepository sortedProcedureRepository, DocumentService documentService, UserService userService) {
        this.sortedProcedureRepository = sortedProcedureRepository;
        this.documentService = documentService;
        this.userService = userService;
    }


    // save user details during a session
    public User setAppUser (HttpSession session)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        session.setAttribute("AppUser" , user);
        return user ;
    }

    // Get - archive page
    @RequestMapping(value="/archive" , method = RequestMethod.GET)
    String ArchiveDoc_Get(Model model ,HttpSession session){
        Document document = new Document();
        model.addAttribute("document", document);
        model.addAttribute("user", setAppUser (session));
        return "archive";
    }

    // Get - archive page (is called from JS function )
    @RequestMapping(value="/Archive/doc/{holderNo}", method = RequestMethod.GET)
    public @ResponseBody Document ArchiveDoc_Get_JS(@PathVariable String holderNo , HttpServletRequest request, HttpServletResponse response ,@SessionAttribute("AppUser") User user){
        String docno = request.getParameter("documentNo");
        //System.out.println( "000000000000000000000----------------  "+ user.getUsername() + "------" );
        //System.out.println( "111111111111111111111----------------  "+ docno + "------" );
        Document document =  documentService.archiveDocument(new Long( docno), holderNo , user.getUsername());
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
