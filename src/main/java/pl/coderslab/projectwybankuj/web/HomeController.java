package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.projectwybankuj.entity.Agency;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.entity.Loan;
import pl.coderslab.projectwybankuj.entity.Mortgage;
import pl.coderslab.projectwybankuj.repository.AgencyRepository;
import pl.coderslab.projectwybankuj.repository.BankRepository;
import pl.coderslab.projectwybankuj.repository.LoanRepository;
import pl.coderslab.projectwybankuj.repository.MortgageRepository;
import pl.coderslab.projectwybankuj.service.EmailService;
import pl.coderslab.projectwybankuj.service.LoanService;
import pl.coderslab.projectwybankuj.service.MortgageService;

import java.util.List;
import java.util.Map;

//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;

@Controller
@RequestMapping("/")
public class HomeController {

    private final LoanRepository loanRepository;
    private final LoanService loanService;
    private final EmailService emailService;
    //    private final TemplateEngine templateEngine;
    private final AgencyRepository agencyRepository;
    private final BankRepository bankRepository;
    private final MortgageRepository mortgageRepository;
    private final MortgageService mortgageService;

    public HomeController(LoanRepository loanRepository, LoanService loanService, EmailService emailService, AgencyRepository agencyRepository, BankRepository bankRepository, MortgageRepository mortgageRepository, MortgageService mortgageService) {
        this.loanRepository = loanRepository;
        this.loanService = loanService;
        this.emailService = emailService;
        this.agencyRepository = agencyRepository;
        this.bankRepository = bankRepository;
        this.mortgageRepository = mortgageRepository;
        this.mortgageService = mortgageService;
    }

    @GetMapping
    public String startApp() {
        return "index";
    }

    @GetMapping("/loanParameters")
    public String getLoanParameters() {
        return "loanparameters";
    }

    @PostMapping("/loanParameters")
    public String postLoanParameters(@RequestParam int amount, @RequestParam int creditPeriod,
                                     @RequestParam String chooseServiceCharge, @RequestParam String chooseInsurance,
                                     @RequestParam int age, Model model) {

        List<Loan> loans;
        if (chooseInsurance.equals("no") && chooseServiceCharge.equals("no")) {
            loans = loanRepository.findAllByParametersWithoutInsuranceAndServiceCharge(amount, creditPeriod, age);
        } else if (chooseInsurance.equals("no") && chooseServiceCharge.equals("yes")) {
            loans = loanRepository.findAllByParametersWithoutInsurance(amount, creditPeriod, age);
        } else if (chooseServiceCharge.equals("no") && chooseInsurance.equals("yes")) {
            loans = loanRepository.findAllByParametersWithoutServiceCharge(amount, creditPeriod, age);
        } else {
            loans = loanRepository.findAllByParameters(amount, creditPeriod, age);
        }
        Map<Loan, Double> loansWithPayments = loanService.calculateLoanPayment(loans, amount, creditPeriod);

        model.addAttribute("loanSimulation", loansWithPayments);
        model.addAttribute("amount", amount);
        model.addAttribute("creditPeriod", creditPeriod);
        model.addAttribute("chooseServiceCharge", chooseServiceCharge);
        model.addAttribute("chooseInsurance", chooseInsurance);
        model.addAttribute("age", age);

        return "calculateloan";
    }

