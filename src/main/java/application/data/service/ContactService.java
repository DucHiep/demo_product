package application.data.service;

import application.data.model.Contact;
import application.data.model.PaginableItemList;
import application.data.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    public void addNewContact (Contact contact){
        contactRepository.save(contact);
    }

    public Contact findOne(int contactId) {
        return contactRepository.findOne(contactId);
    }

    public boolean updateContact (Contact contact) {
        try {
            contactRepository.save(contact);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deteleContact (int contactId) {
        try {
            contactRepository.delete(contactId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PaginableItemList<Contact> getListContacts(int pageSize, int pageNumber) {
        PaginableItemList<Contact> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<Contact> pages = contactRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public List<Contact> getListAllContacts() {
        try {
            return contactRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
