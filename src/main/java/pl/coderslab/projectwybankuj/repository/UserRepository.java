package pl.coderslab.projectwybankuj.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.projectwybankuj.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
