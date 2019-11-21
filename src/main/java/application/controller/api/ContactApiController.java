package application.controller.api;


import application.data.model.Contact;
import application.data.service.ContactService;
import application.model.api.BaseApiResult;
import application.model.api.ContactDataModel;
import application.model.api.DataApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactApiController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public BaseApiResult createContact(@RequestBody ContactDataModel contactDataModel){
        DataApiResult result = new DataApiResult();

        try {
            Contact contact = new Contact();
            contact.setComment(contactDataModel.getComment());
            contact.setEmail(contactDataModel.getEmail());
            contact.setName(contactDataModel.getName());
            contactService.addNewContact(contact);
            result.setMessage("Save contact successfully: " + contact.getId());
            result.setSuccess(true);
            result.setData(contact);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }



    @PostMapping("/delete/{contactId}")
    public BaseApiResult deleteContact(@PathVariable int contactId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(contactService.deteleContact(contactId)){
                result.setSuccess(true);
                result.setMessage("Delete contact successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


}
