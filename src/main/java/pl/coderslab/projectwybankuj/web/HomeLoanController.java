package pl.coderslab.projectwybankuj.web;

import com.itextpdf.text.DocumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Controller
public class HomeLoanController {

    private final static Logger logger = LogManager.getLogger(HomeLoanController.class);

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

        List<Loan> loans = loanService.chooseOffers(userLoan.getChooseInsurance(),
                userLoan.getChooseServiceCharge(), userLoan.getAmount(),
                userLoan.getCreditPeriod(), userLoan.getAge());
        Map<Loan, BigDecimal> loansWithPayments = loanService.calculateLoanPayment(loans, userLoan.getAmount(), userLoan.getCreditPeriod(), userLoan.getAge());

        model.addAttribute("loanSimulation", loansWithPayments);
        model.addAttribute("userLoan", userLoan);

        return "calculateloan";
    }

    @PostMapping("/loanDetails")
    public String getLoanDetails(@RequestParam Long loanId, @ModelAttribute UserLoan userLoan, Model model) {
        Loan loan = loanRepository.findById(loanId).orElse(new Loan());
        model.addAttribute("loan", loan);

        BigDecimal payment = loanService.calculateChoosenLoanPayment(loan, userLoan.getAmount(), userLoan.getCreditPeriod());
        model.addAttribute("payment", payment);

        BigDecimal serviceCharge = loanService.calculateServiceCharge(loan, userLoan.getAmount());
        model.addAttribute("serviceCharge", serviceCharge);

        BigDecimal insurance = loanService.calculateInsurance(loan, userLoan.getAmount());
        model.addAttribute("insurance", insurance);

        BigDecimal interests = loanService.calculateInterestsCost(userLoan.getAmount(), userLoan.getCreditPeriod(), payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        BigDecimal totalCost = loanService.calculateTotalCost(userLoan.getAmount(), userLoan.getCreditPeriod(), payment);
        model.addAttribute("totalCost", totalCost);

        model.addAttribute("userLoan", userLoan);

        return "loandetails";
    }

    @PostMapping("/generateOffer")
    public String generateOfferToPDF(@RequestParam Long loanId, @ModelAttribute UserLoan userLoan,
                                     Model model) throws DocumentException, IOException, URISyntaxException {

        Loan loan = loanRepository.findById(loanId).orElse(new Loan());
        model.addAttribute("loan", loan);

        BigDecimal payment = loanService.calculateChoosenLoanPayment(loan, userLoan.getAmount(), userLoan.getCreditPeriod());
        model.addAttribute("payment", payment);

        BigDecimal serviceCharge = loanService.calculateServiceCharge(loan, userLoan.getAmount());
        model.addAttribute("serviceCharge", serviceCharge);

        BigDecimal insurance = loanService.calculateInsurance(loan, userLoan.getAmount());
        model.addAttribute("insurance", insurance);

        BigDecimal interests = loanService.calculateInterestsCost(userLoan.getAmount(), userLoan.getCreditPeriod(), payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        BigDecimal totalCost = loanService.calculateTotalCost(userLoan.getAmount(), userLoan.getCreditPeriod(), payment);
        model.addAttribute("totalCost", totalCost);

        model.addAttribute("userLoan", userLoan);

        loanService.generatePDF(loan, userLoan, payment, serviceCharge, insurance,
                interests, totalCost);

        return "loandetails";
    }

    @ModelAttribute("answears")
    public List<String> answears() {
        return List.of("TAK", "NIE");
    }
}
