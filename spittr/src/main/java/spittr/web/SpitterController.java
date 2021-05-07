package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        // required for sf:form commandName="spitter"
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value="/register", method = POST)
    // validate Spitter input and return to registerForm if there is an error
//    public String processRegistration(@RequestPart() MultipartFile profilePicture, @Valid Spitter spitter, Errors errors, Model model) {
    public String processRegistration(@Valid Spitter spitter, Errors errors, RedirectAttributes model) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitter = spitterRepository.save(spitter);
        model.addAttribute("username", spitter.getUsername());
//        model.addAttribute("spitterId", spitter.getId());
        // using Flash Attribute to hold spitter
        model.addFlashAttribute("spitter", spitter);
//        return "redirect:/spitter/" + spitter.getUsername();
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value="/{username}", method = GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        System.out.println("GET /" + username + " has spitter attribute ? " + model.containsAttribute("spitter"));
        if (!model.containsAttribute("spitter")) {
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute(spitter);
        }
        return "profile";
    }


}
