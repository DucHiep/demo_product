package application.viewmodel.order;

import application.data.model.Order;
import application.data.model.ProductOrder;

import java.util.List;

public class OrderDetailViewModel {
    private Order order;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
