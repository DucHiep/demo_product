package application.data.repository;

import application.data.model.MaterialHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialHistoryRepository extends JpaRepository<MaterialHistory, Integer> {
}
