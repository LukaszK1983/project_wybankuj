package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.BankRepository;
import pl.coderslab.projectwybankuj.service.LogoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/bank")
public class BankController {

    private final BankRepository bankRepository;
    private final LogoService bankService;

    public BankController(BankRepository bankRepository, LogoService bankService) {
        this.bankRepository = bankRepository;
        this.bankService = bankService;
    }

    @GetMapping
    public String allBanks(Model model) {
        model.addAttribute("banks", bankRepository.findAll());
        return "allbanks";
    }

    @GetMapping("/logo")
    public String addLogo(@RequestParam Long bankId, Model model) {
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "addlogo";
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

    @GetMapping("/delete")
    public String deleteBank(@RequestParam Long id) {
        bankRepository.deleteById(id);
        return "redirect:/bank";
    }
}
