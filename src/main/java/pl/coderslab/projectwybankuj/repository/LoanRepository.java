package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projectwybankuj.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findAllByBankId(Long bankId);
}
