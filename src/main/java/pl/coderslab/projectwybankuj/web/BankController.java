package pl.coderslab.projectwybankuj.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.repository.BankRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/bank")
public class BankController {

    private final static Logger logger = LogManager.getLogger(BankController.class);

    private final BankRepository bankRepository;
    private final HttpServletRequest request;

    public BankController(BankRepository bankRepository, HttpServletRequest request) {
        this.bankRepository = bankRepository;
        this.request = request;
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
    public String addPostForm(@RequestParam("file") MultipartFile file, @Valid Bank bank, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addbank";
        }
        String logoName = "logo" + bank.getBankName() + ".jpg";
        String filePath = request.getSession().getServletContext().getRealPath("/img");
        File fileToUpload = new File(filePath + "/" + logoName);
        file.transferTo(fileToUpload);
        Bank bankToSave = new Bank(bank.getBankName(), logoName);
        bankRepository.save(bankToSave);
        return "redirect:/bank";
    }

    @GetMapping("/edit")
    public String editInitForm(@RequestParam Long id, Model model) {
        model.addAttribute("bank", bankRepository.findById(id));
        return "editbank";
    }

    @PostMapping("/edit")
    public String editPostForm(@RequestParam("file") MultipartFile file, @RequestParam String logo,
                               @Valid Bank bank, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "editbank";
        }

        String filePath = request.getSession().getServletContext().getRealPath("/img");

        if (file.isEmpty()) {
            String logoToSet = "logo" + bank.getBankName() + ".jpg";
            bank.setLogo(logoToSet);
            bankRepository.save(bank);
            File actualFile = new File(filePath + "/" + logo);
            File fileToChange = new File(filePath + "/" + logoToSet);
            actualFile.renameTo(fileToChange);
        } else {
            File fileToDelete = new File(filePath + "/" + logo);
            fileToDelete.delete();
            String logoName = "logo" + bank.getBankName() + ".jpg";
            File fileToUpload = new File(filePath + "/" + logoName);
            file.transferTo(fileToUpload);
            bank.setLogo(logoName);
            bankRepository.save(bank);
        }
        return "redirect:/bank";
    }

    @GetMapping("/delete")
    public String deleteBank(@RequestParam Long id) {
        Bank bank = bankRepository.findById(id).orElseThrow();
        String filePath = request.getSession().getServletContext().getRealPath("/img");
        File fileToDelete = new File(filePath + "/" + bank.getLogo());
        fileToDelete.delete();
        bankRepository.deleteById(id);
        return "redirect:/bank";
    }
}