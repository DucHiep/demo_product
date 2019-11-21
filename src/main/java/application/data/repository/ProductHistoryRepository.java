package application.data.repository;


import application.data.model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Integer> {
}
