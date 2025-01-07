package MojaChata.pl.app.controller;

import java.util.List;

import MojaChata.pl.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUser")
public class StartPageController {
    @Autowired
    private CottageRepository cottageRepository;
    // @Autowired
    // private RequestRepository requestRepository;
    @Autowired
    private RequestService requestService;


    @GetMapping("/index")
    public String mainPage(Model model, @SessionAttribute(value = "loggedInUser", required = false) User login) {
    boolean hasRequests = false;

    if (login != null) {
        // Get all cottages owned by the user
        List<Cottage> userCottages = cottageRepository.findByOwnerId(login.getId());

        // Check if any of the user's cottages have requests
        hasRequests = userCottages.stream()
            .anyMatch(cottage -> requestService.existsByCottageIdAndNoApproval(cottage.getId()));
        }

    model.addAttribute("hasRequests", hasRequests);
    return "index";
}
}