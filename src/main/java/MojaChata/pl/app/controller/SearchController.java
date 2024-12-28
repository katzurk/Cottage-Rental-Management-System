package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.*;
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
    private CottageService cottageService;

    @GetMapping("/search")
    public String searchCottage(@SessionAttribute(value = "loggedInUser", required = false) User login,
                                @ModelAttribute SearchDTO search, Model model) {
        if (login != null) {
            search.setOwnerId(login.getId());
        }

        List<Cottage> cottages = cottageService.searchCottage(search);

        model.addAttribute("searchDTO", search);
        model.addAttribute("cottages", cottages);
        return "search-cottage";
    }
}
