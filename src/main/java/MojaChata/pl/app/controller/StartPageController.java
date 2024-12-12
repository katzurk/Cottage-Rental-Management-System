package MojaChata.pl.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.User;
import MojaChata.pl.app.model.RequestRepository;

@Controller
@SessionAttributes("loggedInUser")
public class StartPageController {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private RequestRepository requestRepository;


    @GetMapping("/index")
    public String mainPage(Model model, @SessionAttribute(value = "loggedInUser", required = false) User login) {
    boolean hasRequests = false;

    if (login != null) {
        // Get all cottages owned by the user
        List<Cottage> userCottages = cottageRepository.findByOwnerId(login.getId());

        // Check if any of the user's cottages have requests
        hasRequests = userCottages.stream()
            .anyMatch(cottage -> !requestRepository.findByCottageId(cottage.getId()).isEmpty());
        }

    model.addAttribute("hasRequests", hasRequests);
    return "index";
}
}