package MojaChata.pl.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import jakarta.validation.Valid;

@Controller
public class CottageController {
    @Autowired
    private CottageRepository cottageRepository;

    @GetMapping("/addcottage")
    public String showSignUpForm(Cottage cottage) {
        return "add-cottage";
    }

    @PostMapping("/addcottage")
    public String addCottage(@Valid Cottage cottage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-cottage";
        }

        cottageRepository.save(cottage);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showCottageList(Model model) {
        model.addAttribute("cottages", cottageRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Cottage cottage = cottageRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid cottage Id:" + id));

        model.addAttribute("cottage", cottage);
        return "update-cottage";
    }

    @PostMapping("/update/{id}")
    public String updateCottage(@PathVariable("id") long id, @Valid Cottage cottage,
    BindingResult result, Model model) {
        if (result.hasErrors()) {
            // cottage.setId(id);
            return "update-cottage";
        }

        cottageRepository.save(cottage);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCottage(@PathVariable("id") long id, Model model) {
        Cottage cottage = cottageRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid cottage Id:" + id));
        cottageRepository.delete(cottage);
        return "redirect:/index";
    }

}

