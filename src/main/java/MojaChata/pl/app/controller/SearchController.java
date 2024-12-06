package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CottageService cottageService;

    @GetMapping("/search")
    public String searchCottage(@SessionAttribute(value = "loggedInUser", required = false) Login login,
                                @ModelAttribute SearchDTO search, Model model) {
        if (login != null) {
            search.setOwnerId(login.getId());
        }

        List<Cottage> cottages = cottageService.searchCottage(search);

        model.addAttribute("searchDTO", search);
        model.addAttribute("cottages", cottages);
        return "search-cottage";
    }

    @GetMapping("/request")
    public String sendRequest(@SessionAttribute(value = "loggedInUser", required = false) Login login,
                              @RequestParam("cottageId") long cottageId, Model model) {
        Cottage cottage = cottageRepository.findById(cottageId)
                .orElseThrow(() -> new RuntimeException("Invalid cottage Id: " + cottageId));
        if (!requestRepository.existsBySubmitterIdAndCottageId(login.getId(), cottage.getId())) {
            requestRepository.save(new Request(cottage, login.getId(), cottage.getOwnerId()));
        }
        return "redirect:/search";
    }
}
