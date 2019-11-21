package application.model.customquery;

public class DemoCustomQueryProduct {
    private int id;
    private String name;
    private int cId;
    private String cName;

    public DemoCustomQueryProduct(int id, String name, int cId, String cName) {
        this.id = id;
        this.name = name;
        this.cId = cId;
        this.cName = cName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
