package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.projectwybankuj.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
