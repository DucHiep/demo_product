package application.viewmodel.admin;

import application.data.model.Material;

import java.util.List;

public class AdminMaterialVM {
    private String keyWord;
    private int currentPage;
    private int totalPagingItems;
    private List<Material> materialList;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

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

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }
}
