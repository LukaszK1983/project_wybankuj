package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projectwybankuj.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {
}
