package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.projectwybankuj.entity.Agency;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.AgencyRepository;
import pl.coderslab.projectwybankuj.repository.BankRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/agency")
public class AgencyController {

    private final AgencyRepository agencyRepository;
    private final BankRepository bankRepository;

    public AgencyController(AgencyRepository agencyRepository, BankRepository bankRepository) {
        this.agencyRepository = agencyRepository;
        this.bankRepository = bankRepository;
    }

    @GetMapping
    public String allAgencies(@RequestParam Long bankId, Model model) {
        model.addAttribute("agencies", agencyRepository.findAllByBankId(bankId));
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "allagencies";
    }

    @GetMapping("/add")
    public String addInitForm(@RequestParam Long bankId, Model model) {
        model.addAttribute("agency", new Agency());
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "addagency";
    }

    @PostMapping("/add")
    public String addPostForm(@Valid Agency agency, BindingResult bindingResult,
                              @RequestParam Long bankId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bank", bankRepository.findById(bankId));
            return "addagency";
        }
        agencyRepository.save(agency);
        return "redirect:/agency?bankId=" + agency.getBank().getId() + "";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, @RequestParam Long bankId, Model model) {
        model.addAttribute("agency", agencyRepository.findById(id));
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "editagency";
    }

    @PostMapping("/edit")
    public String editPostForm(@Valid Agency agency, BindingResult bindingResult,
                               @RequestParam Long bankId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bank", bankRepository.findById(bankId));
            return "editagency";
        }
        agencyRepository.save(agency);
        return "redirect:/agency?bankId=" + agency.getBank().getId() + "";
    }

    @GetMapping("/delete")
    public String deleteAgency(@RequestParam Long id, @RequestParam Long bankId) {
        agencyRepository.deleteById(id);
        return "redirect:/agency?bankId=" + bankId + "";
    }

    @ModelAttribute("hours")
    public List<String> hours() {
        return Arrays.asList("08:00 - 18:00", "09:00 - 17:00", "09:30 - 17:30", "10:00 - 18:00");
    }

    @ModelAttribute("banks")
    public List<Bank> banks() {
        return (List<Bank>) bankRepository.findAll();
    }
}
