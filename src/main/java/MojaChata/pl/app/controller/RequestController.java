package MojaChata.pl.app.controller;

import MojaChata.pl.app.model.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RequestController {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestApprovalsRepository requestApprovalsRepository;
    @Autowired
    private RequestService requestService;

    @GetMapping("/unrequest")
    public String unsendRequest(@SessionAttribute(value = "loggedInUser", required = false) User login,
                                @RequestParam("requestId") long requestId, Model model) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + requestId));;
        RequestApproval requestApproval = requestApprovalsRepository.findByRequestId(requestId);
        if (requestApproval != null) {
            requestApprovalsRepository.delete(requestApproval);
        }
        requestRepository.delete(request);
        return "redirect:/reservations";
    }

    @GetMapping("/request/addRequest/{cottageId}")
    public String AddRequest(@SessionAttribute(value = "loggedInUser", required = false) User login,
                                @PathVariable("cottageId") long cottageId, Model model, Request request) {
        if (login == null) {
            return "redirect:/login";
        }

        Cottage cottage = cottageRepository.findById(cottageId)
                .orElseThrow(() -> new RuntimeException("Invalid cottage Id: " + cottageId));
        if (requestService.existsByCustomerIdAndCottageIdAndNoApproval(login.getId(), cottage.getId())) {
            return "redirect:/reservations";
        }

        model.addAttribute("cottageId", cottageId);
        return "add-request";
    }

    @PostMapping("/request/addRequest/{cottageId}")
    public String SendRequest(@SessionAttribute(value = "loggedInUser", required = true) User login,
                             @PathVariable("cottageId") long cottageId, Model model, @ModelAttribute("request") Request request,
                              BindingResult bindingResult) {

        Cottage cottage = cottageRepository.findById(cottageId)
                .orElseThrow(() -> new RuntimeException("Invalid cottage ID: " + cottageId));

        if (request.getCheckInDate() == null) {
            bindingResult.rejectValue("checkInDate", "checkInDate.required", "Check-in date is required.");
        }

        if (request.getCheckOutDate() == null) {
            bindingResult.rejectValue("checkOutDate", "checkOutDate.required", "Check-out date is required.");
        }

        if (request.getCheckInDate() != null && request.getCheckOutDate() != null) {
            if (request.getCheckInDate().isAfter(request.getCheckOutDate())) {
                bindingResult.rejectValue("checkInDate", "checkInDate.invalid", "Check-in date cannot be after check-out date.");
            }
        }

        if (bindingResult.hasErrors()) {
            return "add-request";
        }

        request.setCustomerId(login.getId());
        request.setCottage(cottage);
        requestService.sendRequest(request);
        return "redirect:/search";
    }

    @GetMapping("/reservations")
    public String showReservations(@SessionAttribute(value = "loggedInUser", required = false) User login,
                                   @RequestParam(value = "status", required = false) String status, Model model) {
        List<Request> requests = requestRepository.findByCustomerId(login.getId());
        Map<Long, Integer> requestStatus = new HashMap<>();

        if ("approved".equalsIgnoreCase(status)) {
            requests = requests.stream()
                    .filter(request -> requestService.existsByRequestIdAndIsApproved(request.getId(), true))
                    .collect(Collectors.toList());
        } else if ("rejected".equalsIgnoreCase(status)) {
            requests = requests.stream()
                    .filter(request -> requestService.existsByRequestIdAndIsApproved(request.getId(), false))
                    .collect(Collectors.toList());
        } else if ("pending".equalsIgnoreCase(status)) {
            requests = requests.stream()
                    .filter(request -> !requestApprovalsRepository.existsByRequestId(request.getId()))
                    .collect(Collectors.toList());
        }

        for (Request request: requests) {
            requestStatus.put(request.getId(), requestService.getStatus(request.getId()));
        }

        model.addAttribute("requests", requests);
        model.addAttribute("requestStatus", requestStatus);
        return "my-reservations";
    }
}
