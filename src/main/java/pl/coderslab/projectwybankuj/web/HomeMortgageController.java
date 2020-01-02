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

        List<Mortgage> mortgages;
        if (userMortgage.getChooseInsurance().equals("no") && userMortgage.getChooseServiceCharge().equals("no")) {
            mortgages = mortgageRepository.findAllByParametersWithoutInsuranceAndServiceCharge(userMortgage.getAmount(), userMortgage.getCreditPeriod(), userMortgage.getAge(), userMortgage.getContributionPercent());
        } else if (userMortgage.getChooseInsurance().equals("no") && userMortgage.getChooseServiceCharge().equals("yes")) {
            mortgages = mortgageRepository.findAllByParametersWithoutInsurance(userMortgage.getAmount(), userMortgage.getCreditPeriod(), userMortgage.getAge(), userMortgage.getContributionPercent());
        } else if (userMortgage.getChooseServiceCharge().equals("no") && userMortgage.getChooseInsurance().equals("yes")) {
            mortgages = mortgageRepository.findAllByParametersWithoutServiceCharge(userMortgage.getAmount(), userMortgage.getCreditPeriod(), userMortgage.getAge(), userMortgage.getContributionPercent());
        } else {
            mortgages = mortgageRepository.findAllByParameters(userMortgage.getAmount(), userMortgage.getCreditPeriod(), userMortgage.getAge(), userMortgage.getContributionPercent());
        }
        Map<Mortgage, BigDecimal> mortgagesWithPayments = mortgageService.calculateMortgagePayment(mortgages, userMortgage.getAmount(), userMortgage.getCreditPeriod());

        model.addAttribute("mortgageSimulation", mortgagesWithPayments);
        model.addAttribute("amount", userMortgage.getAmount());
        model.addAttribute("creditPeriod", userMortgage.getCreditPeriod());
        model.addAttribute("contributionPercent", userMortgage.getContributionPercent());
        model.addAttribute("chooseServiceCharge", userMortgage.getChooseServiceCharge());
        model.addAttribute("chooseInsurance", userMortgage.getChooseInsurance());
        model.addAttribute("age", userMortgage.getAge());

        return "calculatemortgage";
    }

    @GetMapping("/mortgageDetails")
    public String getMortgageDetails(@RequestParam Long mortgageId, @RequestParam int amount, @RequestParam int creditPeriod,
                                     @RequestParam double contributionPercent, @RequestParam String chooseServiceCharge,
                                     @RequestParam String chooseInsurance, @RequestParam int age, Model model) {
        Mortgage mortgage = mortgageRepository.findById(mortgageId).orElse(new Mortgage());
        model.addAttribute("mortgage", mortgage);

        BigDecimal payment = mortgageService.calculateChoosenMortgagePayment(mortgage, amount, creditPeriod);
        model.addAttribute("payment", payment);

        BigDecimal serviceCharge = mortgageService.calculateServiceCharge(mortgage, amount);
        model.addAttribute("serviceCharge", serviceCharge);

        BigDecimal insurance = mortgageService.calculateInsurance(mortgage, amount);
        model.addAttribute("insurance", insurance);

        BigDecimal interests = mortgageService.calculateInterestsCost(mortgage, amount, creditPeriod, payment, serviceCharge, insurance);
        model.addAttribute("interests", interests);

        BigDecimal totalCost = mortgageService.calculateTotalCost(mortgage, amount, creditPeriod, payment);
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
}
