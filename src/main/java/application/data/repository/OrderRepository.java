package application.data.repository;

import application.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional
    @Query("select o from tbl_order o where o.userName = :userName")
    List<Order> findOrderByUsername(@Param("userName") String userName);

    @Transactional
    @Query("select o from tbl_order o where o.guid = :guid")
    List<Order> findOrderByGuid(@Param("guid") String guid);

}
