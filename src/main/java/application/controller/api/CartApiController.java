package application.controller.api;

import application.data.model.Cart;
import application.data.service.CartService;
import application.model.api.BaseApiResult;
import application.model.api.CartDataModel;
import application.model.api.DataApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public BaseApiResult createCart(@RequestBody CartDataModel cartDataModel){
        DataApiResult result = new DataApiResult();

        try {
            Cart cart = new Cart();
            cart.setGuid(cartDataModel.getGuid());
            if(cartDataModel.getUserName()!=null){
                cart.setUserName(cartDataModel.getUserName());
            }
            cart.setCreatedDate(cartDataModel.getCreatedDate());
            cartService.addNewCart(cart);
            result.setData(cart.getId());
            result.setMessage("Save cart successfully: " + cart.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{cartId}")
    public BaseApiResult detailCart(@PathVariable int cartId){
        DataApiResult result= new DataApiResult();

        try {
            Cart cart = cartService.findOne(cartId);
            if(cart == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this cart");
            } else {
                result.setSuccess(true);
                result.setData(cart);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/delete/{cartId}")
    public BaseApiResult deleteMaterial(@PathVariable int cartId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(cartService.deteleCart(cartId)){
                result.setSuccess(true);
                result.setMessage("Delete cart successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
