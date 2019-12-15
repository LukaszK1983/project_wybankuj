package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.entity.Loan;
import pl.coderslab.projectwybankuj.repository.BankRepository;
import pl.coderslab.projectwybankuj.repository.LoanRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private final LoanRepository loanRepository;
    private final BankRepository bankRepository;

    public LoanController(LoanRepository loanRepository, BankRepository bankRepository) {
        this.loanRepository = loanRepository;
        this.bankRepository = bankRepository;
    }

    @GetMapping
    public String allLoans(@RequestParam Long bankId, Model model) {
        model.addAttribute("loans", loanRepository.findAllByBankId(bankId));
        return "allloans";
    }

    @GetMapping("/add")
    public String addInitForm(Model model) {
        model.addAttribute("loan", new Loan());
        return "addloan";
    }

    @PostMapping("/add")
    public String addPostForm(@Valid Loan loan, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addloan";
        }
        loanRepository.save(loan);
        return "redirect:/loan?bankId=" + loan.getBank().getId() + "";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, Model model) {
        model.addAttribute("loan", loanRepository.findById(id));
        return "editloan";
    }

    @PostMapping("/edit")
    public String editPostForm(@Valid Loan loan, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editloan";
        }
        loanRepository.save(loan);
        return "redirect:/loan?bankId=" + loan.getBank().getId() + "";
    }

    @GetMapping("/confirm")
    public String confirmDelete(@RequestParam Long id, @RequestParam Long bankId, Model model) {
        model.addAttribute("loan", loanRepository.findById(id));
        model.addAttribute("bank", bankRepository.findById(bankId));
        return "confirmloan";
    }

    @GetMapping("/delete")
    public String deleteLoan(@RequestParam Long id, @RequestParam Long bankId) {
        loanRepository.deleteById(id);
        return "redirect:/loan?bankId=" + bankId + "";
    }

    @ModelAttribute("banks")
    public List<Bank> banks() {
        return (List<Bank>) bankRepository.findAll();
    }
}
