package application.controller.api;

import application.data.model.Booker;
import application.data.service.BookerService;
import application.model.api.BaseApiResult;
import application.model.api.BookerDataModel;
import application.model.api.DataApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booker")
public class BookerApiController {

    @Autowired
    private BookerService bookerService;

    @PostMapping("/create")
    public BaseApiResult createBooker(@RequestBody BookerDataModel bookerDataModel){
        DataApiResult result = new DataApiResult();

        try {
            Booker booker = new Booker();
            booker.setComment(bookerDataModel.getComment());
            booker.setEmail(bookerDataModel.getEmail());
            booker.setName(bookerDataModel.getName());
            booker.setTableAmount(bookerDataModel.getTableAmount());
            booker.setPhone(bookerDataModel.getPhone());
            booker.setTimeBooked(bookerDataModel.getTimeBooked());
            bookerService.addNewBooker(booker);
            result.setMessage("Save booker successfully: " + booker.getId());
            result.setSuccess(true);
            result.setData(booker);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{bookerId}")
    public BaseApiResult detailBooker(@PathVariable int bookerId){
        DataApiResult result= new DataApiResult();

        try {
            Booker booker = bookerService.findOne(bookerId);
            BookerDataModel bookerDataModel = new BookerDataModel();
            bookerDataModel.setId(booker.getId());
            if(booker == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this booker");
            } else {
                result.setSuccess(true);
                result.setData(booker);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }




    @PostMapping("/delete/{bookerId}")
    public BaseApiResult deleteBooker(@PathVariable int bookerId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(bookerService.deteleBooker(bookerId)){
                result.setSuccess(true);
                result.setMessage("Delete booker successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
