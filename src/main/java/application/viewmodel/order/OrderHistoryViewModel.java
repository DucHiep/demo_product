package application.viewmodel.order;

import application.data.model.Order;

import java.util.List;

public class OrderHistoryViewModel {
    List<Order> orderList;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
