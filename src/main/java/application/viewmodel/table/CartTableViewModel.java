package application.viewmodel.table;

import application.data.model.Cart;

public class CartTableViewModel {
    private Cart cart;
    private int tableId;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
