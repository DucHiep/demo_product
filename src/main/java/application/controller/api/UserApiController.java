package application.controller.api;

import application.data.model.User;
import application.data.service.UserService;
import application.model.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;


    @GetMapping("/detail/{userId}")
    public BaseApiResult detailUser(@PathVariable int userId)
    {
        DataApiResult result = new DataApiResult();
        try {
            User exitUser = userService.findOne(userId);
            if(exitUser == null)
            {
                result.setSuccess(false);
                result.setMessage("can't find this User");
            }else {
                result.setSuccess(true);
                ModelMapper modelMapper = new ModelMapper();
                UserDetailModel userDetailModel = modelMapper.map(exitUser,UserDetailModel.class);
                result.setData(userDetailModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @PostMapping("/delete/{userId}")
    public BaseApiResult deleteUser(@PathVariable int userId) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(userService.deleteUser(userId)) {
                result.setSuccess(true);
                result.setMessage("Delete user successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
