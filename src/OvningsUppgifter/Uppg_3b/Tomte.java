package OvningsUppgifter.Uppg_3b;

public class Tomte {

    private int id;
    private String namn;

    public Tomte(){}

    public Tomte(int id, String namn) {
        this.id = id;
        this.namn = namn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }
}
