package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CottageController {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private RequestRepository requestRepository;

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
        return "redirect:/my-cottages";
    }

    @GetMapping("/my-cottages")
    public String showCottageList(Model model) {
        model.addAttribute("cottages", cottageRepository.findAll());
        return "my-cottages";
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
        return "redirect:/my-cottages";
    }

    @GetMapping("/delete/{id}")
    public String deleteCottage(@PathVariable("id") long id, Model model) {
        Cottage cottage = cottageRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid cottage Id:" + id));
        cottageRepository.delete(cottage);
        return "redirect:/my-cottages";
    }

    @GetMapping("/reservations")
    public String showReservations(Model model) {
        List<Request> requests = requestRepository.findBySubmitterId(90L); // placeholder
        List<Cottage> cottages = requests.stream()
                .map(Request::getCottage)
                .collect(Collectors.toList());
        model.addAttribute("cottages", cottages);
        return "my-reservations";
    }

    @GetMapping("/unrequest")
    public String unsendRequest(@RequestParam("cottageId") long cottageId, Model model) {
        Request request = requestRepository.findBySubmitterIdAndCottageId(90L, cottageId); // placeholder
        requestRepository.delete(request);
        return "redirect:/reservations";
    }
}

