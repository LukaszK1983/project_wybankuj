package pl.coderslab.projectwybankuj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.projectwybankuj.entity.Mortgage;
import pl.coderslab.projectwybankuj.entity.UserMortgage;
import pl.coderslab.projectwybankuj.repository.MortgageRepository;
import pl.coderslab.projectwybankuj.service.MortgageService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class HomeMortgageController {

    private final MortgageRepository mortgageRepository;
    private final MortgageService mortgageService;

    public HomeMortgageController(MortgageRepository mortgageRepository, MortgageService mortgageService) {
        this.mortgageRepository = mortgageRepository;
        this.mortgageService = mortgageService;
    }

    @GetMapping("/mortgageParameters")
    public String getMortgageParameters(Model model) {
        model.addAttribute("userMortgage", new UserMortgage());
        return "mortgageparameters";
    }

    @PostMapping("/mortgageParameters")
    public String postMortgageParameters(@ModelAttribute UserMortgage userMortgage,
                                         Model model) {
        List<Mortgage> mortgages = mortgageService.chooseOffers(userMortgage.getChooseInsurance(),
                userMortgage.getChooseServiceCharge(), userMortgage.getAmount(),
                userMortgage.getCreditPeriod(), userMortgage.getAge(), userMortgage.getContributionPercent());
        Map<Mortgage, BigDecimal> mortgagesWithPayments = mortgageService.calculateMortgagePayment(mortgages, userMortgage.getAmount(), userMortgage.getCreditPeriod());

        model.addAttribute("mortgageSimulation", mortgagesWithPayments);
        model.addAttribute("userMortgage", userMortgage);

        return "calculatemortgage";
    }

    @PostMapping("/mortgageDetails")
    public String getMortgageDetails(@RequestParam Long mortgageId, @ModelAttribute UserMortgage userMortgage, Model model) {
        Mortgage mortgage = mortgageRepository.findById(mortgageId).orElse(new Mortgage());
        model.addAttribute("mortgage", mortgage);

        BigDecimal payment = mortgageService.calculateChoosenMortgagePayment(mortgage, userMortgage.getAmount(), userMortgage.getCreditPeriod());
        model.addAttribute("payment", payment);

        BigDecimal serviceCharge = mortgageService.calculateServiceCharge(mortgage, userMortgage.getAmount());
        model.addAttribute("serviceCharge", serviceCharge);

        BigDecimal insurance = mortgageService.calculateInsurance(mortgage, userMortgage.getAmount());
        model.addAttribute("insurance", insurance);

        BigDecimal interests = mortgageService.calculateInterestsCost(mortgage, userMortgage.getAmount(), userMortgage.getCreditPeriod(), payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        BigDecimal totalCost = mortgageService.calculateTotalCost(mortgage, userMortgage.getAmount(), userMortgage.getCreditPeriod(), payment);
        model.addAttribute("totalCost", totalCost);

        model.addAttribute("userMortgage", userMortgage);

        return "mortgagedetails";
    }

    @ModelAttribute("answears")
    public List<String> answears() {
        return List.of("TAK", "NIE");
    }
}
