package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String searchCottage(@ModelAttribute SearchDTO search, Model model) {
        List<Cottage> cottages = cottageService.searchCottage(search);

        model.addAttribute("searchDTO", search);
        model.addAttribute("cottages", cottages);
        return "search-cottage";
    }

    @GetMapping("/request")
    public String sendRequest(@RequestParam("cottageId") long cottageId, Model model) {
        Cottage cottage = cottageRepository.findById(cottageId)
                .orElseThrow(() -> new RuntimeException("Invalid cottage Id: " + cottageId));
        requestRepository.save(new Request(cottage, 90, 91)); // test Ids
        return "redirect:/search";
    }
}
