package MojaChata.pl.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

    @GetMapping("/index")
    public String showStartPage(Model model) {
        model.addAttribute("username", "unknown");
        return "index";
    }
}
