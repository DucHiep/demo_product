package application.viewmodel.material;

import application.data.model.Material;
import application.data.model.Product;

import java.util.ArrayList;

public class MaterialSearchVM {

    private ArrayList<Material> materials;
    private String keyWord;

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
