package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.AgencyRepository;
import pl.coderslab.projectwybankuj.repository.BankRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/bank")
public class BankController {

    private final BankRepository bankRepository;
    private final AgencyRepository agencyRepository;

    public BankController(BankRepository bankRepository, AgencyRepository agencyRepository) {
        this.bankRepository = bankRepository;
        this.agencyRepository = agencyRepository;
    }

    @GetMapping
    public String allBanks(Model model) {
        model.addAttribute("banks", bankRepository.findAll());
        return "allbanks";
    }

    @GetMapping("/add")
    public String addInitForm(Model model) {
        model.addAttribute("bank", new Bank());
        return "addbank";
    }

    @PostMapping("/add")
    public String addPostForm(@Valid Bank bank, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addbank";
        }
        bankRepository.save(bank);
        return "redirect:/bank";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, Model model) {
        model.addAttribute("bank", bankRepository.findById(id));
        return "editbank";
    }

    @PostMapping("/edit")
    public String editPostForm(@Valid Bank bank, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editbank";
        }
        bankRepository.save(bank);
        return "redirect:/bank";
    }

    @GetMapping("/confirm")
    public String confirmDelete(@RequestParam Long id, Model model) {
        model.addAttribute("bank", bankRepository.findById(id));
        return "confirmbank";
    }

    @GetMapping("/delete")
    public String deleteBank(@RequestParam Long id) {
        bankRepository.deleteById(id);
        return "redirect:/bank";
    }
}
