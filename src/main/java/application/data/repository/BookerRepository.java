package application.data.repository;

import application.data.model.Booker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookerRepository extends JpaRepository<Booker,Integer> {

}
