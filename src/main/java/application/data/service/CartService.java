package application.data.service;

import application.data.model.Cart;
import application.data.repository.CartProductRepository;
import application.data.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductService cartProductService;

    public void addNewCart (Cart cart){
        cartRepository.save(cart);
    }

    public Cart findOne(int cartid) {
        return cartRepository.findOne(cartid);
    }

    public boolean updateCart (Cart cart) {
        try {
            cartRepository.save(cart);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deteleCart (int cartid) {
        try {
            Cart cart = this.findOne(cartid);
            cartProductService.deteleListCartProduct(cart.getListCartProducts());
            cartRepository.delete(cartid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Cart> findByUserName(String userName){
        return cartRepository.findByUserName(userName);
    }

    public List<Cart> findByGuid(String guid){
        return cartRepository.findByGuid(guid);
    }

    public List<Cart> findByTableId(int tableId){
        return cartRepository.findByTableId(tableId);
    }
}
