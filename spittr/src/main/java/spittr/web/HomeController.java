package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller    // declared to be a controller
@RequestMapping({"/", "/homepage"})  // map controller to / and /homepage
public class HomeController {

    // handle GET requests
    @RequestMapping(method=GET)
    public String home() {
        // view name is home
        return "home";
    }
}