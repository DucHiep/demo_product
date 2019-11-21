package application.data.repository;

import application.data.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {
}
