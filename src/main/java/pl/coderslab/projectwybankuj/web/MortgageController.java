package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.entity.Mortgage;
import pl.coderslab.projectwybankuj.repository.BankRepository;
import pl.coderslab.projectwybankuj.repository.MortgageRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mortgage")
public class MortgageController {

    private final MortgageRepository mortgageRepository;
    private final BankRepository bankRepository;

    public MortgageController(MortgageRepository mortgageRepository, BankRepository bankRepository) {
        this.mortgageRepository = mortgageRepository;
        this.bankRepository = bankRepository;
    }

    @GetMapping
    public String allMortgages(@RequestParam Long bankId, Model model) {
        model.addAttribute("mortgages", mortgageRepository.findAllByBankId(bankId));
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "allmortgages";
    }

    @GetMapping("/add")
    public String addInitForm(@RequestParam Long bankId, Model model) {
        model.addAttribute("mortgage", new Mortgage());
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "addmortgage";
    }

    @PostMapping("/add")
    public String addPostForm(@Valid Mortgage mortgage, BindingResult bindingResult,
                              @RequestParam Long bankId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bank", bankRepository.findById(bankId));
            return "addmortgage";
        }
        mortgageRepository.save(mortgage);
        return "redirect:/mortgage?bankId=" + mortgage.getBank().getId() + "";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, @RequestParam Long bankId, Model model) {
        model.addAttribute("mortgage", mortgageRepository.findById(id));
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "editmortgage";
    }

    @PostMapping("/edit")
    public String editPostForm(@Valid Mortgage mortgage, BindingResult bindingResult,
                               @RequestParam Long bankId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bank", bankRepository.findById(bankId));
            return "editmortgage";
        }
        mortgageRepository.save(mortgage);
        return "redirect:/mortgage?bankId=" + mortgage.getBank().getId() + "";
    }

    @GetMapping("/delete")
    public String deleteMortgage(@RequestParam Long id, @RequestParam Long bankId) {
        mortgageRepository.deleteById(id);
        return "redirect:/mortgage?bankId=" + bankId + "";
    }

    @ModelAttribute("banks")
    public List<Bank> banks() {
        return (List<Bank>) bankRepository.findAll();
    }
}
