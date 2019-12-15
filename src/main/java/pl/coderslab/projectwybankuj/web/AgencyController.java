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
        return "allagencies";
    }

    @GetMapping("/add")
    public String addInitForm(Model model) {
        model.addAttribute("agency", new Agency());
        return "addagency";
    }

    @PostMapping("/add")
    public String addPostForm(@Valid Agency agency, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addagency";
        }
        agencyRepository.save(agency);
        return "redirect:/agency?bankId=" + agency.getBank().getId() + "";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, Model model) {
        model.addAttribute("agency", agencyRepository.findById(id));
        return "editagency";
    }

    @PostMapping("/edit")
    public String editPostForm(@Valid Agency agency, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editagency";
        }
        agencyRepository.save(agency);
        return "redirect:/agency?bankId=" + agency.getBank().getId() + "";
    }

    @GetMapping("/confirm")
    public String confirmDelete(@RequestParam Long id, @RequestParam Long bankId, Model model) {
        model.addAttribute("agency", agencyRepository.findById(id));
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "confirmagency";
    }

    @GetMapping("/delete")
    public String deleteAgency(@RequestParam Long id, @RequestParam Long bankId) {
        agencyRepository.deleteById(id);
        return "redirect:/agency?bankId=" + bankId + "";
    }

    @ModelAttribute("hours")
    public List<String> hours() {
        return Arrays.asList("8:00 - 18:00", "9:00 - 17:00", "9:30 - 17:30", "10:00 - 18:00");
    }

    @ModelAttribute("banks")
    public List<Bank> banks() {
        return (List<Bank>) bankRepository.findAll();
    }
}
