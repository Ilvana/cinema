package kino.controller;

import kino.configuration.BeanConfiguration;
import kino.model.entities.Contact;
import kino.service.MailService;
import kino.utils.ErrorGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Iterator;

@Controller
public class MainController {

    @RequestMapping(value = "/welcome" , method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin");
        model.addAttribute("message", "Admin Page - This is protected page!");
        return "adminPage";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loginPage(Model model, Principal principal) {
        String userName = principal.getName();
        model.addAttribute("message", "Welcome " + userName);
        return "index";
    }

    @RequestMapping(value="/event/upload/photo/{event}", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public void UploadFile(@PathVariable("event") Integer event, MultipartHttpServletRequest request, HttpServletResponse response) {

        Iterator<String> itr=request.getFileNames();

        MultipartFile file=request.getFile(itr.next());

        String fileName=file.getOriginalFilename();
        System.out.println(fileName);
    }

    @RequestMapping(value = "/contactUs",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity contactUs(@RequestBody Contact contact){
        try {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
            MailService mailService = applicationContext.getBean(MailService.class);
            mailService.sendMail(contact.getEmail(), "mujic-m@hotmail.com", contact.getSubject(), contact.getMessage());
            return new ResponseEntity(contact, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(ErrorGenerator.generateError("Error happened during sending mail."), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403Page";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound() {
        return "404";
    }
}