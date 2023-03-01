package OvningsUppgifter.Uppg_1;

import java.io.IOException;
import java.util.List;
import java.util.OptionalDouble;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Repository r = new Repository();

        List<Barn> barnList = r.getBarn();
        List<Tomte> tomteList = r.getTomte();
        OptionalDouble avg = barnList
                .stream()
                .mapToDouble(b->b.getUppförandetal())
                .average();


        while(true) {
            System.out.println("Antal barn: " + barnList.size());
            System.out.println("Antal Tomtar: " + tomteList.size());
            tomteList.forEach(t -> System.out.println(t.getNamn()));
            System.out.println("Uppförandetal medelvärde: " + avg);

            Thread.sleep(2000);
        }






    }
}
