package OvningsUppgifter.Uppg_2a;


import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
       Repository r = new Repository();

        String barnNamn = "Karl";

       int u = r.getUppförande(barnNamn);
        System.out.println("Uppförandetal för "+barnNamn+" är: "+u);







    }
}
