package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projectwybankuj.entity.Mortgage;

import java.util.List;

public interface MortgageRepository extends CrudRepository<Mortgage, Long> {

    List<Mortgage> findAllByBankId(Long bankId);
}
