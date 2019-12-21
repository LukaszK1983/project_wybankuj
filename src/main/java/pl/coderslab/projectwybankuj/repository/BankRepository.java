package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projectwybankuj.entity.Bank;

import java.util.Optional;

public interface BankRepository extends CrudRepository<Bank, Long> {

    Optional<Bank> findFirstById(Long bankId);
}
