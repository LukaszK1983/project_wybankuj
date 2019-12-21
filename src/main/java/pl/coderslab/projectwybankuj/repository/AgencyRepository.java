package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.projectwybankuj.entity.Agency;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    List<Agency> findAllByBankId(Long bankId);

    List<Agency> findAllByBankIdAndCity(Long bankId, String city);
}
