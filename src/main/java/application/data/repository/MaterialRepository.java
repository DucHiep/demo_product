package application.data.repository;

import application.data.model.Material;
import application.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

    @Query("select count(m.id) from tbl_material m")
    long getTotalMaterials();

    @Query("select m from tbl_material m where m.name = :name")
    Object findByName(@Param("name") String name);

    ArrayList<Material> findByNameContaining(@Param("name") String name);

    @Query("select m from tbl_material m")
    ArrayList<Material> getAllMaterials();

    @Query("select m from tbl_material m where m.id = :id")
    Material findById(@Param("id") int id);
}


