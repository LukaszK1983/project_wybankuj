package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.service.BankService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public String allBanks(Model model) {
        model.addAttribute("banks", bankService.findAllBanks());
        return "allbanks";
    }

    @GetMapping("/add")
    public String addInitForm(Model model) {
        model.addAttribute("bank", new Bank());
        return "addbank";
    }

    @PostMapping("/add")
    public String addPostForm(@RequestParam("file") MultipartFile file, @Valid Bank bank, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addbank";
        }
        bankService.addBankWithLogo(file, bank);
        return "redirect:/bank";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, Model model) {
        model.addAttribute("bank", bankService.findBankById(id));
        return "editbank";
    }

    @PostMapping("/edit")
    public String editPostForm(@RequestParam("file") MultipartFile file, @Valid Bank bank,
                               @RequestParam String logo, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "editbank";
        }
        bankService.editBank(file, bank, logo);
        return "redirect:/bank";
    }

    @GetMapping("/delete")
    public String deleteBank(@RequestParam Long id) {
        bankService.deleteBank(id);
        return "redirect:/bank";
    }
}