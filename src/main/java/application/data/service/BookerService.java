package application.data.service;

import application.data.model.Booker;
import application.data.model.CartProduct;
import application.data.model.PaginableItemList;
import application.data.repository.BookerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookerService {

    @Autowired
    private BookerRepository bookerRepository;

    public void addNewBooker (Booker booker){
        bookerRepository.save(booker);
    }

    public Booker findOne(int bookerId) {
        return bookerRepository.findOne(bookerId);
    }

    public boolean updateBooker (Booker booker) {
        try {
            bookerRepository.save(booker);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deteleBooker (int bookerId) {
        try {
            bookerRepository.delete(bookerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PaginableItemList<Booker> getListBookers(int pageSize, int pageNumber) {
        PaginableItemList<Booker> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<Booker> pages = bookerRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public List<Booker> getListAllBookers() {
        try {
            return bookerRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
