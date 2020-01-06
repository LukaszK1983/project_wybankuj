package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.projectwybankuj.entity.Logo;

@Repository
public interface LogoRepository extends JpaRepository<Logo, String> {
}
