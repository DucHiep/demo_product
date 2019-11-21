package application.data.service;

import application.data.model.MaterialHistory;
import application.data.model.PaginableItemList;
import application.data.repository.MaterialHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialHistoryService {

    @Autowired
    private MaterialHistoryRepository materialHistoryRepository;

    public void addNewMaterialHistory (MaterialHistory materialHistory){
        materialHistoryRepository.save(materialHistory);
    }

    public List<MaterialHistory> getListAllMaterialHistories() {
        try {
            return materialHistoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public PaginableItemList<MaterialHistory> getListMaterialHistories(int pageSize, int pageNumber) {
        PaginableItemList<MaterialHistory> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<MaterialHistory> pages = materialHistoryRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }


}
