package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.projectwybankuj.entity.Loan;
import pl.coderslab.projectwybankuj.entity.UserLoan;
import pl.coderslab.projectwybankuj.repository.LoanRepository;
import pl.coderslab.projectwybankuj.service.LoanService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class HomeLoanController {

    private final LoanRepository loanRepository;
    private final LoanService loanService;

    public HomeLoanController(LoanRepository loanRepository, LoanService loanService) {
        this.loanRepository = loanRepository;
        this.loanService = loanService;
    }

    @GetMapping("/loanParameters")
    public String getLoanParameters(Model model) {
        model.addAttribute("userLoan", new UserLoan());
        return "loanparameters";
    }

    @PostMapping("/loanParameters")
    public String postLoanParameters(@ModelAttribute UserLoan userLoan, Model model) {

        List<Loan> loans;
        if (userLoan.getChooseInsurance().equals("no") && userLoan.getChooseServiceCharge().equals("no")) {
            loans = loanRepository.findAllByParametersWithoutInsuranceAndServiceCharge(userLoan.getAmount(), userLoan.getCreditPeriod(), userLoan.getAge());
        } else if (userLoan.getChooseInsurance().equals("no") && userLoan.getChooseServiceCharge().equals("yes")) {
            loans = loanRepository.findAllByParametersWithoutInsurance(userLoan.getAmount(), userLoan.getCreditPeriod(), userLoan.getAge());
        } else if (userLoan.getChooseServiceCharge().equals("no") && userLoan.getChooseInsurance().equals("yes")) {
            loans = loanRepository.findAllByParametersWithoutServiceCharge(userLoan.getAmount(), userLoan.getCreditPeriod(), userLoan.getAge());
        } else {
            loans = loanRepository.findAllByParameters(userLoan.getAmount(), userLoan.getCreditPeriod(), userLoan.getAge());
        }
        Map<Loan, BigDecimal> loansWithPayments = loanService.calculateLoanPayment(loans, userLoan.getAmount(), userLoan.getCreditPeriod(), userLoan.getAge());

        model.addAttribute("loanSimulation", loansWithPayments);
        model.addAttribute("amount", userLoan.getAmount());
        model.addAttribute("creditPeriod", userLoan.getCreditPeriod());
        model.addAttribute("chooseServiceCharge", userLoan.getChooseServiceCharge());
        model.addAttribute("chooseInsurance", userLoan.getChooseInsurance());
        model.addAttribute("age", userLoan.getAge());

        return "calculateloan";
    }

    @GetMapping("/loanDetails")
    public String getLoanDetails(@RequestParam Long loanId, @RequestParam int amount, @RequestParam int creditPeriod,
                                 @RequestParam String chooseServiceCharge, @RequestParam String chooseInsurance,
                                 @RequestParam int age, Model model) {
        Loan loan = loanRepository.findById(loanId).orElse(new Loan());
        model.addAttribute("loan", loan);

        BigDecimal payment = loanService.calculateChoosenLoanPayment(loan, amount, creditPeriod);
        model.addAttribute("payment", payment);

        BigDecimal serviceCharge = loanService.calculateServiceCharge(loan, amount);
        model.addAttribute("serviceCharge", serviceCharge);

        BigDecimal insurance = loanService.calculateInsurance(loan, amount);
        model.addAttribute("insurance", insurance);

        BigDecimal interests = loanService.calculateInterestsCost(loan, amount, creditPeriod, payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        BigDecimal totalCost = loanService.calculateTotalCost(loan, amount, creditPeriod, payment);
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

    @ModelAttribute("answears")
    public List<String> answears() {
        return List.of("TAK", "NIE");
    }
}