    @GetMapping("/loanDetails")
    public String getLoanDetails(@RequestParam Long loanId, @RequestParam int amount, @RequestParam int creditPeriod,
                                 @RequestParam String chooseServiceCharge, @RequestParam String chooseInsurance,
                                 @RequestParam int age, Model model) {
        Loan loan = loanRepository.findById(loanId).orElse(new Loan());
        model.addAttribute("loan", loan);

        double payment = loanService.calculateChoosenLoanPayment(loan, amount, creditPeriod);
        model.addAttribute("payment", payment);

        double serviceCharge = loanService.calculateServiceCharge(loan, amount);
        model.addAttribute("serviceCharge", serviceCharge);

        double insurance = loanService.calculateInsurance(loan, amount);
        model.addAttribute("insurance", insurance);

        double interests = loanService.calculateInterestsCost(loan, amount, creditPeriod, payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        double totalCost = loanService.calculateTotalCost(loan, amount, creditPeriod, payment);
        model.addAttribute("totalCost", totalCost);

        model.addAttribute("amount", amount);
        model.addAttribute("creditPeriod", creditPeriod);
        model.addAttribute("serviceCharge", serviceCharge);
        model.addAttribute("insurance", insurance);
        model.addAttribute("age", age);
        model.addAttribute("chooseServiceCharge", chooseServiceCharge);
        model.addAttribute("chooseInsurance", chooseInsurance);

        return "loandetails";
    }

    @GetMapping("/mortgageParameters")
    public String getMortgageParameters() {
        return "mortgageparameters";
    }

    @PostMapping("/mortgageParameters")
    public String postMortgageParameters(@RequestParam int amount, @RequestParam int creditPeriod,
                                         @RequestParam double contributionPercent, @RequestParam String chooseServiceCharge,
                                         @RequestParam String chooseInsurance, @RequestParam int age,
                                         Model model) {

        List<Mortgage> mortgages;
        if (chooseInsurance.equals("no") && chooseServiceCharge.equals("no")) {
            mortgages = mortgageRepository.findAllByParametersWithoutInsuranceAndServiceCharge(amount, creditPeriod, age, contributionPercent);
        } else if (chooseInsurance.equals("no") && chooseServiceCharge.equals("yes")) {
            mortgages = mortgageRepository.findAllByParametersWithoutInsurance(amount, creditPeriod, age, contributionPercent);
        } else if (chooseServiceCharge.equals("no") && chooseInsurance.equals("yes")) {
            mortgages = mortgageRepository.findAllByParametersWithoutServiceCharge(amount, creditPeriod, age, contributionPercent);
        } else {
            mortgages = mortgageRepository.findAllByParameters(amount, creditPeriod, age, contributionPercent);
        }
        Map<Mortgage, Double> mortgagesWithPayments = mortgageService.calculateMortgagePayment(mortgages, amount, creditPeriod);

        model.addAttribute("mortgageSimulation", mortgagesWithPayments);
        model.addAttribute("amount", amount);
        model.addAttribute("creditPeriod", creditPeriod);
        model.addAttribute("contributionPercent", contributionPercent);
        model.addAttribute("chooseServiceCharge", chooseServiceCharge);
        model.addAttribute("chooseInsurance", chooseInsurance);
        model.addAttribute("age", age);

        return "calculatemortgage";
    }

    @GetMapping("/mortgageDetails")
    public String getMortgageDetails(@RequestParam Long mortgageId, @RequestParam int amount, @RequestParam int creditPeriod,
                                     @RequestParam double contributionPercent, @RequestParam String chooseServiceCharge,
                                     @RequestParam String chooseInsurance, @RequestParam int age, Model model) {
        Mortgage mortgage = mortgageRepository.findById(mortgageId).orElse(new Mortgage());
        model.addAttribute("mortgage", mortgage);

        double payment = mortgageService.calculateChoosenMortgagePayment(mortgage, amount, creditPeriod);
        model.addAttribute("payment", payment);

        double serviceCharge = mortgageService.calculateServiceCharge(mortgage, amount);
        model.addAttribute("serviceCharge", serviceCharge);

        double insurance = mortgageService.calculateInsurance(mortgage, amount);
        model.addAttribute("insurance", insurance);

        double interests = mortgageService.calculateInterestsCost(mortgage, amount, creditPeriod, payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        double totalCost = mortgageService.calculateTotalCost(mortgage, amount, creditPeriod, payment);
        model.addAttribute("totalCost", totalCost);

        model.addAttribute("amount", amount);
        model.addAttribute("creditPeriod", creditPeriod);
        model.addAttribute("contributionPercent", contributionPercent);
        model.addAttribute("serviceCharge", serviceCharge);
        model.addAttribute("insurance", insurance);
        model.addAttribute("age", age);
        model.addAttribute("chooseServiceCharge", chooseServiceCharge);
        model.addAttribute("chooseInsurance", chooseInsurance);

        return "mortgagedetails";
    }

    @ModelAttribute("answears")
    public List<String> answears() {
        return List.of("TAK", "NIE");
    }

    @GetMapping("/listOfAgencies")
    public String getAgencies(@RequestParam Long bankId, @RequestParam int amount,
                              @RequestParam int creditperiod, Model model) {
        List<Agency> agencies = agencyRepository.findAllByBankId(bankId);
        model.addAttribute("agencies", agencies);

        Bank bank = bankRepository.findFirstById(bankId).orElseThrow();
        model.addAttribute("bank", bank);

        model.addAttribute("amount", amount);
        model.addAttribute("creditperiod", creditperiod);

        return "listofagencies";
    }

    @PostMapping("/listOfAgencies")
    public String getAgenciesByCity(@RequestParam Long bankId, @RequestParam String city,
                                    @RequestParam int amount, @RequestParam int creditperiod,
                                    Model model) {
        List<Agency> agencies = agencyRepository.findAllByBankIdAndCity(bankId, city);
        model.addAttribute("agencies", agencies);

        Bank bank = bankRepository.findFirstById(bankId).orElseThrow();
        model.addAttribute("bank", bank);

        model.addAttribute("amount", amount);
        model.addAttribute("creditperiod", creditperiod);

        return "listofagencies";
    }

    @GetMapping("/agencyContactForm")
    public String agencyContactForm(@RequestParam Long agencyId, @RequestParam int amount,
                                    @RequestParam int creditperiod, Model model) {
        model.addAttribute("agency", agencyRepository.findById(agencyId));
        model.addAttribute("amount", amount);
        model.addAttribute("creditperiod", creditperiod);
        return "contactform";
    }

    @PostMapping("/agencyContactForm")
    public String sendAgencyContactForm(@RequestParam Long agencyId, @RequestParam String name,
                                        @RequestParam String email, @RequestParam String phone,
                                        @RequestParam String message) {
//        Context context = new Context();
//        context.setVariable("name", name);
//        context.setVariable("contact", email);
//        context.setVariable("message", message);
//
//        String body = templateEngine.process("message", context);
//        emailService.send(agencyRepository.findById(agencyId).get().getEmail(), "Wiadomość z Wybankuj", body);
        String agencyMail = agencyRepository.findById(agencyId).get().getEmail();
        emailService.send(agencyMail, name, message);
        return "index";
    }
}
