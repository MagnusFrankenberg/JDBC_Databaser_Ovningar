package OvningsUppgifter.Uppg_1;

public class Barn {

    private int id;
    private String namn;
    private int uppförandetal;

    public Barn (){

    }

    public Barn(int id, String namn, int uppförandetal) {
        this.id = id;
        this.namn = namn;
        this.uppförandetal = uppförandetal;
    }

    public int getUppförandetal() {
        return uppförandetal;
    }

    public void setUppförandetal(int uppförandetal) {
        this.uppförandetal = uppförandetal;
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
