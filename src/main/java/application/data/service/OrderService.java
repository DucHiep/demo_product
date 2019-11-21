package application.data.service;

import application.data.model.Order;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.OrderRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
     public static final Logger logger = LogManager.getLogger(OrderService.class) ;

     @Autowired
     private OrderRepository orderRepository;

    @Transactional
    public void addNewListOrder (List<Order> orders){
        orderRepository.save(orders);
    }

    public List<Order> getListAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }



    public PaginableItemList<Order> getListOrders(int pageSize, int pageNumber) {
        PaginableItemList<Order> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<Order> pages = orderRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }


    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public boolean updateOrder(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteOrder(int orderId) {
        try {
            orderRepository.delete(orderId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }



    public Order findOne(int OrderId) {
        return orderRepository.findOne(OrderId);
    }

    public List<Order> findOrderByUserName(String userName){
        return orderRepository.findOrderByUsername(userName);
    }


    public List<Order> findOrderByGuid(String guid){
        return orderRepository.findOrderByGuid(guid);
    }
}
