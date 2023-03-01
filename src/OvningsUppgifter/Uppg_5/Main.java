package OvningsUppgifter.Uppg_5;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Repository r = new Repository();
        Scanner sc = new Scanner(System.in);
        String present;
        int tillverkareID;

        while(true){

            System.out.println("Ange Present att lägga till: ");
            present = sc.nextLine();

            System.out.println("Ange Id för Tillverkningstomte (13-20): ");
            tillverkareID = sc.nextInt();
            sc.nextLine();

            String result = r.läggInPresent(present,tillverkareID);

            System.out.println(result);
        }
    }
}
