package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CottageController {
    Logger logger = LoggerFactory.getLogger(CottageController.class);

    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RequestService requestService;

    @GetMapping("/addcottage")
    public String showAddCottageForm(Model model, Cottage cottage) {
        model.addAttribute("cities", cityRepository.findAll());
        return "add-cottage";
    }

    @PostMapping("/addcottage")
    public String addCottage(@Valid Cottage cottage, BindingResult result, Model model, @SessionAttribute("loggedInUser") User login) {
        if (result.hasErrors()) {
            logger.warn("-- add cottage has errors: " + result.getAllErrors());
            return "add-cottage";
        }
        cottage.setOwner(login);
        cottageRepository.save(cottage);
        return "redirect:/my-cottages";
    }

    @GetMapping("/my-cottages")
    public String showCottageList(Model model, @SessionAttribute(value = "loggedInUser", required = false) User login) {
        if (login!= null) {
            List<Cottage> cottages = cottageRepository.findByOwnerId(login.getId());
            Map<Long, Boolean> cottageHasRequests = new HashMap<>();
            for (Cottage cottage : cottages) {
                boolean hasRequests = requestService.existsByCottageIdAndNoApproval(cottage.getId());
                cottageHasRequests.put(cottage.getId(), hasRequests);
            }
            model.addAttribute("cottages", cottages);
            model.addAttribute("hasRequests", cottageHasRequests);
        }
        return "my-cottages";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Cottage cottage = cottageRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid cottage Id:" + id));

        logger.info("-- show update with cottage: " + cottage);
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("cottage", cottage);
        return "update-cottage";
    }

    @PostMapping("/update/{id}")
    public String updateCottage(@PathVariable("id") long id, @Valid Cottage updatedCottage,
    BindingResult result, Model model) {
        if (result.hasErrors()) {
            // cottage.setId(id);
            return "update-cottage";
        }
        logger.info("-- update cottage: " + updatedCottage);
        Cottage existingCottage = cottageRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid cottage Id:" + id));

        existingCottage.setName(updatedCottage.getName());
        existingCottage.setSize_m2(updatedCottage.getSize_m2());
        existingCottage.getAddress().setStreet(updatedCottage.getAddress().getStreet());
        existingCottage.getAddress().setPostalCode(updatedCottage.getAddress().getPostalCode());
        existingCottage.getAddress().setCity(updatedCottage.getAddress().getCity());
        existingCottage.setRoomsNumber(updatedCottage.getRoomsNumber());
        existingCottage.setBathroomsNumber(updatedCottage.getBathroomsNumber());
        existingCottage.setMaxPeopleNum(updatedCottage.getMaxPeopleNum());
        existingCottage.setMinPricePerDay(updatedCottage.getMinPricePerDay());
        cottageRepository.save(existingCottage);
        return "redirect:/my-cottages";
    }

    @GetMapping("/delete/{id}")
    public String deleteCottage(@PathVariable("id") long id, Model model) {
        Cottage cottage = cottageRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid cottage Id:" + id));
        cottageRepository.delete(cottage);
        return "redirect:/my-cottages";
    }
}

