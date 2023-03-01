package OvningsUppgifter.Uppg_3b;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Repository r = new Repository();
        Scanner sc = new Scanner(System.in);
        String tomteNamn;
        List<Tomte> tomteList = new ArrayList<>();

        while(true){

            System.out.println("Ange Tomte som ska tas bort: ");
            tomteNamn = sc.nextLine();

            r.taBortTomte(tomteNamn);

            tomteList = r.allaTomtar();

            tomteList.forEach(t -> System.out.println(t.getId()+" "+t.getNamn()));
        }
    }
}
