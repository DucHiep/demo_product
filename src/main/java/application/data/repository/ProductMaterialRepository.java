package application.data.repository;

import application.data.model.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Integer> {
}
