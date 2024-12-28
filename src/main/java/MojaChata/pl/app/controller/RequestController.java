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

import java.util.List;
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
                                @RequestParam("cottageId") long cottageId, Model model) {
        Request request = requestRepository.findByCustomerIdAndCottageId(login.getId(), cottageId);
        RequestApproval requestApproval = requestApprovalsRepository.findByRequestId(request.getId());
        requestApprovalsRepository.delete(requestApproval);
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
        if (requestRepository.existsByCustomerIdAndCottageId(login.getId(), cottage.getId())) {
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
        List<RequestApproval> approvals;

        if ("approved".equalsIgnoreCase(status)) {
            approvals = requestApprovalsRepository.findByIsApprovedTrueAndRequest_CustomerId(login.getId());
        } else if ("pending".equalsIgnoreCase(status)) {
            approvals = requestApprovalsRepository.findByIsApprovedFalseAndRequest_CustomerId(login.getId());
        } else {
            approvals = requestApprovalsRepository.findByRequest_CustomerId(login.getId());
        }

        model.addAttribute("approvals", approvals);
        return "my-reservations";
    }
}
