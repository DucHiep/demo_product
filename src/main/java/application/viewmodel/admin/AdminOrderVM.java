package application.viewmodel.admin;

import application.data.model.Order;

import java.util.List;

public class AdminOrderVM {
    private int currentPage;
    private int totalPagingItems;
    private List<Order> orderList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPagingItems() {
        return totalPagingItems;
    }

    public void setTotalPagingItems(int totalPagingItems) {
        this.totalPagingItems = totalPagingItems;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
