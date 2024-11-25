package MojaChata.pl.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUser")
public class StartPageController {

    @GetMapping("/index")
    public String showStartPage(Model model) {
        model.addAttribute("username", "unknown");
        return "index";
    }
}
