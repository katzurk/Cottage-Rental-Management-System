package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.CottageService;
import MojaChata.pl.app.model.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private CottageService cottageService;

    @GetMapping("/search")
    public String searchCottage(@ModelAttribute SearchDTO search, Model model) {
        List<Cottage> cottages = cottageService.searchCottage(search);

        model.addAttribute("searchDTO", search);
        model.addAttribute("cottages", cottages);
        return "search-cottage";
    }
}
