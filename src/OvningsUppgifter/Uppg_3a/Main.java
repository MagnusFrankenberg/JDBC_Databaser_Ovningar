package OvningsUppgifter.Uppg_3a;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Repository r = new Repository();
        Scanner sc = new Scanner(System.in);
        String oldName;
        String newName;
        List<Tomte> tomteList = new ArrayList<>();

        while(true){

            System.out.println("Ange nuvarande namn: ");
            oldName = sc.nextLine();

            System.out.println("Ange nytt namn: ");
            newName = sc.nextLine();

            r.bytTomteNamn(oldName,newName);

            tomteList = r.allaTomtar();

            tomteList.forEach(t -> System.out.println(t.getNamn()));

        }


    }
}
