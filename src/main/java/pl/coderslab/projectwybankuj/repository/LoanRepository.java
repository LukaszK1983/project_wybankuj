package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projectwybankuj.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    Optional<Loan> findById(Long id);

    List<Loan> findAllByBankId(Long bankId);

    @Query("SELECT l FROM Loan l WHERE l.minCreditAmount <= :amount AND l.maxCreditAmount >= :amount AND l.maxCreditPeriod >= :creditPeriod AND l.minBorrowerAge <= :age AND l.maxBorrowerAge >= :age")
    List<Loan> findAllByParameters(int amount, int creditPeriod, int age);

    @Query("SELECT l FROM Loan l WHERE l.minCreditAmount <= :amount AND l.maxCreditAmount >= :amount AND l.maxCreditPeriod >= :creditPeriod AND l.minBorrowerAge <= :age AND l.maxBorrowerAge >= :age AND l.insurance = 0.0")
    List<Loan> findAllByParametersWithoutInsurance(int amount, int creditPeriod, int age);

    @Query("SELECT l FROM Loan l WHERE l.minCreditAmount <= :amount AND l.maxCreditAmount >= :amount AND l.maxCreditPeriod >= :creditPeriod AND l.minBorrowerAge <= :age AND l.maxBorrowerAge >= :age AND l.serviceCharge = 0.0")
    List<Loan> findAllByParametersWithoutServiceCharge(int amount, int creditPeriod, int age);

    @Query("SELECT l FROM Loan l WHERE l.minCreditAmount <= :amount AND l.maxCreditAmount >= :amount AND l.maxCreditPeriod >= :creditPeriod AND l.minBorrowerAge <= :age AND l.maxBorrowerAge >= :age AND l.insurance = 0.0 AND l.serviceCharge = 0.0")
    List<Loan> findAllByParametersWithoutInsuranceAndServiceCharge(int amount, int creditPeriod, int age);
}
